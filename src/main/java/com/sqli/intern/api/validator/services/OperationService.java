package com.sqli.intern.api.validator.services;

import com.sqli.intern.api.validator.utils.dtos.RequestDto;
import com.sqli.intern.api.validator.utils.dtos.ResponseDto;

public interface OperationService {
    ResponseDto call(RequestDto requestDto);
}
