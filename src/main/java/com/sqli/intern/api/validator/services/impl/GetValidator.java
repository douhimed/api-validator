package com.sqli.intern.api.validator.services.impl;

import com.sqli.intern.api.validator.services.OperationRules;
import com.sqli.intern.api.validator.services.OperationValidator;
import com.sqli.intern.api.validator.utilities.dtos.OperationDto;
import com.sqli.intern.api.validator.utilities.enums.ExpectedTypeEnum;
import com.sqli.intern.api.validator.utilities.exceptions.OperationException;
import org.springframework.stereotype.Component;

import static com.sqli.intern.api.validator.utilities.enums.ExceptionMessageEnum.NOT_VALID_EXPECTED_RESPONSE_TYPE;

@Component
public class GetValidator implements OperationValidator {

    @Override
    public boolean validate(OperationDto operationDto) {
        OperationRules.isUrlBlank(operationDto.getUrl());
        isExpectedTypeValid(operationDto.getExpectedType());
        OperationRules.isExpectedResponseBlank(operationDto.getExpectedResponse());
        OperationRules.isJsonValid(operationDto.getExpectedResponse());
        return true;
    }

    private boolean isExpectedTypeValid(String type) {
        if(!ExpectedTypeEnum.estGetTypeValid(type))
            throw new OperationException(NOT_VALID_EXPECTED_RESPONSE_TYPE);
        return true;
    }

}
