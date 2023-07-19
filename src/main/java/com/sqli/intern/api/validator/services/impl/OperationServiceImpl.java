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

import static com.sqli.intern.api.validator.utilities.enums.ExceptionMessageEnum.NOT_FOUND_OPERATION;
import static com.sqli.intern.api.validator.utilities.enums.ExceptionMessageEnum.NOT_VALID_HTTP_METHOD;


@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private RestStrategyHandler restStrategyHandler;

    @Autowired
    private JsonComparator queryValidator;
    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private GetValidator getValidator;
    @Autowired
    private PostValidator postValidator;
    @Autowired
    private PutValidator putValidator;
    @Autowired
    private DeleteValidator deleteValidator;

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
                .map(OperationMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public OperationDto getOperationById(Long id) {
        return operationRepository.findById(id)
                .map(OperationMapper::map)
                .orElseThrow(() -> new OperationException(NOT_FOUND_OPERATION));
    }

    @Override
    public Long addOperation(OperationDto operationDto) {
        validateOperation(operationDto);
        return operationRepository.save(OperationMapper.from(operationDto)).getId();
    }

    @Override
    public Long updateOperation(Long operationId, OperationDto operationDto) {
        validateOperation(operationDto);
        return operationRepository.findById(operationId)
                .map(operationEntity -> {
                    updateOperationEntity(operationDto, operationEntity);
                    return operationRepository.save(operationEntity).getId();
                })
                .orElseThrow(() -> new OperationException(NOT_FOUND_OPERATION));
    }

    private void updateOperationEntity(OperationDto operationDto, OperationEntity operationEntity) {
        operationEntity.setUrl(operationDto.getUrl());
        operationEntity.setBody(operationDto.getBody());
        operationEntity.setType(operationDto.getType());
        operationEntity.setExpectedType(operationDto.getExpectedType());
        operationEntity.setExpectedResponse(operationDto.getExpectedResponse());
    }

    @Override
    public Long deleteOperation(Long id) {
        Optional<OperationEntity> operationEntity = operationRepository.findById(id);
        validateOperation(OperationMapper.map(operationEntity.get()));
        return operationEntity
                .map(entity -> {
                    operationRepository.delete(entity);
                    return id;
                })
                .orElseThrow(() -> new OperationException(NOT_FOUND_OPERATION));
    }

    private void validateOperation(OperationDto operationDto) {
        getValidator(operationDto.getType()).validate(operationDto);
    }

    private OperationValidator getValidator(String type) throws OperationException {
        return switch (type) {
            case "GET" -> getValidator;
            case "POST" -> postValidator;
            case "PUT" -> putValidator;
            case "DELETE" -> deleteValidator;
            default -> throw new OperationException(NOT_VALID_HTTP_METHOD);
        };
    }
}

