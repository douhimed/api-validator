package com.sqli.intern.api.validator.utilities.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProjectExceptionHandler {
    @ExceptionHandler(value = {ProjectException.class})
    public ResponseEntity<Object> handleProjectException(ProjectException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        projectExceptionFields projectExceptionFields = new projectExceptionFields(
                e.getMessage(),
                badRequest
        );
        return new ResponseEntity<>(projectExceptionFields, badRequest);
    }
}
