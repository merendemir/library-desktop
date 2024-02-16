package com.application.library.desktop.enumerations;

import com.application.library.desktop.core.IBaseFrame;
import com.application.library.desktop.gui.home.MainFrame;
import com.application.library.desktop.gui.login.LoginFrame;

public enum ApplicationFrames {

    LOGIN_FRAME(LoginFrame.class),

    MAIN_FRAME(MainFrame.class);

    private final Class<? extends IBaseFrame> frameClass;

    ApplicationFrames(Class<? extends IBaseFrame> frameClass) {
        this.frameClass = frameClass;
    }

    public Class<? extends IBaseFrame> getFrameClass() {
        return frameClass;
    }
}
