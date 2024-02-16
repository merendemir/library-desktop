package com.application.library.desktop.gui.header.enumeration;

public enum ApplicationHeaderCard {
    NOTIFICATION_PANEL("notificationCard"),
    HEADER_PANEL("headerCard");

    private final String cardName;

    ApplicationHeaderCard(String cardName) {
        this.cardName = cardName;
    }

    public String getCardName() {
        return cardName;
    }
}
