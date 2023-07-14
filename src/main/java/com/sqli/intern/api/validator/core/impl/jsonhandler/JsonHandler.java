package com.sqli.intern.api.validator.core.impl.jsonhandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqli.intern.api.validator.core.JsonComparator;
import com.sqli.intern.api.validator.core.impl.OperationHandler;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public abstract class JsonHandler extends OperationHandler implements JsonComparator {

    @Autowired
    protected ObjectMapper objectMapper;


    @Override
    public void invoke(ResponseDto responseDto) {
        try {
            JsonNode expectedJsonNode = objectMapper.readTree(responseDto.getExpectedResponse());
            invokeValidation(expectedJsonNode, responseDto);
        } catch (JsonProcessingException e) {
            responseDto.addMessage("INVALID FORMAT");
        }
    }

    public void compareJson(ResponseDto responseDto) {
        invoke(responseDto);
    }

    protected abstract void invokeValidation(JsonNode expected, ResponseDto responseDto);

}