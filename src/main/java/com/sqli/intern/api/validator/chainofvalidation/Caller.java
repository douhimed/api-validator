package com.sqli.intern.api.validator.chainofvalidation;

import com.sqli.intern.api.validator.utils.dtos.ResponseDto;
import org.springframework.stereotype.Component;

@Component
public interface Caller {
    void call(ResponseDto responseDto);

}
