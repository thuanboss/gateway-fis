package com.ftu.gateway.common;

import java.util.Date;

public class ErrorResponse {
    private Date timestamp;
    private int code;
    private String message;
    private String details;

    public ErrorResponse(Date timestamp, int code, String message, String details) {
        super();
        this.timestamp = timestamp;
        this.code = code;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public int getCode() {
        return code;
    }
}
