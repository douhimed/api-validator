package com.sqli.intern.api.validator.services;

import com.sqli.intern.api.validator.utilities.dtos.OperationDto;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;
import com.sqli.intern.api.validator.utilities.enums.ValidationStatus;

public interface OperationService {
    ValidationStatus compareJson(OperationDto operationDto);

    ResponseDto call(OperationDto operationDto);
}
