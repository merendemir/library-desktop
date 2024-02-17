package com.application.library.desktop.supplier;

import com.application.library.desktop.exceptions.AuthorizationException;
import com.application.library.desktop.exceptions.RequestNotSuccessException;

import java.net.http.HttpResponse;

public class Supplier {

    public static void run(TaskSupplier task) {
        task.run();
    }

    public static HttpResponse<String> executeRequest(HttpResponse<String> response) {
        int statusCode = response.statusCode();
        System.out.println("Status code: " + statusCode);
        if (is2xxSuccessful(statusCode)) return response;

        if (statusCode == 401) throw new AuthorizationException();

        throw new RequestNotSuccessException(response);
    }

    private static boolean is2xxSuccessful(int statusCode) {
        return statusCode >= 200 && statusCode < 300;
    }
}
