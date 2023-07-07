package com.sqli.intern.api.validator.chainofvalidation;

import com.sqli.intern.api.validator.dtos.RequestDto;

public class StrategyWs {
    public static Caller init(RequestDto requestDto) {
        if (requestDto.isOfType("GET"))
            return new GetRequestHandler();
        return null;
    }
}
