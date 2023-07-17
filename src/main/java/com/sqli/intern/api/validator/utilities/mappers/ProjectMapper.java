package com.sqli.intern.api.validator.utilities.mappers;

import com.sqli.intern.api.validator.entities.ProjectEntity;
import com.sqli.intern.api.validator.utilities.dtos.ProjectDto;

public final class ProjectMapper {

    private ProjectMapper() {
        throw new RuntimeException("INSTANTIATION NOT ALLOWED");
    }

    public static ProjectDto map(ProjectEntity projectEntity) {
        return ProjectDto.builder()
                .id(projectEntity.getProjectId())
                .name(projectEntity.getName())
                .operationDto(OperationMapper.fromOperationEntities(projectEntity.getOperationEntities()))
                .build();
    }

    public static ProjectDto mapToGetAll(ProjectEntity projectEntity) {
        return ProjectDto.builder()
                .id(projectEntity.getProjectId())
                .name(projectEntity.getName())
                .build();
    }

    public static ProjectEntity from(ProjectDto projectDto) {
        return ProjectEntity.builder()
                .projectId(projectDto.getId())
                .name(projectDto.getName())
                .build();
    }
}
