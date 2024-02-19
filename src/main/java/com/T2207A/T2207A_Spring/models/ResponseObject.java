package com.T2207A.T2207A_Spring.models;

public class ResponseObject {
    private String status;
    private String message;
    private Object data;

    public ResponseObject() {
    }

    public ResponseObject(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public ResponseObject setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseObject setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResponseObject setData(Object data) {
        this.data = data;
        return this;
    }
}
