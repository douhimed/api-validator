package com.sqli.intern.api.validator.core.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.zjsonpatch.JsonDiff;
import com.sqli.intern.api.validator.core.JsonComparator;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;
import com.sqli.intern.api.validator.utilities.enums.ValidationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class JsonHandler extends OperationHandler implements JsonComparator {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void invoke(ResponseDto responseDto) {
        try {
            JsonNode currentJsonNode = objectMapper.readTree(responseDto.getActualResponse());
            JsonNode expectedJsonNode = objectMapper.readTree(responseDto.getExpectedResponse());
            JsonNode patch = JsonDiff.asJson(expectedJsonNode, currentJsonNode);
            responseDto.setValidationStatus(patch.size() == 0 ? ValidationStatus.VALID : ValidationStatus.INVALID);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void compareJson(ResponseDto responseDto) {
        invoke(responseDto);
    }

}
