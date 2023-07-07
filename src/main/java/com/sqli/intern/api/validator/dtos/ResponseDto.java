package com.sqli.intern.api.validator.dtos;

import com.sqli.intern.api.validator.utils.enums.ValidationStatus;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseDto {
    private String url;
    private String type;
    private String body;
    private String expectedResponse;
    private String httpStatus;
    private String currentResponse;
    private String message;
    private ValidationStatus validationStatus;
}
