package com.sqli.intern.api.validator.utilities.enums;

public enum ValidationStatus {
    VALID, INVALID;

    public boolean isInvalid() {
        return this == INVALID;
    }
}
