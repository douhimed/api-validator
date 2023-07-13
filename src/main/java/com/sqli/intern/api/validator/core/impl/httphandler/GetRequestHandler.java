package com.sqli.intern.api.validator.core.impl.httphandler;

import com.sqli.intern.api.validator.core.impl.jsonhandler.JsonHandler;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

@Component
public class GetRequestHandler extends RestHandler {

    @Autowired
    private JsonHandler queryValidator;

    @Override
    public HttpMethod getType() {
        return HttpMethod.GET;
    }

    @Override
    public HttpEntity getBody(ResponseDto responseDto) {
        return null;
    }


    @PostConstruct
    public void initNext() {
        super.setNext(queryValidator);
    }

}
