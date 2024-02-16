package com.application.library.desktop.utils;

public class ErrorResponseHandler {
    private String errorMessage;
    private String timestamp;

    public ErrorResponseHandler() {
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
