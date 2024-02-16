package com.application.library.desktop.utils;

public class ResponseHandler<T> {

    private T data;
    private String timestamp;

    public ResponseHandler() {
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

}
