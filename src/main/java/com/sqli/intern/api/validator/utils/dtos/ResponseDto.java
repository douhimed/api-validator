package com.sqli.intern.api.validator.utils.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private String url;
    private String type;
    private String body;
    private String expectedResponse;
    private String httpStatus;
    private String currentResponse;
}
