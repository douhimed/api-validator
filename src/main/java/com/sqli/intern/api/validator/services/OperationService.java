package com.sqli.intern.api.validator.services;

import com.sqli.intern.api.validator.dto.RequestDto;
import com.sqli.intern.api.validator.utils.ValidationStatus;

public interface OperationService {
    ValidationStatus compareJson(RequestDto requestDto);
}
