package com.application.library.desktop.listener.event;

import org.springframework.context.ApplicationEvent;

public class UpdateUserListEvent extends ApplicationEvent {

    public UpdateUserListEvent(Object source) {
        super(source);
    }

}
