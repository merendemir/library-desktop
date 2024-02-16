package com.application.library.desktop.listener.event;

import com.application.library.desktop.enumerations.MenuOptions;
import org.springframework.context.ApplicationEvent;

public class MenuSelectedEvent extends ApplicationEvent {

    private final MenuOptions menuOptions;

    public MenuSelectedEvent(Object source, MenuOptions menuOptions) {
        super(source);
        this.menuOptions = menuOptions;
    }

    public MenuOptions getMenuOptions() {
        return menuOptions;
    }
}
