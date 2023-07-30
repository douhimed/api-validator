package com.sqli.intern.api.validator.utilities.mappers;

import com.sqli.intern.api.validator.entities.OperationEntity;
import com.sqli.intern.api.validator.entities.ProjectEntity;
import com.sqli.intern.api.validator.utilities.dtos.OperationDto;

import java.util.List;
import java.util.stream.Collectors;

public final class OperationMapper {
    private OperationMapper() {
        throw new RuntimeException("INSTANTIATION NOT ALLOWED");
    }

    public static OperationDto map(OperationEntity operationEntity) {
        return OperationDto.builder()
                .id(operationEntity.getId())
                .url(operationEntity.getUrl())
                .body(operationEntity.getBody())
                .type(operationEntity.getType())
                .actualResponse(operationEntity.getActualResponse())
                .expectedResponse(operationEntity.getExpectedResponse())
                .expectedType(operationEntity.getExpectedType())
                .build();
    }

    public static List<OperationDto> map(List<OperationEntity> operationEntities) {
        return operationEntities.stream()
                .map(OperationMapper::map)
                .collect(Collectors.toList());
    }

    public static OperationEntity from(OperationDto operationDto) {
        return OperationEntity.builder()
                .url(operationDto.getUrl())
                .body(operationDto.getBody())
                .type(operationDto.getType())
                .expectedResponse(operationDto.getExpectedResponse())
                .actualResponse(operationDto.getActualResponse())
                .expectedType(operationDto.getExpectedType())
                .project(ProjectEntity.builder().id(operationDto.getProjectId()).build())
                .build();
    }

    public static List<OperationEntity> from(List<OperationDto> operationDtos) {
        return operationDtos.stream()
                .map(OperationMapper::from)
                .collect(Collectors.toList());
    }

    public static void updateOperationEntity(OperationDto operationDto, OperationEntity operationEntity) {
        operationEntity.setUrl(operationDto.getUrl());
        operationEntity.setBody(operationDto.getBody());
        operationEntity.setType(operationDto.getType());
        operationEntity.setExpectedType(operationDto.getExpectedType());
        operationEntity.setExpectedResponse(operationDto.getExpectedResponse());
        operationEntity.setActualResponse(operationDto.getActualResponse());
    }
}
