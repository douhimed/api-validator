package com.sqli.intern.api.validator.core.impl;

import com.sqli.intern.api.validator.core.RestCaller;
import com.sqli.intern.api.validator.utilities.enums.OperationTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RestStrategyHandler {

    @Autowired
    private RestCaller getRequestHandler;
    @Autowired
    private RestCaller postRequestHandler;
    @Autowired
    private RestCaller putRequestHandler;
    @Autowired
    private RestCaller deleteRequestHandler;

    public RestCaller getCaller(String type) {
        if (OperationTypeEnum.isTypeGet(type))
            return getRequestHandler;
        else if (OperationTypeEnum.isTypePost(type))
            return postRequestHandler;
        else if (OperationTypeEnum.isTypePut(type))
            return putRequestHandler;
        else if (OperationTypeEnum.isTypeDelete(type))
            return deleteRequestHandler;
        throw new RuntimeException("NOT ALLOWED OPERATION");
    }
}
