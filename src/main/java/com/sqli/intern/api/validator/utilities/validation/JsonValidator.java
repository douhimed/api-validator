package com.sqli.intern.api.validator.utilities.validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sqli.intern.api.validator.utilities.validation.annotations.JsonValid;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class JsonValidator implements ConstraintValidator<JsonValid, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            new ObjectMapper().readTree(value);
            return true;
        } catch (JsonProcessingException e) {
            return false;
        }
    }
}
