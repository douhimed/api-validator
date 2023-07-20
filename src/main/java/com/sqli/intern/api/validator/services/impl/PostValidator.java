package com.sqli.intern.api.validator.services.impl;

import com.sqli.intern.api.validator.services.OperationRules;
import com.sqli.intern.api.validator.services.OperationValidator;
import com.sqli.intern.api.validator.utilities.dtos.OperationDto;
import com.sqli.intern.api.validator.utilities.enums.ExpectedTypeEnum;
import com.sqli.intern.api.validator.utilities.exceptions.OperationException;
import org.springframework.stereotype.Component;

import static com.sqli.intern.api.validator.utilities.enums.ExceptionMessageEnum.NOT_VALID_EXPECTED_RESPONSE_TYPE;

@Component
public class PostValidator implements OperationValidator {

    public boolean validate(OperationDto operationDto) {
        isExpectedTypeValid(operationDto.getExpectedType());
        OperationRules.isBodyBlank(operationDto.getBody());
        OperationRules.isJsonValid((operationDto.getBody()));
        return true;
    }

    private boolean isExpectedTypeValid(String type) {
        if(!ExpectedTypeEnum.estPostTypeValid(type))
            throw new OperationException(NOT_VALID_EXPECTED_RESPONSE_TYPE);
        return true;
    }
}