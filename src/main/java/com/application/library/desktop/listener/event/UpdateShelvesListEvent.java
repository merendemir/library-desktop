package com.application.library.desktop.listener.event;

import org.springframework.context.ApplicationEvent;

public class UpdateShelvesListEvent extends ApplicationEvent {

    public UpdateShelvesListEvent(Object source) {
        super(source);
    }

}
