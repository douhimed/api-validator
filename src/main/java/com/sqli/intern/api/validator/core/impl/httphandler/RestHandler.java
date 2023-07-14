package com.sqli.intern.api.validator.core.impl.httphandler;

import com.sqli.intern.api.validator.core.RestCaller;
import com.sqli.intern.api.validator.core.impl.OperationHandler;
import com.sqli.intern.api.validator.core.impl.jsonhandler.JsonHandler;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;;
import com.sqli.intern.api.validator.utilities.enums.ExceptionMessageEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public abstract class RestHandler extends OperationHandler implements RestCaller {

    @Autowired
    public RestTemplate restTemplate;

    @Autowired
    protected JsonHandler commandValidator;

    @Autowired
    protected JsonHandler queryValidator;

    public abstract HttpMethod getType();

    public abstract HttpEntity getBody(ResponseDto responseDto);

    @Override
    public void call(ResponseDto responseDto) {
        invoke(responseDto);
    }

    @Override
    public void invoke(ResponseDto responseDto) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            ResponseEntity<String> responseEntity = restTemplate.exchange(responseDto.getUrl().trim(),
                    getType(),
                    getBody(responseDto),
                    String.class);
            responseDto.setHttpStatus(String.valueOf(responseEntity.getStatusCode().value()));
            responseDto.setActualResponse(responseEntity.getBody());
            invokeNext(responseDto);
        } catch (HttpClientErrorException e) {
            responseDto.addMessage(e.getStatusCode().is5xxServerError()
                    ? ExceptionMessageEnum.SERVICE_NOT_FOUND.getMessage()
                    : ExceptionMessageEnum.BAD_REQUEST.getMessage());
        }
    }

}
