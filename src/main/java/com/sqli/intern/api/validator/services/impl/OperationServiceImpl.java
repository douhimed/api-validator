package com.sqli.intern.api.validator.services.impl;

import com.sqli.intern.api.validator.chainOfValidation.JsonComparator;
import com.sqli.intern.api.validator.dto.RequestDto;
import com.sqli.intern.api.validator.dto.ResponseDto;
import com.sqli.intern.api.validator.services.OperationService;
import com.sqli.intern.api.validator.utils.ValidationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService {


    @Autowired
    private JsonComparator jsonComparator;

    @Override
    public ValidationStatus compareJson(RequestDto requestDto) {
        final ResponseDto responseDto = ResponseDto.builder()
                .expectedResponse(requestDto.getExpectedResponse())
                .actualResponse(requestDto.getActualResponse())
                .build();
        jsonComparator.compareJson(responseDto);
        return responseDto.getValidationStatus();
    }
}
