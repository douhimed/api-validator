package com.sqli.intern.api.validator.utilities.exceptions;

import com.sqli.intern.api.validator.utilities.enums.ExceptionMessageEnum;

public class TestRunException extends RuntimeException{
    public TestRunException(String message) {
        super(message);
    }

    public TestRunException(ExceptionMessageEnum reason) {
        this(reason.getMessage());
    }
}
