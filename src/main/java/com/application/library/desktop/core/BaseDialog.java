package com.application.library.desktop.core;

import com.application.library.desktop.constants.IconConstants;

import javax.swing.*;
import java.awt.*;

public class BaseDialog extends JDialog {
    public BaseDialog() {
        setIconImage(IconConstants.APP_ICON.getImage());
    }

    public BaseDialog(Window owner, String title) {
        super(owner, title);
    }
}
