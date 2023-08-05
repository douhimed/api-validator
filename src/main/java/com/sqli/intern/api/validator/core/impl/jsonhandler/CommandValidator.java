package com.sqli.intern.api.validator.core.impl.jsonhandler;

import com.fasterxml.jackson.databind.JsonNode;
import com.sqli.intern.api.validator.utilities.ValidatorUtility;
import com.sqli.intern.api.validator.utilities.dtos.ReportDto;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;
import com.sqli.intern.api.validator.utilities.enums.OperationTypeEnum;
import com.sqli.intern.api.validator.utilities.enums.ValidationStatus;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

@Component("commandValidator")
public class CommandValidator extends JsonHandler {

    private static final Map<String, Predicate<String>> PREDICATES;

    static {
        PREDICATES = new HashMap<>();
        PREDICATES.put("number", ValidatorUtility::isNumber);
        PREDICATES.put("string", ValidatorUtility::isString);
        PREDICATES.put("boolean", ValidatorUtility::isBoolean);
        PREDICATES.put("json", ValidatorUtility::isJson);
    }

    @Override
    protected void invokeValidation(JsonNode patch, ResponseDto responseDto) {
        Predicate<String> predicate = PREDICATES.getOrDefault(responseDto.getExpectedType(), ValidatorUtility::isJson);
        ValidationStatus status = predicate.test(responseDto.getActualResponse())
                ? ValidationStatus.VALID
                : ValidationStatus.INVALID;
        responseDto.setValidationStatus(status);
        if (status.isInvalid()) {
            responseDto.addMessage(ReportDto.createErrorMessage("INVALID TYPE"));
        }
    }

    @Override
    public void compareJson(ResponseDto responseDto) {
        invoke(responseDto, null);
    }

    @Override
    public Set<String> getSupportedRequestTypes() {
        Set<String> supportedRequestTypes = new HashSet<>();
        supportedRequestTypes.add(OperationTypeEnum.POST.name());
        supportedRequestTypes.add(OperationTypeEnum.DELETE.name());
        supportedRequestTypes.add(OperationTypeEnum.PUT.name());
        return supportedRequestTypes;
    }

}
