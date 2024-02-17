package com.application.library.desktop.gui.header.impl;

import com.application.library.desktop.gui.common.TimeUpdatablePanel;
import com.application.library.desktop.service.TimerService;
import com.application.library.desktop.utils.TimeUtil;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.time.LocalDateTime;

public class HeaderPanel extends JPanel implements TimeUpdatablePanel {
    public final static String APP_NAME = "LIBRARY MANAGEMENT SYSTEM";
    public final static String SERVER_TIME_TEXT = "Time: ";
    private JPanel contentPane;
    private JLabel applicationNameLabel;
    private JLabel timeLabel;

    public HeaderPanel() {
        setLayout(new BorderLayout());
        add(contentPane, BorderLayout.CENTER);
        applicationNameLabel.setText(APP_NAME);
        timeLabel.setText(SERVER_TIME_TEXT + TimeUtil.getLocalDate(LocalDateTime.now()));
        TimerService.addTimeUpdatablePanel(this);
    }


    @Override
    public void updateTime() {
        timeLabel.setText(SERVER_TIME_TEXT + TimeUtil.getLocalDate(LocalDateTime.now()));
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.setBorder(BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        applicationNameLabel = new JLabel();
        applicationNameLabel.setText("");
        contentPane.add(applicationNameLabel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        timeLabel = new JLabel();
        timeLabel.setText("");
        contentPane.add(timeLabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
