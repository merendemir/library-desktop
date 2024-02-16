package com.application.library.desktop.core;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class BaseDialog extends JDialog {
    public BaseDialog() {
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/application_icon_48px.png"))).getImage());
    }

    public BaseDialog(Window owner, String title) {
        super(owner, title);
    }
}
