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
    public static final String GET_ALL_USERS = "/api/users";
    public static final String UPDATE_USER_BY_ID = "/api/users/{0}";
    public static final String UPDATE_ACTIVE_USER_INFO_PATH = "/api/users/active";
    public static final String GET_USER_BY_ID = "/api/users/{0}";
    public static final String DELETE_USER_BY_ID = "/api/users/{0}";
}
