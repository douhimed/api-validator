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

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<ProjectDto> getAllProjects() {
        List<ProjectDto> projectDtos = projectRepository.findAll()
                .stream()
                .map(entity -> {
                    ProjectDto projectDto = ProjectMapper.map(entity);
                    return projectDto;
                })
                .collect(Collectors.toList());
        return projectDtos;
    }

    @Override
    public ProjectDto getProjectById(Long id) {
        return projectRepository.findById(id)
                .map(entity -> {
                    ProjectDto projectDto = ProjectMapper.map(entity);
                    return projectDto;
                })
                .orElseThrow(() -> new ProjectException("Project n'existe pas"));
    }

    @Override
    public Long addProject(ProjectDto projectDto) {

        Optional<ProjectEntity> checkName = projectRepository.findByName(projectDto.getName());
        if (checkName.isPresent()) {
            throw new ProjectException("Name Already Exist!");
        }

        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName(projectDto.getName());

        ProjectEntity savedProject = projectRepository.save(projectEntity);
        return savedProject.getId();
    }

    @Override
    public Long updateProject(Long id, ProjectDto projectDto) {

        Optional<ProjectEntity> checkName = projectRepository.findByName(projectDto.getName());
        if (checkName.isPresent()) {
            throw new ProjectException("Name Already Exist!");
        }

        Optional<ProjectEntity> optionalProjectEntity = projectRepository.findById(id);

        if (optionalProjectEntity.isPresent()) {
            ProjectEntity projectEntity = optionalProjectEntity.get();
            projectEntity.setName(projectDto.getName());
            ProjectEntity updatedProject = projectRepository.save(projectEntity);
            return updatedProject.getId();
        } else {
            throw new ProjectException("Project n'existe pas");
        }
    }

    @Override
    public Long deleteProject(Long id) {
        Optional<ProjectEntity> optionalProjectEntity = projectRepository.findById(id);

        if (optionalProjectEntity.isPresent()) {
            ProjectEntity projectEntity = optionalProjectEntity.get();
            projectRepository.delete(projectEntity);
        } else {
            throw new ProjectException("Project n'existe pas");
        }
        return id;
    }
}
