package com.application.library.desktop.constants;

import javax.swing.*;
import java.util.Objects;

public class IconConstants {
    private static final String ICONS_FOLDER = "/icons/";
    private static ImageIcon ICON_OF(String iconName) {
        return new ImageIcon(Objects.requireNonNull(IconConstants.class.getResource(ICONS_FOLDER + iconName)));
    }

    public static final ImageIcon APPLICATION_BACKGROUND = new ImageIcon(Objects.requireNonNull(IconConstants.class.getResource("/image/login-background_icon.png")));


    public static final ImageIcon APP_ICON = ICON_OF("application-icon-48px.png");
    public static final ImageIcon UPDATE_ACCOUNT_ICON = ICON_OF("user-update.png");
    public static final ImageIcon DELETE_USER_ICON = ICON_OF("delete-user.png");
    public static final ImageIcon LOGOUT_ICON = ICON_OF("logout.png");
    public static final ImageIcon USER_ICON = ICON_OF("user.png");
    public static final ImageIcon SEARCH_ICON = ICON_OF("search.png");
    public static final ImageIcon CLEAR_ICON = ICON_OF("clear.png");
    public static final ImageIcon WARNING_ICON = ICON_OF("warning.png");
    public static final ImageIcon SETTINGS_ICON = ICON_OF("settings.png");

    //MENU ICONS
    public static final ImageIcon MENU_ICON = ICON_OF("menu.png");
    public static final ImageIcon MENU_BOOK_MANAGEMENT_ICON = ICON_OF("menu-book-management.png");
    public static final ImageIcon MENU_BOOK_ICON = ICON_OF("menu-books.png");
    public static final ImageIcon MENU_ADD_BOOK_ICON = ICON_OF("menu-add-books.png");
    public static final ImageIcon MENU_USER_MANAGEMENT_ICON = ICON_OF("menu-user-management.png");
    public static final ImageIcon MENU_USERS_ICON = ICON_OF("menu-users.png");
    public static final ImageIcon MENU_ADD_USER_ICON = ICON_OF("menu-add-user.png");
    public static final ImageIcon MENU_ADD_SHELF_ICON = ICON_OF("add-shelf.png");
    public static final ImageIcon MENU_SHELF_MANAGEMENT_ICON = ICON_OF("shelf-management.png");
    public static final ImageIcon MENU_SHELVES_ICON = ICON_OF("shelves.png");
    public static final ImageIcon MENU_DELETE_ICON = ICON_OF("delete.png");
    public static final ImageIcon MENU_UPDATE_ICON = ICON_OF("update.png");
    public static final ImageIcon MENU_MOVE_ICON = ICON_OF("move.png");

    //STREAM ICONS
    public static final ImageIcon NEXT_ICON = ICON_OF("next.png");
    public static final ImageIcon PREVIOUS_ICON = ICON_OF("previous.png");
    public static final ImageIcon LAST_ICON = ICON_OF("last.png");
    public static final ImageIcon FIRST_ICON = ICON_OF("first.png");

}
