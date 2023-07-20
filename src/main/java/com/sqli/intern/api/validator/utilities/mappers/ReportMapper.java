package com.sqli.intern.api.validator.utilities.mappers;

import com.fasterxml.jackson.databind.JsonNode;
import com.sqli.intern.api.validator.utilities.dtos.ReportDto;

public final class ReportMapper {

    private ReportMapper() {throw new RuntimeException("INSTANTIATION NOT ALLOWED");}

        public static ReportDto map(JsonNode node) {
            String operation = node.get("op").toString();
            String path = node.get("path").toString();
            String value = node.get("value").toString();

            return ReportDto.builder()
                    .operation(operation)
                    .path(path)
                    .value(value)
                    .build();
        }

}
