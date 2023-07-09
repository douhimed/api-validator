package com.sqli.intern.api.validator.utilities.mappers;

import com.sqli.intern.api.validator.utilities.dtos.RequestDto;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;

public final class RequestResponseMapper {

    private RequestResponseMapper() {
        throw new RuntimeException("INSTANTIATION NOT ALLOWED");
    }

    public static ResponseDto map(RequestDto requestDto) {
        return ResponseDto.builder()
                .url(requestDto.getUrl())
                .type(requestDto.getType())
                .body(requestDto.getBody())
                .expectedResponse(requestDto.getExpectedResponse())
                .build();
    }
}
