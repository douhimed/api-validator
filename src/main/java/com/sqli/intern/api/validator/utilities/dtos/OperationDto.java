package com.sqli.intern.api.validator.utilities.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OperationDto {
    private String url;
    private String type;
    private String body;
    private String expectedResponse;
    private String actualResponse;
    private String expectedType;
    private Long projectId;

}
