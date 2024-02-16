package com.application.library.desktop.listener.event;

import com.application.library.desktop.enumerations.NotificationType;
import org.springframework.context.ApplicationEvent;

public class NotificationEvent extends ApplicationEvent {
    private final String message;
    private final NotificationType notificationType;
    private int timeInSecond = 3;

    public NotificationEvent(Object source, String message, NotificationType notificationType, int timeInSecond) {
        super(source);
        this.message = message;
        this.notificationType = notificationType;
        this.timeInSecond = timeInSecond;
    }

    public NotificationEvent(Object source, String message, NotificationType notificationType) {
        super(source);
        this.message = message;
        this.notificationType = notificationType;
    }


    public String getMessage() {
        return message;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    public int getTimeInSecond() {
        return timeInSecond;
    }
}
