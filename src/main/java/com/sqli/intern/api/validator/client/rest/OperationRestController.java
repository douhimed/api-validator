package com.sqli.intern.api.validator.client.rest;

import com.sqli.intern.api.validator.services.OperationService;
import com.sqli.intern.api.validator.utilities.dtos.OperationDto;
import com.sqli.intern.api.validator.utilities.enums.ValidationStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Tag(name = "Operation Rest Controller", description = "Endpoints related to API Validation")
public class OperationRestController {
    @Autowired
    private OperationService operationService;

    @PostMapping("/validator")
    @Operation(summary = "JSON validator", description = "Validates a JSON request compares the expect json with the actual json")
    @ApiResponse(responseCode = "200", description = "Validation status",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ValidationStatus.class)))
    public ValidationStatus jsonValidator(@RequestBody OperationDto operationDto) {
        return operationService.compareJson(operationDto);
    }

    @GetMapping("/operation")
    public ResponseEntity<List<OperationDto>> getAllOperations() {
        List<OperationDto> operationDto = operationService.getAllOperations();
        return new ResponseEntity<>(operationDto, HttpStatus.OK);
    }

    @GetMapping(path = "/operation/{id}")
    public ResponseEntity<OperationDto> getOperationById(@PathVariable Long id) {
        OperationDto operationDto = operationService.getOperationById(id);
        return new ResponseEntity<>(operationDto, HttpStatus.OK);
    }

    @PostMapping("/operation")
    public ResponseEntity<Long> addOperation(@RequestBody OperationDto operationDto) {
        Long operationId = operationService.addOperation(operationDto);
        return new ResponseEntity<>(operationId, HttpStatus.OK);
    }

    @PutMapping(path = "/operation/{id}")
    public ResponseEntity<Long> updateOperationById(@RequestBody OperationDto operationDto,
                                                    @PathVariable Long id) {
        Long operationId = operationService.updateOperation(id, operationDto);
        return new ResponseEntity<>(operationId, HttpStatus.OK);
    }

    @DeleteMapping(path = "/operation/{id}")
    public ResponseEntity<Long> deleteOperationById(@PathVariable Long id) {
        Long operationId = operationService.deleteOperation(id);
        return new ResponseEntity<>(operationId, HttpStatus.OK);
    }
}
