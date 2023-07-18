package com.sqli.intern.api.validator.services.impl;

import com.sqli.intern.api.validator.services.OperationRules;
import com.sqli.intern.api.validator.services.OperationValidator;
import com.sqli.intern.api.validator.utilities.dtos.OperationDto;
import org.springframework.stereotype.Component;

@Component
public class DeleteValidator implements OperationValidator {
    @Override
    public boolean validate(OperationDto operationDto) {
        OperationRules.isExpectedResponseNull(operationDto.getExpectedResponse());
        OperationRules.isExpectedResponseTypeNotValid(operationDto.getExpectedResponse());
        OperationRules.isHttpMethodNotValid(operationDto.getType());
        return true;
    }
}
