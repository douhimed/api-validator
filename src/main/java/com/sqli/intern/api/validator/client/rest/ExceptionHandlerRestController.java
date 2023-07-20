package com.sqli.intern.api.validator.client.rest;

import com.sqli.intern.api.validator.utilities.dtos.ExceptionMessageDto;
import com.sqli.intern.api.validator.utilities.exceptions.OperationException;
import com.sqli.intern.api.validator.utilities.exceptions.ProjectException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.function.Function;

@ControllerAdvice
public class ExceptionHandlerRestController {

    private final Function<String, ResponseEntity<Object>> HANDLE_EXCEPTION = message -> new ResponseEntity<>(new ExceptionMessageDto(message), HttpStatus.BAD_REQUEST);

    @ExceptionHandler(value = {ProjectException.class})
    public ResponseEntity<Object> handleProjectException(ProjectException e) {
        return HANDLE_EXCEPTION.apply(e.getMessage());
    }

    @ExceptionHandler(value = {OperationException.class})
    public ResponseEntity<Object> handleOperationException(OperationException operationException) {
        return HANDLE_EXCEPTION.apply(operationException.getMessage());
    }

}

