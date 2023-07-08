package com.sqli.intern.api.validator.utils.dtos;

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

}
