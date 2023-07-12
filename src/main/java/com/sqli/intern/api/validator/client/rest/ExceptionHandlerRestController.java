package com.sqli.intern.api.validator.client.rest;

import com.sqli.intern.api.validator.utilities.dtos.ExceptionMessageDto;
import com.sqli.intern.api.validator.utilities.exceptions.ProjectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerRestController {
    @ExceptionHandler(value = {ProjectException.class})
    public ResponseEntity<Object> handleProjectException(ProjectException e) {
        ExceptionMessageDto exceptionMessageDto = new ExceptionMessageDto(
                e.getMessage()
        );
        return new ResponseEntity<>(exceptionMessageDto, HttpStatus.BAD_REQUEST);
    }
}
