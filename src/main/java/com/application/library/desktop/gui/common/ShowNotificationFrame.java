package com.application.library.desktop.gui.common;

import com.application.library.desktop.enumerations.NotificationType;

public interface ShowNotificationFrame {
    void showNotification(String message, NotificationType type, int timeInSecond);
}
