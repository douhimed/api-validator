package com.sqli.intern.api.validator.chainofvalidation;

import com.sqli.intern.api.validator.dtos.ResponseDto;

public abstract class ApiValidationHandler {
    protected ApiValidationHandler next;


    public void setNext(ApiValidationHandler next) {
        this.next = next;
    }

    public void invokeNext(ResponseDto responseDto) {
        if (next != null) {
            next.invoke(responseDto);
        }
    }

    public abstract void invoke(ResponseDto responseDto);

}
