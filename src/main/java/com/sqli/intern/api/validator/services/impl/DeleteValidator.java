package com.sqli.intern.api.validator.services.impl;

import com.sqli.intern.api.validator.services.OperationRules;
import com.sqli.intern.api.validator.services.OperationValidator;
import com.sqli.intern.api.validator.utilities.dtos.OperationDto;
import com.sqli.intern.api.validator.utilities.exceptions.OperationException;

import static com.sqli.intern.api.validator.utilities.enums.ExceptionMessageEnum.OPERATION_NOT_ELIGIBLE;

public class DeleteValidator implements OperationValidator {
    @Override
    public boolean validate(OperationDto operationDto) {
        if (!OperationRules.isExpectedResponseNotNull(operationDto.getExpectedResponse())
                || OperationRules.isExpectedResponseNotValid(operationDto.getExpectedResponse())
                || OperationRules.isHttpMethodNotValid(operationDto.getType())
        )
            throw new OperationException(OPERATION_NOT_ELIGIBLE);
        return true;
    }
}
