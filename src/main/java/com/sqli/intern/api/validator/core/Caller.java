package com.sqli.intern.api.validator.core;

import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;
import org.springframework.stereotype.Component;

@Component
public interface Caller {
    void call(ResponseDto responseDto);

}
