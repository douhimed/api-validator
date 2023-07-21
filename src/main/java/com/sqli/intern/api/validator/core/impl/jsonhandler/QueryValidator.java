package com.sqli.intern.api.validator.core.impl.jsonhandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.flipkart.zjsonpatch.JsonDiff;
import com.sqli.intern.api.validator.utilities.dtos.ReportDto;
import com.sqli.intern.api.validator.utilities.JsonUtils;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;
import com.sqli.intern.api.validator.utilities.enums.ValidationStatus;
import com.sqli.intern.api.validator.utilities.mappers.ReportMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QueryValidator extends JsonHandler {
    private static final String PATH = "/op";
    private static final String VALUE = "move";


    @Override
    protected void invokeValidation(JsonNode expected, ResponseDto responseDto) {
        try {
            JsonNode currentJsonNode = objectMapper.readTree(responseDto.getActualResponse());
            JsonNode patch = JsonDiff.asJson(expected, currentJsonNode);
            renseignerMessages(responseDto, patch);
            renseignerValidationStatus(responseDto);
        } catch (JsonProcessingException e) {
            responseDto.addMessage(ReportDto.createErrorMessage("INVALID FORMAT"));
            responseDto.setValidationStatus(ValidationStatus.INVALID);
        }
    }

    private static void renseignerValidationStatus(ResponseDto responseDto) {
        responseDto.setValidationStatus(responseDto.getMessages().isEmpty()
                ? ValidationStatus.VALID
                : ValidationStatus.INVALID);
    }

    private static void renseignerMessages(ResponseDto responseDto, JsonNode patch) {

        List<ReportDto> reportDtos = new ArrayList<>();
        for (JsonNode node : patch) {
            if (JsonUtils.estNodeValueNotEquals(node, PATH, VALUE))
                reportDtos.add(ReportMapper.map(node));
        }
        responseDto.setMessages(reportDtos);
    }

    public void compareJson(ResponseDto responseDto) {
        invoke(responseDto);
    }

}
