package com.quantitymeasurement.model;

import com.quantitymeasurement.enumeration.Message;

public class Response {

    private int status;
    private Message message;
    private Object result;

    public Response(Object result, Message message, int status) {
        this.result = result;
        this.message = message;
        this.status = status;
    }

    public Response() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
