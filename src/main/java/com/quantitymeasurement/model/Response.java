package com.quantitymeasurement.model;

import java.time.LocalDateTime;

public class Response {

    private int status;
    private String message;
    private LocalDateTime timestamp;
    private Object result;

    public Response(Object result, String message, int status, LocalDateTime timestamp) {
        this.result = result;
        this.message = message;
        this.status = status;
        this.timestamp = timestamp;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Response() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

}
