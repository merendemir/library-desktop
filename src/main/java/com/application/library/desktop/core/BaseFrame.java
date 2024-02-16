package com.application.library.desktop.core;

import javax.swing.*;
import java.util.Objects;

public class BaseFrame extends JFrame {
    public BaseFrame() {
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/icons/application_icon_48px.png"))).getImage());
    }

}
