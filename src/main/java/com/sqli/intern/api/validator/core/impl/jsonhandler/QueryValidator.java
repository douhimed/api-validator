package com.sqli.intern.api.validator.core.impl.jsonhandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.flipkart.zjsonpatch.JsonDiff;
import com.sqli.intern.api.validator.utilities.dtos.ReportDto;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;
import com.sqli.intern.api.validator.utilities.enums.ValidationStatus;
import com.sqli.intern.api.validator.utilities.mappers.ReportMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
                responseDto.setValidationStatus(ValidationStatus.INVALID);

                List<ReportDto> reportDtos = new ArrayList<>();
                patch.forEach(node -> {
                    ReportDto reportDto = ReportMapper.map(node);
                    reportDtos.add(reportDto);
                });
                responseDto.setMessages(reportDtos);
            }
        } catch (JsonProcessingException e) {
            responseDto.addMessage(ReportDto.createErrorMessage("INVALID FORMAT"));
        }
    }

    public void compareJson(ResponseDto responseDto) {
        invoke(responseDto);
    }

}
