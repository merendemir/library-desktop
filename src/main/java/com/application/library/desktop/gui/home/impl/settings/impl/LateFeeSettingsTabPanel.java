package com.application.library.desktop.gui.home.impl.settings.impl;

import com.application.library.desktop.constants.MessageConstants;
import com.application.library.desktop.enumerations.NotificationType;
import com.application.library.desktop.listener.event.NotificationEvent;
import com.application.library.desktop.service.HttpRequestService;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;

@Service
public class LateFeeSettingsTabPanel extends JPanel implements ITabbedPane {

    private final HttpRequestService httpRequestService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public LateFeeSettingsTabPanel(HttpRequestService httpRequestService, ApplicationEventPublisher applicationEventPublisher) {
        this.httpRequestService = httpRequestService;
        this.applicationEventPublisher = applicationEventPublisher;

        setLayout(new BorderLayout());
        add(contentPane, BorderLayout.CENTER);


        setComponentsActions();
    }

    private void setComponentsActions() {
        LateFeeSpinner.addChangeListener(e -> setSpinnerLabelText());
        LateFeeSpinner.setModel(new SpinnerNumberModel(0, 0, 9999.99, 0.05));
    }

    private void setSpinnerLabelText() {
        lateFeeSpinnerLabel.setText(getSpinnerValue());
    }

    private String getSpinnerValue() {
        Double value = (Double) LateFeeSpinner.getValue();
        return String.format("%.2f", value).replace(",", ".");
    }

    @Override
    public void selected() {
        Double lateFeeSettings = httpRequestService.getLateFeeSettings();
        LateFeeSpinner.setValue(lateFeeSettings);
    }

    @Override
    public void onOK() {
        String response = httpRequestService.setLateFeeSettings(Double.parseDouble(getSpinnerValue()));
        if (response != null) {
            applicationEventPublisher.publishEvent(new NotificationEvent(this, MessageConstants.LATE_FEE_SETTINGS_UPDATE_SUCCESS, NotificationType.SUCCESS));
        }
    }

    //COMPONENTS
    private JPanel contentPane;
    private JSpinner LateFeeSpinner;
    private JLabel lateFeeSpinnerLabel;

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
        contentPane.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("The daily penalty amount applied if books are not returned by the due date.");
        panel1.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        LateFeeSpinner = new JSpinner();
        panel1.add(LateFeeSpinner, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        contentPane.add(spacer2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        lateFeeSpinnerLabel = new JLabel();
        lateFeeSpinnerLabel.setText("");
        contentPane.add(lateFeeSpinnerLabel, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        LateFeeSpinner.setEditor(lateFeeSpinnerLabel);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
