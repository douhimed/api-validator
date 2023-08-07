package com.sqli.intern.api.validator.utilities.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {
    @JsonProperty("op")
    private String operation;
    @JsonProperty("path")
    private String path;
    @JsonProperty("value")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object value;


    public static ReportDto createErrorMessage(String errorMsg) {
        return ReportDto.builder()
                .value(ValueDto.builder()
                        .msg(errorMsg)
                        .build()
                        .toString())
                .build();
    }
}
