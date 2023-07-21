package com.sqli.intern.api.validator.utilities.mappers;

import com.fasterxml.jackson.databind.JsonNode;
import com.sqli.intern.api.validator.utilities.dtos.ReportDto;

public final class ReportMapper {

    private ReportMapper() {throw new RuntimeException("INSTANTIATION NOT ALLOWED");}

        public static ReportDto map(JsonNode node) {
            String operation = node.has("op") ? node.get("op").asText() : null;
            String path = node.has("path") ? node.get("path").asText() : null;
            String value = node.has("value") ? node.get("value").toString() : null;

            return ReportDto.builder()
                    .operation(operation)
                    .path(path)
                    .value(value)
                    .build();
        }

}
