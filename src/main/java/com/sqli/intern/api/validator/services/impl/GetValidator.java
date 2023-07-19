package com.sqli.intern.api.validator.services.impl;

import com.sqli.intern.api.validator.services.OperationRules;
import com.sqli.intern.api.validator.services.OperationValidator;
import com.sqli.intern.api.validator.utilities.dtos.OperationDto;
import com.sqli.intern.api.validator.utilities.enums.ExpectedTypeEnum;
import com.sqli.intern.api.validator.utilities.exceptions.OperationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.sqli.intern.api.validator.utilities.enums.ExceptionMessageEnum.NOT_VALID_EXPECTED_RESPONSE_TYPE;
import static com.sqli.intern.api.validator.utilities.enums.ExpectedTypeEnum.*;

@Component
public class GetValidator implements OperationValidator {

    private static final List<ExpectedTypeEnum> VALID_EXPECTED_TYPE;

    static {
        VALID_EXPECTED_TYPE = new ArrayList<>();
        VALID_EXPECTED_TYPE.add(JSON);
        VALID_EXPECTED_TYPE.add(NUMBER);
        VALID_EXPECTED_TYPE.add(STRING);
        VALID_EXPECTED_TYPE.add(BOOLEAN);
    }

    @Override
    public boolean validate(OperationDto operationDto) {
        isExpectedTypeValid(operationDto.getExpectedType());
        OperationRules.isExpectedResponseBlank(operationDto.getExpectedResponse());
        OperationRules.isJsonValid(operationDto.getExpectedResponse());
        return true;
    }

    @Override
    public boolean isExpectedTypeValid(String type) {
        try {
            ExpectedTypeEnum.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new OperationException(NOT_VALID_EXPECTED_RESPONSE_TYPE);
        }

        return true;
    }

}
