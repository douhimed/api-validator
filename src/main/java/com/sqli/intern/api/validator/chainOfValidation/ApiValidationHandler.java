package com.sqli.intern.api.validator.chainOfValidation;

import com.sqli.intern.api.validator.dto.ResponseDto;

public abstract class ApiValidationHandler {
    protected ApiValidationHandler next;

    public void setNext(ApiValidationHandler next) {
        this.next = next;
    }

    public abstract void invoke(ResponseDto responseDto);
}
