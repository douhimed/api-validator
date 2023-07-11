package com.sqli.intern.api.validator.client.rest;

import com.sqli.intern.api.validator.utilities.dtos.ProjectDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProjectRestController {

    @GetMapping(path = "projects")
    public ResponseEntity<List<ProjectDto>> getAllProjects() {
        List<ProjectDto> projectDto = new ArrayList<>();

        return new ResponseEntity<List<ProjectDto>>(projectDto, HttpStatus.OK);
    }
}
