package com.sqli.intern.api.validator.utilities.exceptions;

import com.sqli.intern.api.validator.utilities.enums.ExceptionMessageEnum;

public class AuthenticationException extends RuntimeException {
    public AuthenticationException(String message) {
        super(message);
    }

    public AuthenticationException(ExceptionMessageEnum raison) {
        super(String.valueOf(raison));
    }

}
