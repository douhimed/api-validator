package com.sqli.intern.api.validator.chainofvalidation;

import com.sqli.intern.api.validator.dtos.ResponseDto;
import org.springframework.http.*;
import org.springframework.stereotype.Component;

@Component
public class GetRequestHandler extends ApiCaller {
    @Override
    public HttpMethod getType() {
        return HttpMethod.GET;
    }

    @Override
    public String getBody(ResponseDto responseDto) {
        return null;
    }

}
