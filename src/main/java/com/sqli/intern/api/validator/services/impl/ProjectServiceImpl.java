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
import java.util.Optional;
import java.util.stream.Collectors;

import static com.sqli.intern.api.validator.utilities.enums.ExceptionMessageEnum.NAME_ALREADY_EXIST;
import static com.sqli.intern.api.validator.utilities.enums.ExceptionMessageEnum.NULL_PROJECT;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<ProjectDto> getAllProjects() {
        List<ProjectDto> projectDtos = projectRepository.findAll()
                .stream()
                .map(ProjectMapper::map)
                .collect(Collectors.toList());
        return projectDtos;
    }

    @Override
    public ProjectDto getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(ProjectMapper::map)
                .orElseThrow(() -> new ProjectException(NULL_PROJECT));
    }

    @Override
    public Long addProject(ProjectDto projectDto) {

        Optional<ProjectEntity> checkName = projectRepository.findByName(projectDto.getName());
        if (checkName.isPresent()) {
            throw new ProjectException(NAME_ALREADY_EXIST);
        }

        ProjectEntity projectEntity = ProjectMapper.from(projectDto);

        ProjectEntity savedProject = projectRepository.save(projectEntity);
        return savedProject.getId();
    }

    @Override
    public Long updateProject(Long id, ProjectDto projectDto) {

        Optional<ProjectEntity> checkName = projectRepository.findByName(projectDto.getName());
        if (checkName.isPresent()) {
            throw new ProjectException(NAME_ALREADY_EXIST);
        }

        return projectRepository.findById(id)
                .map(entity -> {
                    entity.setName(projectDto.getName());
                    ProjectEntity updatedProject = projectRepository.save(entity);
                    return updatedProject.getId();
                })
                .orElseThrow(() -> new ProjectException(NULL_PROJECT));
    }

    @Override
    public Long deleteProject(Long id) {

        return projectRepository.findById(id)
                .map(entity -> {
                    projectRepository.delete(entity);
                    return id;
                })
                .orElseThrow(() -> new ProjectException(NULL_PROJECT));
    }
}
