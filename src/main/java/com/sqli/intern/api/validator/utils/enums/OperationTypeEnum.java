package com.sqli.intern.api.validator.utils.enums;

public enum OperationTypeEnum {
    GET, POST, DELETE, PUT;

    public static boolean isTypeGet(String type) {
        return GET.name().equalsIgnoreCase(type);
    }

}
