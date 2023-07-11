package com.sqli.intern.api.validator.core.impl;

import com.sqli.intern.api.validator.core.RestCaller;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component

public abstract class RestHandler extends OperationHandler implements RestCaller {

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    private OperationHandler jsonHandler;

    public abstract HttpMethod getType();

    public abstract HttpEntity getBody(ResponseDto responseDto);


    @Override
    public void call(ResponseDto responseDto) {
        invoke(responseDto);
    }

    @Override
    public void invoke(ResponseDto responseDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<String> responseEntity = restTemplate.exchange(responseDto.getUrl(),
                getType(),
                getBody(responseDto),
                String.class);
        responseDto.setHttpStatus(String.valueOf(responseEntity.getStatusCode().value()));
        responseDto.setActualResponse(responseEntity.getBody());
        //jsonHandler.compareJson(responseDto);
        invokeNext(responseDto);
    }


    @PostConstruct
    public void initNext() {
        super.setNext(jsonHandler);
    }

}
