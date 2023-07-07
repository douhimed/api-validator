package com.sqli.intern.api.validator.services;

import com.sqli.intern.api.validator.dtos.RequestDto;
import com.sqli.intern.api.validator.dtos.ResponseDto;

public interface OperationService {
    ResponseDto call(RequestDto requestDto);
}
