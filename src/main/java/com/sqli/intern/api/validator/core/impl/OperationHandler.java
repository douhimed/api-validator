package com.sqli.intern.api.validator.core.impl;

import com.sqli.intern.api.validator.core.impl.jsonhandler.JsonHandler;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;

public abstract class OperationHandler {

    protected OperationHandler next;

    public void invokeNext(ResponseDto responseDto) {
        getNext().invoke(responseDto);
    }

    public abstract JsonHandler getNext();

    public abstract void invoke(ResponseDto responseDto);

}
