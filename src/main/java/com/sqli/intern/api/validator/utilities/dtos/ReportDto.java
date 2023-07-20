package com.sqli.intern.api.validator.utilities.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReportDto {
    private String operation;
    private String path;
    private String value;

    public static ReportDto createErrorMessage(String errorMsg) {
        return ReportDto.builder()
                .operation("")
                .path("")
                .value(errorMsg)
                .build();
    }
}
