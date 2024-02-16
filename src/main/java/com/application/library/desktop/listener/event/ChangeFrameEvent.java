package com.application.library.desktop.listener.event;

import com.application.library.desktop.enumerations.ApplicationFrames;
import org.springframework.context.ApplicationEvent;

public class ChangeFrameEvent extends ApplicationEvent {
    private final ApplicationFrames toFrame;

    public ChangeFrameEvent(Object source, ApplicationFrames toFrame) {
        super(source);
        this.toFrame = toFrame;
    }

    public ApplicationFrames getToFrame() {
        return toFrame;
    }
}
