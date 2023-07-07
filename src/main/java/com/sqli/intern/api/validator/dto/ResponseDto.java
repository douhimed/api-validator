package com.sqli.intern.api.validator.dto;

import com.sqli.intern.api.validator.utils.ValidationStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class ResponseDto {
    private String url;
    private String type;
    private String body;
    private String expectedResponse;
    private String httpStatusCode;
    private String actualResponse;
    private List<String> message;
    private ValidationStatus validationStatus;

}
