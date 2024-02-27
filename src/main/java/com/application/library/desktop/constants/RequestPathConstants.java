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
    public static final String LEND_DAY_SETTINGS = "/api/settings/lend/day";
    public static final String LATE_FEE_SETTINGS = "/api/settings/late/fee";
    public static final String GET_ALL_SHELVES = "/api/shelves";
    public static final String SAVE_SHELVES = "/api/shelves";
    public static final String UPDATE_SHELF_BY_ID = "/api/shelves/{0}";
    public static final String DELETE_SHELF_BY_ID = "/api/shelves/{0}";
    public static final String GET_ALL_BOOKS_BY_SHELF_ID = "/api/books/shelf/{0}";
    public static final String GET_ALL_BOOKS = "/api/books";
    public static final String SAVE_BOOKS = "/api/books";
    public static final String GET_BOOK_BY_ID = "/api/books/{0}";
    public static final String DELETE_BOOK_BY_ID = "/api/books/{0}";
    public static final String UPDATE_BOOK_BY_ID = "/api/books/{0}";
    public static final String MOVE_BOOK = "/api/books/move/{0}";
}
