package com.sqli.intern.api.validator.chainofvalidation;

import com.sqli.intern.api.validator.utils.dtos.ResponseDto;
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
