package com.application.library.desktop.supplier;

import com.application.library.desktop.exceptions.AuthorizationException;
import com.application.library.desktop.exceptions.RequestNotSuccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.http.HttpResponse;

public class RequestSupplier {

    public static HttpResponse<String> executeRequest(HttpResponse<String> response) {
        int statusCode = response.statusCode();
        if (is2xxSuccessful(statusCode)) return response;

        if (statusCode == 401) throw new AuthorizationException();

        throw new RequestNotSuccessException(response);
    }

    private static boolean is2xxSuccessful(int statusCode) {
        return statusCode >= 200 && statusCode < 300;
    }
}
