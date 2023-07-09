package com.sqli.intern.api.validator.core;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.zjsonpatch.JsonDiff;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;
import com.sqli.intern.api.validator.utilities.enums.ValidationStatus;
import org.springframework.stereotype.Component;


@Component
public class Validator extends ApiValidationHandler implements JsonComparator {

    private static final ObjectMapper JSON_MAPPER = new ObjectMapper();

    @Override
    public void invoke(ResponseDto responseDto) {
        try {
            JsonNode currentJsonNode = JSON_MAPPER.readTree(responseDto.getActualResponse());
            JsonNode expectedJsonNode = JSON_MAPPER.readTree(responseDto.getExpectedResponse());
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
