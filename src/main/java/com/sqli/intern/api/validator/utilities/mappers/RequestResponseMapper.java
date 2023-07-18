package com.sqli.intern.api.validator.utilities.mappers;

import com.sqli.intern.api.validator.utilities.dtos.OperationDto;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;

public final class RequestResponseMapper {

    private RequestResponseMapper() {
        throw new RuntimeException("INSTANTIATION NOT ALLOWED");
    }

    public static ResponseDto map(OperationDto operationDto) {
        return ResponseDto.builder()
                .url(operationDto.getUrl())
                .type(operationDto.getType())
                .body(operationDto.getBody())
                .expectedResponse(operationDto.getExpectedResponse())
                .actualResponse(operationDto.getActualResponse())
                .expectedType(operationDto.getExpectedType())
                .build();
    }
}
