package com.application.library.desktop.enumerations;

public enum MenuOptions {
    HOME("Menu", "homeCard"),
    BOOK_MANAGEMENT("Book Management", "homeCard"),
    USER_MANAGEMENT("User Management", "homeCard"),
    BOOKS("Book", "bookCard"),
    ADD_BOOK("Add Book", "addBookCard"),
    USERS("Users", "usersCard"),
    ADD_USER("Add User", "addUserCard");


    private final String title;
    private final String cardName;

    MenuOptions(String title, String cardName) {
        this.title = title;
        this.cardName = cardName;
    }

    public String getTitle() {
        return title;
    }

    public String getCardName() {
        return cardName;
    }
}
