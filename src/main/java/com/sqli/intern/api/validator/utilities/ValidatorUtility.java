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
}
