package com.sqli.intern.api.validator.services.impl;

import com.sqli.intern.api.validator.core.JsonComparator;
import com.sqli.intern.api.validator.core.StrategyWs;
import com.sqli.intern.api.validator.services.OperationService;
import com.sqli.intern.api.validator.utilities.dtos.RequestDto;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;
import com.sqli.intern.api.validator.utils.ValidationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private StrategyWs strategyWs;

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

    @Override
    public ResponseDto call(RequestDto requestDto) {
        final ResponseDto responseDto = ResponseDto.builder()
                .url(requestDto.getUrl())
                .type(requestDto.getType())
                .body(requestDto.getBody())
                .expectedResponse(requestDto.getExpectedResponse())
                .build();
        strategyWs.getCaller(requestDto.getType()).call(responseDto);
        return responseDto;
    }

}
