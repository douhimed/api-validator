package com.sqli.intern.api.validator.services.impl;

import com.sqli.intern.api.validator.services.OperationRules;
import com.sqli.intern.api.validator.services.OperationValidator;
import com.sqli.intern.api.validator.utilities.dtos.OperationDto;
import org.springframework.stereotype.Component;

@Component
public class PostValidator implements OperationValidator {
    public boolean validate(OperationDto operationDto) {
        OperationRules.isBodyNull(operationDto.getBody());
        OperationRules.isJsonNotValid((operationDto.getBody()));
        OperationRules.isExpectedResponseTypeNotValid((operationDto.getExpectedResponse()));
        return true;
    }
}
