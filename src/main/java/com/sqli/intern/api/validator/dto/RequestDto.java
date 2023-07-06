package com.sqli.intern.api.validator.dto;

import com.sqli.intern.api.validator.utils.ValidationStatus;
import lombok.Data;

import java.util.List;

@Data
public class RequestDto {
    private String expectedResponse;
    private String actualResponse;

}
