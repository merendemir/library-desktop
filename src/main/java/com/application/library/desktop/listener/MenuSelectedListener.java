package com.application.library.desktop.listener;

import com.application.library.desktop.gui.home.MainFrame;
import com.application.library.desktop.listener.event.MenuSelectedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MenuSelectedListener {

    private final MainFrame mainFrame;

    public MenuSelectedListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Async
    @EventListener
    public void onMenuSelectedEvent(MenuSelectedEvent event) {
        mainFrame.updateCardLayout(event.getMenuOptions());
    }

}
