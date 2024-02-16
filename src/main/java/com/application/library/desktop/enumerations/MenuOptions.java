package com.application.library.desktop.enumerations;

public enum MenuOptions {
    MENU("Menu", "homeCard"),
    BOOK_MANAGEMENT("Book Management", "homeCard"),
    BOOKS("Books", "booksCard"),
    ADD_BOOK("Add Book", "addBookCard");

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
