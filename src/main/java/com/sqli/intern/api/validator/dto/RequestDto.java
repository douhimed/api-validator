package com.sqli.intern.api.validator.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class RequestDto {
    private String expectedResponse;
    private String actualResponse;

}
