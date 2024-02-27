package com.application.library.desktop.listener.event;

import com.application.library.desktop.gui.home.impl.panel.main.IRefreshablePanel;
import org.springframework.context.ApplicationEvent;

public class UpdatePanelDataEvent extends ApplicationEvent {

    private final Class<? extends IRefreshablePanel> refreshablePanel;

    public UpdatePanelDataEvent(Object source, Class<? extends IRefreshablePanel> refreshablePanel) {
        super(source);
        this.refreshablePanel = refreshablePanel;
    }

    public Class<? extends IRefreshablePanel> getRefreshablePanel() {
        return refreshablePanel;
    }
}
