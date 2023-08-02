package com.sqli.intern.api.validator.core.impl.jsonhandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flipkart.zjsonpatch.JsonDiff;
import com.sqli.intern.api.validator.utilities.dtos.ReportDto;
import com.sqli.intern.api.validator.utilities.JsonUtils;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;
import com.sqli.intern.api.validator.utilities.enums.ValidationStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class QueryValidator extends JsonHandler {

    private static final String PATH = "/op";
    private static final String MOVE = "move";


    @Override
    protected void invokeValidation(JsonNode expected, ResponseDto responseDto) {
        try {
            JsonNode currentJsonNode = objectMapper.readTree(responseDto.getActualResponse());
            JsonNode patch = JsonDiff.asJson(expected, currentJsonNode);
            createValidationMessages(responseDto, patch);
            setValidationStatus(responseDto);
        } catch (JsonProcessingException e) {
            responseDto.addMessage(ReportDto.createErrorMessage("INVALID FORMAT"));
            responseDto.setValidationStatus(ValidationStatus.INVALID);
        }
    }

    private static void setValidationStatus(ResponseDto responseDto) {
        responseDto.setValidationStatus(responseDto.getMessages().isEmpty()
                ? ValidationStatus.VALID
                : ValidationStatus.INVALID);
    }

    private static void createValidationMessages(ResponseDto responseDto, JsonNode patch) {

        List<ReportDto> reportDtos = new ArrayList<>();
        for (JsonNode node : patch) {
            if (JsonUtils.isNodeValueNotEqual(node, PATH, MOVE))
                reportDtos.add(mapJsonToReportDto(node));
        }
        responseDto.setMessages(reportDtos);
    }

    private static ReportDto mapJsonToReportDto(JsonNode node) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(node, ReportDto.class);
    }

    @Override
    public void compareJson(ResponseDto responseDto) {
        invoke(responseDto);
    }

}
