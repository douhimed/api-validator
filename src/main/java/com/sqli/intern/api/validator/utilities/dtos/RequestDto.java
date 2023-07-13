package com.sqli.intern.api.validator.utilities.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestDto {
    private String url;
    private String type;
    private String body;
    private String expectedResponse;
    private String actualResponse;
    private String expectedType;
}
