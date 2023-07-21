package com.sqli.intern.api.validator.services;

import com.sqli.intern.api.validator.utilities.dtos.OperationDto;
import com.sqli.intern.api.validator.utilities.dtos.ResponseDto;
import com.sqli.intern.api.validator.utilities.enums.ValidationStatus;

import java.util.List;

public interface OperationService {
    ValidationStatus compareJson(OperationDto operationDto);

    ResponseDto runTest(OperationDto operationDto);

    List<OperationDto> getAllOperations();

    OperationDto getOperationById(Long id);

    Long addOperation(OperationDto operationDto);

    Long updateOperation(Long id, OperationDto operationDto);

    Long deleteOperation(Long id);

}
