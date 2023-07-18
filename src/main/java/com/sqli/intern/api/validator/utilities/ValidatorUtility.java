package com.sqli.intern.api.validator.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.List;

public final class ValidatorUtility {

    private static final List<String> BOOLEANS = Arrays.asList("TRUE", "FALSE");

    public static boolean isNumber(String value) {
        if (StringUtils.isBlank(value))
            return false;
        try {
            Double.parseDouble(value);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isString(String value) {
        return StringUtils.isNotBlank(value)
                && !isNumber(value)
                && !Boolean.parseBoolean(value);
    }

    public static boolean isBoolean(String value) {
        return StringUtils.isNotBlank(value) && BOOLEANS.contains(value.toUpperCase());
    }

    public static boolean isJson(String value) {
        try {
            new ObjectMapper().readTree(value);
            return true;
        } catch (JsonProcessingException e) {
            return false;
        }
    }

    public static boolean isNotJson(String value) {
        return !ValidatorUtility.isJson(value);
    }

    public static boolean isVoid(String value) {
        return StringUtils.isBlank(value);
    }
}
