package com.sqli.intern.api.validator.services.impl;

import com.sqli.intern.api.validator.core.JsonComparator;
import com.sqli.intern.api.validator.core.impl.RestStrategyHandler;
import com.sqli.intern.api.validator.services.OperationService;
import com.sqli.intern.api.validator.utilities.dtos.OperationDto;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;
import com.sqli.intern.api.validator.utilities.mappers.RequestResponseMapper;
import com.sqli.intern.api.validator.utilities.enums.ValidationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private RestStrategyHandler restStrategyHandler;

    @Autowired
    private JsonComparator queryValidator;

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

}
