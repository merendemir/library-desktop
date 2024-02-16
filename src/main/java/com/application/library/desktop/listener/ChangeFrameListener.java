package com.application.library.desktop.listener;

import com.application.library.desktop.constants.SystemVariables;
import com.application.library.desktop.core.BaseDialog;
import com.application.library.desktop.core.BaseFrame;
import com.application.library.desktop.core.IBaseFrame;
import com.application.library.desktop.enumerations.ApplicationFrames;
import com.application.library.desktop.listener.event.ChangeFrameEvent;
import com.application.library.desktop.utils.ApplicationContextHelper;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.awt.*;

@Component
public class ChangeFrameListener {

    private final ApplicationContextHelper applicationContextHelper;

    public ChangeFrameListener(ApplicationContextHelper applicationContextHelper) {
        this.applicationContextHelper = applicationContextHelper;
    }

    @Async
    @EventListener
    public void onLoginSuccessEvent(ChangeFrameEvent event) {
        ApplicationFrames toFrame = event.getToFrame();
        if (toFrame == SystemVariables.CURRENT_FRAME) return;

        applicationContextHelper.getBeansOfType(BaseFrame.class).forEach(Window::dispose);
        applicationContextHelper.getBeansOfType(BaseDialog.class).forEach(Window::dispose);

        IBaseFrame bean = applicationContextHelper.getBean(toFrame.getFrameClass());
        bean.showFrame();
        SystemVariables.CURRENT_FRAME = toFrame;
    }

}
