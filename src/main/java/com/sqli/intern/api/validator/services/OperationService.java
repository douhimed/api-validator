package com.sqli.intern.api.validator.services;

import com.sqli.intern.api.validator.authentication.AuthHeaderProvider;
import com.sqli.intern.api.validator.utilities.dtos.OperationDto;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;
import com.sqli.intern.api.validator.utilities.enums.ValidationStatus;

import java.util.List;

public interface OperationService {
    ValidationStatus compareJson(OperationDto operationDto);


    ResponseDto runTest(OperationDto operationDto) throws InstantiationException;
    ResponseDto runTest(OperationDto operationDto, AuthHeaderProvider authHeaderProvider) throws InstantiationException;

    List<OperationDto> getAllOperations();

    OperationDto getOperationById(Long id);

    Long addOperation(OperationDto operationDto);

    Long updateOperation(Long id, OperationDto operationDto);
    Long updateExpectedResponse(Long id, String newExpectedResponse);

    Long deleteOperation(Long id);

}
