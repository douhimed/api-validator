package com.sqli.intern.api.validator.services;

import com.sqli.intern.api.validator.utilities.dtos.RequestDto;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;

public interface OperationService {
    ResponseDto call(RequestDto requestDto);
}
