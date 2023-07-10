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

import java.util.ArrayList;
import java.util.List;


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

            if (patch.size() == 0) {
                responseDto.setValidationStatus(ValidationStatus.VALID);
            } else {
                List<String> patchStrings = new ArrayList<>();
                patch.forEach(node -> patchStrings.add(node.toString()));
                responseDto.setValidationStatus(ValidationStatus.INVALID);
                responseDto.setMessage(patchStrings);
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public void compareJson(ResponseDto responseDto) {
        invoke(responseDto);
    }

}
