package com.sqli.intern.api.validator.core;

import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

@Component
public class GetRequestHandler extends ApiCaller {
    @Override
    public HttpMethod getType() {
        return HttpMethod.GET;
    }

    @Override
    public HttpEntity getBody(ResponseDto responseDto) {
        return null;
    }

}
