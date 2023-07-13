package com.sqli.intern.api.validator.utilities;

import java.util.Objects;

public final class StringUtils {

    public static boolean isBlank(String value) {
        return Objects.isNull(value) || value.trim().length() == 0;
    }

    public static boolean isNotBlank(String value) {
        return !StringUtils.isBlank(value);
    }
}
