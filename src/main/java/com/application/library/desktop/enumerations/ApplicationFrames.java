package com.application.library.desktop.enumerations;

import com.application.library.desktop.core.BaseFrame;
import com.application.library.desktop.gui.login.LoginFrame;

import javax.swing.*;

public enum ApplicationFrames {

    LOGIN_FRAME(LoginFrame.class),

    MAIN_FRAME(LoginFrame.class);

    private final Class<? extends BaseFrame> frameClass;

    ApplicationFrames(Class<? extends BaseFrame> frameClass) {
        this.frameClass = frameClass;
    }

    public Class<? extends BaseFrame> getFrameClass() {
        return frameClass;
    }


}
