package com.sqli.intern.api.validator.client.rest;

import com.sqli.intern.api.validator.services.OperationService;
import com.sqli.intern.api.validator.utilities.dtos.RequestDto;
import com.sqli.intern.api.validator.utilities.enums.ValidationStatus;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


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
    public ValidationStatus jsonValidator(@RequestBody RequestDto requestDto) {
        return operationService.compareJson(requestDto);
    }

}
