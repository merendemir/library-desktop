package com.application.library.desktop.listener;

import com.application.library.desktop.gui.home.impl.panel.main.IRefreshablePanel;
import com.application.library.desktop.listener.event.UpdatePanelDataEvent;
import com.application.library.desktop.utils.ApplicationContextHelper;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class RefreshPanelDataListener {

    private final ApplicationContextHelper applicationContextHelper;

    public RefreshPanelDataListener(ApplicationContextHelper applicationContextHelper) {
        this.applicationContextHelper = applicationContextHelper;
    }

    @Async
    @EventListener
    public void onUpdatePanelDataEvent(UpdatePanelDataEvent event) {
        applicationContextHelper.getBeansOfType(event.getRefreshablePanel()).forEach(IRefreshablePanel::refreshData);
    }

}
