package com.sqli.intern.api.validator.utilities.enums;

public enum ExceptionMessageEnum {
    OPERATION_NOT_ELIGIBLE("NOT VALID OPERATION");

    private final String message;

    ExceptionMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
