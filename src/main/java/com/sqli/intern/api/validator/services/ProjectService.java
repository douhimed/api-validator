package com.sqli.intern.api.validator.services;

import com.sqli.intern.api.validator.utilities.dtos.ProjectDto;

import java.util.List;

public interface ProjectService {
    List<ProjectDto> getAllProjects();

    ProjectDto getProjectById(Long id);

    Long addProject(String name);

    Long updateProject(Long id, String name);

    Long deleteProject(Long id);
}
