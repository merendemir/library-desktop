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
public class LendDaySettingsTabPanel extends JPanel implements ITabbedPane {

    private final HttpRequestService httpRequestService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public LendDaySettingsTabPanel(HttpRequestService httpRequestService, ApplicationEventPublisher applicationEventPublisher) {
        this.httpRequestService = httpRequestService;
        this.applicationEventPublisher = applicationEventPublisher;

        setLayout(new BorderLayout());
        add(contentPane, BorderLayout.CENTER);

        setComponentsActions();
    }

    private void setComponentsActions() {
        lendDaySpinner.addChangeListener(e -> setSpinnerLabelText());
        lendDaySpinner.setModel(new SpinnerNumberModel(0, 0, 365, 1));
    }

    private void setSpinnerLabelText() {
        spinnerLabel.setText(lendDaySpinner.getValue() + " day");
    }

    @Override
    public void selected() {
        Integer lendDaySettings = httpRequestService.getLendDaySettings();
        lendDaySpinner.setValue(lendDaySettings);
        setSpinnerLabelText();
    }

    @Override
    public void onOK() {
        String response = httpRequestService.setLendDaySettings((Integer) lendDaySpinner.getValue());
        if (response != null) {
            applicationEventPublisher.publishEvent(new NotificationEvent(this, MessageConstants.LEND_DAY_SETTINGS_UPDATE_SUCCESS, NotificationType.SUCCESS));
        }
    }

    //COMPONENTS
    private JPanel contentPane;
    private JSpinner lendDaySpinner;
    private JLabel spinnerLabel;

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
        contentPane.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("The maximum number of days allowed for returning a book after it has been borrowed :");
        panel1.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        lendDaySpinner = new JSpinner();
        lendDaySpinner.setEnabled(true);
        panel1.add(lendDaySpinner, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        spinnerLabel = new JLabel();
        spinnerLabel.setText("");
        panel1.add(spinnerLabel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        contentPane.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        lendDaySpinner.setEditor(spinnerLabel);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

}
