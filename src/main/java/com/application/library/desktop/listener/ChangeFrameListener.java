package com.application.library.desktop.listener;

import com.application.library.desktop.listener.event.ChangeFrameEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ChangeFrameListener {

    @Async
    @EventListener
    public void onLoginSuccessEvent(ChangeFrameEvent event) {
        
    }

}
