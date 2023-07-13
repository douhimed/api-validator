package com.sqli.intern.api.validator.utilities;

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
}
