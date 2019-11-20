package com.ftu.gateway.common;

public class ErrorCode {

    public static final String ERROR_100 = "JWT token invalid or expired";
    public static final String ERROR_101 = "Permission of Account has changed, please login again";
    public static final String ERROR_102 = "IP Policy of account has changed, please login again";
    public static final String ERROR_103 = "Schedule Policy of account has changed, please login again";
    public static final String ERROR_104 = "Request has not permission to access URL";

    public static final String ERROR_999 = "Unknow error";
}
