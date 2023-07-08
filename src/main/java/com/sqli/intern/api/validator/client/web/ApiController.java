package com.sqli.intern.api.validator.client.web;

import com.sqli.intern.api.validator.utilities.dtos.RequestDto;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;
import com.sqli.intern.api.validator.services.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
public class ApiController {

    @Autowired
    private OperationService operationService;

    @GetMapping("/home")
    public String home(ModelMap model) {
        model.addAttribute("requestDto", new RequestDto());
        return "home";
    }

    @PostMapping("/home")
    public String handleApiRequest(@ModelAttribute RequestDto requestDto, ModelMap model) {
        ResponseDto responseDto = operationService.call(requestDto);
        model.addAttribute("requestDto", requestDto);
        model.addAttribute("responseDto", responseDto);
        return "home";
    }

}


