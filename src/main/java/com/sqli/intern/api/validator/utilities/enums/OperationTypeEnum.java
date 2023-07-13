package com.sqli.intern.api.validator.utilities.enums;

public enum OperationTypeEnum {
    GET, POST, DELETE, PUT;

    public static boolean isTypeGet(String type) {
        return GET.name().equalsIgnoreCase(type);
    }

    public static boolean isTypePost(String type) {
        return POST.name().equalsIgnoreCase(type);
    }

    public static boolean isTypePut(String type) {
        return PUT.name().equalsIgnoreCase(type);
    }


    public static boolean isTypeDelete(String type) {
        return DELETE.name().equalsIgnoreCase(type);
    }

}
