package com.sqli.intern.api.validator.utilities.exceptions;

import com.sqli.intern.api.validator.utilities.enums.ExceptionMessageEnum;

public class InstantiationException extends RuntimeException{
    public InstantiationException(String message) {
        super(message);
    }

    public InstantiationException(ExceptionMessageEnum raison) {
        super(String.valueOf(raison));
    }
}
