package com.application.library.desktop.constants;

import java.util.List;

public class RequestPathConstants {
    public static final List<String> PUBLIC_PATHS = List.of(
            RequestPathConstants.REGISTER_PATH,
            RequestPathConstants.LOGIN_PATH
    );

    public static final String REGISTER_PATH = "/auth/register";
    public static final String LOGIN_PATH = "/auth/login";
    public static final String SAVE_USER_PATH = "/api/users";
}
