package com.sqli.intern.api.validator.utilities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public final class ValidatorUtility {

    public static boolean isNumber(String actualResponse) {
        if (actualResponse == null || actualResponse.trim().length() == 0) {
            return false;
        }
        try {
            double d = Double.parseDouble(actualResponse);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isString(String actualResponse) {
        return actualResponse != null && !isNumber(actualResponse) && !Boolean.parseBoolean(actualResponse);
    }

    public static boolean isBoolean(String actualResponse) {
        return actualResponse != null && (actualResponse.equalsIgnoreCase("true") || actualResponse.equalsIgnoreCase("false"));
    }

    public static boolean isJson(String actualResponse) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(actualResponse);
            return true;
        } catch (JsonProcessingException e) {
            return false;
        }
    }
}
