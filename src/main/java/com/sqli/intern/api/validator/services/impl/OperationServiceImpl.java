package com.sqli.intern.api.validator.services.impl;

import com.sqli.intern.api.validator.core.JsonComparator;
import com.sqli.intern.api.validator.core.impl.RestStrategyHandler;
import com.sqli.intern.api.validator.entities.OperationEntity;
import com.sqli.intern.api.validator.repositories.OperationRepository;
import com.sqli.intern.api.validator.services.OperationService;
import com.sqli.intern.api.validator.services.OperationValidator;
import com.sqli.intern.api.validator.utilities.dtos.OperationDto;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;
import com.sqli.intern.api.validator.utilities.exceptions.OperationException;
import com.sqli.intern.api.validator.utilities.mappers.OperationMapper;
import com.sqli.intern.api.validator.utilities.mappers.RequestResponseMapper;
import com.sqli.intern.api.validator.utilities.enums.ValidationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.sqli.intern.api.validator.utilities.enums.ExceptionMessageEnum.*;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private RestStrategyHandler restStrategyHandler;

    @Autowired
    private JsonComparator queryValidator;
    @Autowired
    private OperationRepository operationRepository;

    @Override
    public ValidationStatus compareJson(OperationDto operationDto) {
        final ResponseDto responseDto = RequestResponseMapper.map(operationDto);
        queryValidator.compareJson(responseDto);
        return responseDto.getValidationStatus();
    }

    @Override
    public ResponseDto call(OperationDto operationDto) {
        final ResponseDto responseDto = RequestResponseMapper.map(operationDto);
        restStrategyHandler.getCaller(operationDto.getType()).call(responseDto);
        return responseDto;
    }

    @Override
    public List<OperationDto> getAllOperations() {
        return operationRepository.findAll()
                .stream()
                .map(OperationMapper::fromOperationEntity)
                .collect(Collectors.toList());
    }

    @Override
    public OperationDto getOperationById(Long id) {
        return operationRepository.findById(id)
                .map(OperationMapper::fromOperationEntity)
                .orElseThrow(() -> new OperationException(NOT_FOUND_OPERATION));
    }

    @Override
    public Long addOperation(OperationDto operationDto) {
        OperationDto validatedOperationDto = validateOperation(operationDto);
        return operationRepository.save(OperationMapper.fromOperationDto(validatedOperationDto)).getId();
    }

    @Override
    public Long updateOperation(Long operationId, OperationDto operationDto) {
        Optional<OperationEntity> operationOptional = operationRepository.findById(operationId);

        if (operationOptional.isPresent()) {
            OperationDto validatedOperationDto = validateOperation(operationDto);
            OperationEntity operationEntity = operationOptional.get();
            operationEntity.setUrl(validatedOperationDto.getUrl());
            operationEntity.setType(validatedOperationDto.getType());
            operationEntity.setBody(validatedOperationDto.getBody());
            operationEntity.setExpectedResponse(validatedOperationDto.getExpectedResponse());
            operationEntity.setExpectedType(validatedOperationDto.getExpectedType());
            operationRepository.save(operationEntity);
        } else {
            throw new OperationException(NOT_FOUND_OPERATION);
        }

        return operationId;
    }

    @Override
    public Long deleteOperation(Long id) {
        return operationRepository.findById(id)
                .map(entity -> {
                    operationRepository.delete(entity);
                    return id;
                })
                .orElseThrow(() -> new OperationException(NOT_FOUND_OPERATION));
    }

    private OperationDto validateOperation(OperationDto operationDto) {
        OperationValidator validator = switch (operationDto.getType()) {
            case "GET" -> new GetValidator();
            case "POST" -> new PostValidator();
            case "PUT" -> new PutValidator();
            case "DELETE" -> new DeleteValidator();
            default -> throw new OperationException(NOT_VALID_HTTP_METHOD);
        };

        if (!validator.validate(operationDto)) {
            throw new OperationException(OPERATION_NOT_ELIGIBLE);
        }

        return operationDto;
    }
}

