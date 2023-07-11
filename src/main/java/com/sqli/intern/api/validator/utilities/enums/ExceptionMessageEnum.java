package com.sqli.intern.api.validator.utilities.enums;

public enum
ExceptionMessageEnum {
    OPERATION_NOT_ELIGIBLE("NOT VALID OPERATION"),
    SERVICE_NOT_FOUND("SERVICE NOT FOUND"),
    BAD_REQUEST("BAD REQUEST");

    private final String message;

    ExceptionMessageEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
