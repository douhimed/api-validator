package com.sqli.intern.api.validator.core.impl.jsonhandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.flipkart.zjsonpatch.JsonDiff;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;
import com.sqli.intern.api.validator.utilities.enums.ValidationStatus;
import org.springframework.stereotype.Component;

@Component
public class QueryValidator extends JsonHandler {


    @Override
    protected void invokeValidation(JsonNode expected, ResponseDto responseDto) {
        try {
            JsonNode currentJsonNode = objectMapper.readTree(responseDto.getActualResponse());
            JsonNode patch = JsonDiff.asJson(expected, currentJsonNode);
            if (patch.size() == 0) {
                responseDto.setValidationStatus(ValidationStatus.VALID);
            } else {
                patch.forEach(node -> responseDto.addMessage(node.toString()));
                responseDto.setValidationStatus(ValidationStatus.INVALID);
            }
        } catch (JsonProcessingException e) {
            responseDto.addMessage("INVALID FORMAT");
        }
    }
    public void compareJson(ResponseDto responseDto) {
        invoke(responseDto);
    }

}
