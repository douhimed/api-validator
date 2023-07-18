package com.sqli.intern.api.validator.utilities.mappers;

import com.sqli.intern.api.validator.entities.OperationEntity;
import com.sqli.intern.api.validator.utilities.dtos.OperationDto;

import java.util.List;
import java.util.stream.Collectors;

public final class OperationMapper {
    private OperationMapper() {
        throw new RuntimeException("INSTANTIATION NOT ALLOWED");
    }

    public static OperationDto fromOperationEntity(OperationEntity operationEntity) {
        return OperationDto.builder()
                .url(operationEntity.getUrl())
                .body(operationEntity.getBody())
                .type(operationEntity.getType())
                .actualResponse(operationEntity.getActualResponse())
                .expectedResponse(operationEntity.getExpectedResponse())
                .expectedType(operationEntity.getExpectedType())
                .build();
    }

    public static List<OperationDto> fromOperationEntities(List<OperationEntity> operationEntities) {
        return operationEntities.stream()
                .map(OperationMapper::fromOperationEntity)
                .collect(Collectors.toList());
    }

    public static OperationEntity fromOperationDto(OperationDto operationDto) {
        return OperationEntity.builder()
                .url(operationDto.getUrl())
                .body(operationDto.getBody())
                .type(operationDto.getType())
                .actualResponse(operationDto.getActualResponse())
                .expectedResponse(operationDto.getExpectedResponse())
                .expectedType(operationDto.getExpectedType())
                .build();
    }

    public static List<OperationEntity> fromOperationDtos(List<OperationDto> operationDtos) {
        return operationDtos.stream()
                .map(OperationMapper::fromOperationDto)
                .collect(Collectors.toList());
    }

}
