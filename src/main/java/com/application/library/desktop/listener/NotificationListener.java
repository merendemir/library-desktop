package com.application.library.desktop.listener;

import com.application.library.desktop.constants.SystemVariables;
import com.application.library.desktop.core.BaseFrame;
import com.application.library.desktop.core.IBaseFrame;
import com.application.library.desktop.gui.common.ShowNotificationFrame;
import com.application.library.desktop.listener.event.NotificationEvent;
import com.application.library.desktop.utils.ApplicationContextHelper;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class NotificationListener {

    private final ApplicationContextHelper applicationContextHelper;

    public NotificationListener(ApplicationContextHelper applicationContextHelper) {
        this.applicationContextHelper = applicationContextHelper;
    }

    @Async
    @EventListener
    public void onNotificationListenerEvent(NotificationEvent event) {
        IBaseFrame bean = applicationContextHelper.getBean(SystemVariables.CURRENT_FRAME.getFrameClass());
        if (bean instanceof ShowNotificationFrame) {
            ((ShowNotificationFrame) bean).showNotification(event.getMessage(), event.getNotificationType(), event.getTimeInSecond());
        }
    }
}
