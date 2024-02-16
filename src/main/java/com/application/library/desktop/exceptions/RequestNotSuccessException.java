package com.application.library.desktop.exceptions;

import java.net.http.HttpResponse;

public class RequestNotSuccessException extends RuntimeException {
    private final HttpResponse<String> response;

    public RequestNotSuccessException(HttpResponse<String> response) {
        this.response = response;
    }

    public HttpResponse<String> getResponse() {
        return response;
    }
}
