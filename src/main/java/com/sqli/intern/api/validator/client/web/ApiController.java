package com.sqli.intern.api.validator.client.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ApiController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }
}


