package com.sqli.intern.api.validator.services.impl;

import com.sqli.intern.api.validator.entities.ProjectEntity;
import com.sqli.intern.api.validator.repositories.ProjectRepository;
import com.sqli.intern.api.validator.services.ProjectService;
import com.sqli.intern.api.validator.utilities.dtos.ProjectDto;
import com.sqli.intern.api.validator.utilities.exceptions.ProjectException;
import com.sqli.intern.api.validator.utilities.mappers.ProjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.sqli.intern.api.validator.utilities.enums.ExceptionMessageEnum.NAME_ALREADY_EXIST;
import static com.sqli.intern.api.validator.utilities.enums.ExceptionMessageEnum.PROJECT_NOT_FOUND;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAllByDeletedFalse()
                .stream()
                .map(ProjectMapper::mapToGetAll)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto getProjectById(Long id) {
        return projectRepository.findByIdAndDeletedFalse(id)
                .map(ProjectMapper::map)
                .orElseThrow(() -> new ProjectException(PROJECT_NOT_FOUND));
    }


    @Override
    public Long addProject(ProjectDto projectDto) {
        checkIfNameAlreaduExist(projectDto);
        ProjectEntity projectEntity = ProjectMapper.from(projectDto);
        return projectRepository.save(projectEntity).getId();
    }

    @Override
    public Long updateProject(Long id, ProjectDto projectDto) {
        ProjectEntity project = getProjectEntityOrThrowsExceptionIfNotFound(id);
        project.setName(projectDto.getName());
        projectRepository.save(project);
        return id;
    }

    @Override
    public Long deleteProject(Long id) {
        ProjectEntity project = getProjectEntityOrThrowsExceptionIfNotFound(id);
        project.setDeleted(true);
        projectRepository.save(project);
        return id;
    }

    @Override
    public ProjectEntity getProjectEntityOrThrowsExceptionIfNotFound(Long id) {
        ProjectEntity project = projectRepository.findById(id)
                .orElseThrow(() -> new ProjectException(PROJECT_NOT_FOUND));
        return project;
    }

    private void checkIfNameAlreaduExist(ProjectDto projectDto) {
        projectRepository.findByName(projectDto.getName())
                .ifPresent(p -> {
                    throw new ProjectException(NAME_ALREADY_EXIST);
                });

    }

}
