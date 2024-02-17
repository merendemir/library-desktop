package com.application.library.desktop.enumerations;

import com.application.library.desktop.core.BaseFrame;
import com.application.library.desktop.core.IBaseFrame;
import com.application.library.desktop.gui.home.MainFrame;
import com.application.library.desktop.gui.login.LoginFrame;

public enum ApplicationFrames {

    LOGIN_FRAME(LoginFrame.class, LoginFrame.class),

    MAIN_FRAME(MainFrame.class, MainFrame.class);

    private final Class<? extends IBaseFrame> frameClass;
    private final Class<? extends BaseFrame> baseFrameClass;

    ApplicationFrames(Class<? extends IBaseFrame> frameClass, Class<? extends BaseFrame> baseFrameClass) {
        this.frameClass = frameClass;
        this.baseFrameClass = baseFrameClass;
    }

    public Class<? extends IBaseFrame> getFrameClass() {
        return frameClass;
    }

    public Class<? extends BaseFrame> getBaseFrameClass() {
        return baseFrameClass;
    }
}
