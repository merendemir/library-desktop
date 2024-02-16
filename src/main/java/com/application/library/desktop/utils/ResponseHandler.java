package com.application.library.desktop.utils;

public class ResponseHandler<T> {

    private T data;
    private String timestamp;
    private String errorMessage;

    public ResponseHandler() {
    }

    public ResponseHandler(T data, String timestamp) {
        this.data = data;
        this.timestamp = timestamp;
    }

    public ResponseHandler(String timestamp, String errorMessage) {
        this.timestamp = timestamp;
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }


    public String getTimestamp() {
        return timestamp;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
