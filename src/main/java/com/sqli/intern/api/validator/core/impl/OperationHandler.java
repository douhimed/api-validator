package com.sqli.intern.api.validator.core.impl;

import com.sqli.intern.api.validator.authentication.AuthHeaderProvider;
import com.sqli.intern.api.validator.core.impl.jsonhandler.JsonHandler;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;

public abstract class OperationHandler {

    public void invokeNext(ResponseDto responseDto) {
        getNext().invoke(responseDto);
    }

    public abstract JsonHandler getNext();

    public abstract void invoke(ResponseDto responseDto);
    public abstract void invoke(ResponseDto responseDto, AuthHeaderProvider authHeaderProvider);

}
