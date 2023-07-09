package com.sqli.intern.api.validator.core;

import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public abstract class ApiCaller extends ApiValidationHandler implements Caller {

    public static final RestTemplate REST_TEMPLATE = new RestTemplate();

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
        ResponseEntity<String> responseEntity = REST_TEMPLATE.exchange(responseDto.getUrl(),
                getType(),
                getBody(responseDto),
                String.class);
        responseDto.setHttpStatus(String.valueOf(responseEntity.getStatusCode().value()));
        responseDto.setCurrentResponse(responseEntity.getBody());
        invokeNext(responseDto);
    }
}
