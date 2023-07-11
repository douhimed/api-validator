package com.sqli.intern.api.validator.services.impl;

import com.sqli.intern.api.validator.entities.ProjectEntity;
import com.sqli.intern.api.validator.repositories.ProjectRepository;
import com.sqli.intern.api.validator.services.ProjectService;
import com.sqli.intern.api.validator.utilities.dtos.ProjectDto;
import com.sqli.intern.api.validator.utilities.exceptions.ProjectException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<ProjectDto> getAllProjects() {
        List<ProjectDto> projectDto = new ArrayList<>();

        List<ProjectEntity> projectEntity = projectRepository.findAll();
        for (int i = 0; i < projectEntity.size(); i++) {
            ProjectDto project = new ProjectDto();
            BeanUtils.copyProperties(projectEntity.get(i), project);
            projectDto.add(project);
        }
        return projectDto;
    }

    @Override
    public ProjectDto getProjectById(Long id) {
        Optional<ProjectEntity> optionalProjectEntity = projectRepository.findById(id);
        if (optionalProjectEntity.isPresent()) {
            ProjectDto projectDto = new ProjectDto();
            BeanUtils.copyProperties(optionalProjectEntity.get(), projectDto);
            return projectDto;
        } else {
            throw new ProjectException("Project n'existe pas");
        }
    }

    @Override
    public Long addProject(String name) {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setName(name);

        Optional<ProjectEntity> checkName = projectRepository.findByName(name);
        if (checkName.isPresent()) {
            throw new ProjectException("Name Already Exist!");
        }

        ProjectEntity savedProject = projectRepository.save(projectEntity);
        return savedProject.getId();
    }

    @Override
    public Long updateProject(Long id, String name) {
        Optional<ProjectEntity> optionalProjectEntity = projectRepository.findById(id);

        if (optionalProjectEntity.isPresent()) {
            ProjectEntity projectEntity = optionalProjectEntity.get();
            projectEntity.setName(name);
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
