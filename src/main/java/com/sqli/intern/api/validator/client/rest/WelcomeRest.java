package com.sqli.intern.api.validator.client.rest;

import com.sqli.intern.api.validator.services.OperationService;
import com.sqli.intern.api.validator.utilities.dtos.RequestDto;
import com.sqli.intern.api.validator.utilities.enums.ValidationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class WelcomeRest {
    @Autowired
    private OperationService operationService;

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome";
    }

    @PostMapping("/validator")
    public ValidationStatus jsonValidator(@RequestBody RequestDto requestDto) {
        return operationService.compareJson(requestDto);
    }

}
