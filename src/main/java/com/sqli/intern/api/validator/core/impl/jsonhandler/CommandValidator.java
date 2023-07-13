package com.sqli.intern.api.validator.core.impl.jsonhandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.sqli.intern.api.validator.utilities.ValidatorUtility;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;
import com.sqli.intern.api.validator.utilities.enums.ValidationStatus;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

@Component("commandValidator")
public class CommandValidator extends JsonHandler {

    private static final Map<String, Predicate<String>> PREDICATES;

    static {
        PREDICATES = new HashMap<>();
        PREDICATES.put("number", ValidatorUtility::isNumber);
        PREDICATES.put("string", ValidatorUtility::isString);
        PREDICATES.put("boolean", ValidatorUtility::isBoolean);
    }

    @Override
    protected void invokeValidation(JsonNode patch, ResponseDto responseDto) {
        ValidationStatus status = PREDICATES.get(responseDto.getExpectedType()).test(responseDto.getActualResponse())
                ? ValidationStatus.VALID
                : ValidationStatus.INVALID;
        responseDto.setValidationStatus(status);
        if (status.isInvalid())
            responseDto.addMessage("Invalid type");
    }

}
