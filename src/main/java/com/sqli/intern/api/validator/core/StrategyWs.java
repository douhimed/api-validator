package com.sqli.intern.api.validator.core;

import com.sqli.intern.api.validator.utilities.enums.ExceptionMessageEnum;
import com.sqli.intern.api.validator.utilities.enums.OperationTypeEnum;
import com.sqli.intern.api.validator.utilities.exceptions.OperationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StrategyWs {

    @Autowired
    private Caller getRequestHandler;

    public Caller getCaller(String type) {
        if (OperationTypeEnum.isTypeGet(type))
            return getRequestHandler;
        throw new OperationException(ExceptionMessageEnum.OPERATION_NOT_ELIGIBLE.getMessage());
    }

}
