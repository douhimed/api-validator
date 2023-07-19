package com.sqli.intern.api.validator.client.rest;

import com.sqli.intern.api.validator.services.OperationService;
import com.sqli.intern.api.validator.utilities.dtos.OperationDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/operation")
@Tag(name = "Operation Rest Controller", description = "Endpoints related to API Validation")
public class OperationRestController {
    @Autowired
    private OperationService operationService;

    @GetMapping
    public ResponseEntity<List<OperationDto>> getAllOperations() {
        List<OperationDto> operationDto = operationService.getAllOperations();
        return new ResponseEntity<>(operationDto, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<OperationDto> getOperationById(@PathVariable Long id) {
        OperationDto operationDto = operationService.getOperationById(id);
        return new ResponseEntity<>(operationDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Long> addOperation(@RequestBody OperationDto operationDto) {
        Long operationId = operationService.addOperation(operationDto);
        return new ResponseEntity<>(operationId, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Long> updateOperationById(@RequestBody OperationDto operationDto,
                                                    @PathVariable Long id) {
        Long operationId = operationService.updateOperation(id, operationDto);
        return new ResponseEntity<>(operationId, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Long> deleteOperationById(@PathVariable Long id) {
        Long operationId = operationService.deleteOperation(id);
        return new ResponseEntity<>(operationId, HttpStatus.OK);
    }
}
