package com.sqli.intern.api.validator.utilities.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

public class JsonFormatter {
    private final ObjectMapper objectMapper;

    public JsonFormatter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping("/home")
    public String getFormattedResponse(@ModelAttribute ResponseDto responseDto, Model model) {
        String currentResponse;
        try {
            currentResponse = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDto.getCurrentResponse());
        } catch (JsonProcessingException e) {
            currentResponse = "Error while formatting the JSON response";
            e.printStackTrace();
        }
        model.addAttribute("currentResponse", currentResponse);
        return "home";
    }
}
