package com.application.library.desktop.core;

import com.application.library.desktop.constants.IconConstants;

import javax.swing.*;

public class BaseFrame extends JFrame {
    public BaseFrame() {
        setIconImage(IconConstants.APP_ICON.getImage());
    }

}
