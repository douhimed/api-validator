package com.sqli.intern.api.validator.chainofvalidation;

import com.sqli.intern.api.validator.dtos.ResponseDto;;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public abstract class ApiCaller extends ApiValidationHandler implements Caller {
    public static final RestTemplate restTemplate = new RestTemplate();

    public abstract HttpMethod getType();

    public abstract String getBody(ResponseDto responseDto);


    @Override
    public void call(ResponseDto responseDto) {
        invoke(responseDto);
    }

    @Override
    public void invoke(ResponseDto responseDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> requestEntity = new HttpEntity<>(getBody(responseDto), headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(responseDto.getUrl(),
                getType(),
                requestEntity,
                String.class);
        responseDto.setHttpStatus(String.valueOf(responseEntity.getStatusCode().value()));
        responseDto.setExpectedResponse(responseEntity.getBody());

        invokeNext(responseDto);
    }
}
