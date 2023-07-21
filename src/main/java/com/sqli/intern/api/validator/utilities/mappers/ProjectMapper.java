package com.sqli.intern.api.validator.utilities.mappers;

import com.sqli.intern.api.validator.entities.ProjectEntity;
import com.sqli.intern.api.validator.utilities.dtos.ProjectDto;

public final class ProjectMapper {

    private ProjectMapper() {
        throw new RuntimeException("INSTANTIATION NOT ALLOWED");
    }

    public static ProjectDto map(ProjectEntity projectEntity) {
        return ProjectDto.builder()
                .id(projectEntity.getId())
                .name(projectEntity.getName())
                .operationDtos(OperationMapper.map(projectEntity.getOperations()))
                .build();
    }

    public static ProjectEntity from(ProjectDto projectDto) {
        return ProjectEntity.builder()
                .id(projectDto.getId())
                .name(projectDto.getName())
                .build();
    }
}
