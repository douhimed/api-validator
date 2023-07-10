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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Tag(name = "Welcome Rest", description = "Endpoints related to API Validation")
public class WelcomeRest {
    @Autowired
    private OperationService operationService;

    @GetMapping("/welcome")
    @Operation(summary = "Welcome message", description = "Returns a welcome message")
    public String welcome() {
        return "Welcome";
    }

    @PostMapping("/validator")
    @Operation(summary = "JSON validator", description = "Validates a JSON request compares the expect json with the actual json")
    @ApiResponse(responseCode = "200", description = "Validation status",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ValidationStatus.class)))
    public ValidationStatus jsonValidator(@RequestBody RequestDto requestDto) {
        return operationService.compareJson(requestDto);
    }

}
