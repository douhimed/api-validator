package com.sqli.intern.api.validator.core.impl;

import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;

import java.util.Optional;

public abstract class OperationHandler {

    protected OperationHandler next;

    public void setNext(OperationHandler next) {
        this.next = next;
    }

    public void invokeNext(ResponseDto responseDto) {
        Optional.ofNullable(next)
                .ifPresent(next -> next.invoke(responseDto));
    }

    public abstract void invoke(ResponseDto responseDto);

}
