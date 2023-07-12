package com.sqli.intern.api.validator.client.rest;

import com.sqli.intern.api.validator.utilities.exceptions.ProjectException;
import com.sqli.intern.api.validator.utilities.exceptions.ProjectExceptionFields;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerRestController {
    @ExceptionHandler(value = {ProjectException.class})
    public ResponseEntity<Object> handleProjectException(ProjectException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ProjectExceptionFields projectExceptionFields = new ProjectExceptionFields(
                e.getMessage(),
                badRequest
        );
        return new ResponseEntity<>(projectExceptionFields, badRequest);
    }
}
