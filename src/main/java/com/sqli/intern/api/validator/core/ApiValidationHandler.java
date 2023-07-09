package com.sqli.intern.api.validator.core;

import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;

import java.util.Objects;

public abstract class ApiValidationHandler {
    protected ApiValidationHandler next;


    public void setNext(ApiValidationHandler next) {
        this.next = next;
    }

    public void invokeNext(ResponseDto responseDto) {
        if (Objects.nonNull(next)) {
            next.invoke(responseDto);
        }
    }

    public abstract void invoke(ResponseDto responseDto);

}
