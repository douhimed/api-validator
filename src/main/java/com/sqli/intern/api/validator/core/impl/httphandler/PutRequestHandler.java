package com.sqli.intern.api.validator.core.impl.httphandler;

import com.sqli.intern.api.validator.core.impl.jsonhandler.JsonHandler;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class PutRequestHandler extends RestHandler {
    @Autowired
    private JsonHandler commandValidator;

    @Override
    public HttpMethod getType() {
        return HttpMethod.PUT;
    }

    @Override
    public HttpEntity getBody(ResponseDto responseDto) {
        return new HttpEntity<>(responseDto.getBody());
    }

    @PostConstruct
    public void initNext() {
        super.setNext(commandValidator);
    }

}
