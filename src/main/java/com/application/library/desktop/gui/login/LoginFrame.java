package com.application.library.desktop.gui.login;

import com.application.library.desktop.constants.IconConstants;
import com.application.library.desktop.constants.SystemVariables;
import com.application.library.desktop.constants.TitleConstants;
import com.application.library.desktop.core.BaseFrame;
import com.application.library.desktop.core.IBaseFrame;
import com.application.library.desktop.enumerations.NotificationType;
import com.application.library.desktop.gui.common.ShowNotificationFrame;
import com.application.library.desktop.gui.header.ApplicationHeaderPanel;
import com.application.library.desktop.listener.event.LoginSuccessEvent;
import com.application.library.desktop.request.dto.user.LoginRequestDto;
import com.application.library.desktop.request.dto.user.LoginResponseDto;
import com.application.library.desktop.service.HttpRequestService;
import com.application.library.desktop.service.TaskExecutorService;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class LoginFrame extends BaseFrame implements ShowNotificationFrame, IBaseFrame {

    //VARIABLES
    private final ImageIcon backgroundImageIcon = IconConstants.APPLICATION_BACKGROUND;

    //SERVICES
    private final RegisterDialog registerDialog;
    private final HttpRequestService httpRequestService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ApplicationHeaderPanel applicationHeaderPanel = new ApplicationHeaderPanel();

    public LoginFrame(RegisterDialog registerDialog, HttpRequestService httpRequestService, ApplicationEventPublisher applicationEventPublisher) {
        this.registerDialog = registerDialog;
        this.httpRequestService = httpRequestService;
        this.applicationEventPublisher = applicationEventPublisher;

        $$$setupUI$$$();
        setTitle(TitleConstants.LOGIN_FRAME);
        setContentPane(contentPane);
        setSize(backgroundImageIcon.getIconWidth(), backgroundImageIcon.getIconHeight());

        setLocationRelativeTo(null);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setComponentActions();

//        showFrame();
    }

    private void setComponentActions() {
        loginButton.addActionListener(e -> {
            String email = emailTextField.getText();
            String password = new String(passwordField.getPassword());

            if (SystemVariables.isDeveloperMode) {
                email = ("system.admin");
//                email = ("lib");
                password = ("123");
            }

            LoginResponseDto loginResponseDto = httpRequestService.login(new LoginRequestDto(email, password));
            if (loginResponseDto == null) return;
            applicationEventPublisher.publishEvent(new LoginSuccessEvent(this, loginResponseDto));
        });

        registerButton.addActionListener(e -> {
            registerDialog.showDialog(this);

            if (registerDialog.isSuccessful()) {
                emailTextField.setText(registerDialog.getEmail());
                passwordField.setText(null);
            }
        });

        showPasswordCheckbox.addActionListener(e -> {
            passwordField.setEchoChar(showPasswordCheckbox.isSelected() ? (char) 0 : '*');
        });

        emailTextField.getDocument().addDocumentListener(new CostomDocumentListener());
        passwordField.getDocument().addDocumentListener(new CostomDocumentListener());

        contentPane.registerKeyboardAction(e ->
                        loginButton.doClick(),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0)
                , JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        updateLoginButtonStatus();
    }

    private void updateLoginButtonStatus() {
        loginButton.setEnabled(
                !emailTextField.getText().isEmpty() &&
                        passwordField.getPassword().length != 0);
    }

    @Override
    public void showNotification(String message, NotificationType type, int timeInSecond) {
        applicationHeaderPanel.showNotification(message, type);
        TaskExecutorService.schedule(applicationHeaderPanel::hideNotification, timeInSecond, TimeUnit.SECONDS);
    }

    @Override
    public void showFrame() {
        emailTextField.setText("");
        passwordField.setText("");
        setVisible(true);

        if (SystemVariables.isDeveloperMode) {
            loginButton.setEnabled(true);
            loginButton.doClick();
        }
    }

    //COMPONENTS
    private JPanel contentPane;
    private JPanel loginPanel;
    private JTextField emailTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private JCheckBox showPasswordCheckbox;

    private JPanel applicationHeaderPanelGUI;

    private void createUIComponents() {
        applicationHeaderPanelGUI = applicationHeaderPanel;
        contentPane = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                g.drawImage(backgroundImageIcon.getImage(), 0, 0, this);
            }
        };

    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        contentPane.setLayout(new GridLayoutManager(4, 1, new Insets(0, 0, 0, 0), -1, -1));
        contentPane.setRequestFocusEnabled(true);
        loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        loginPanel.setVisible(true);
        contentPane.add(loginPanel, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, new Dimension(600, -1), 0, false));
        final Spacer spacer1 = new Spacer();
        loginPanel.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 3, new Insets(0, 25, 0, 25), -1, -1));
        loginPanel.add(panel1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Password (*)");
        panel1.add(label1, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        emailTextField = new JTextField();
        emailTextField.setToolTipText("Email");
        panel2.add(emailTextField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel3, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        passwordField = new JPasswordField();
        passwordField.setText("");
        passwordField.setToolTipText("Password");
        passwordField.setVisible(true);
        panel3.add(passwordField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel4, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        loginButton = new JButton();
        loginButton.setForeground(new Color(-14044119));
        loginButton.setText("Login");
        loginButton.setToolTipText("Login");
        panel4.add(loginButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        registerButton = new JButton();
        registerButton.setForeground(new Color(-4487113));
        registerButton.setText("Register");
        registerButton.setToolTipText("Register");
        panel4.add(registerButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Email");
        panel1.add(label2, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        showPasswordCheckbox = new JCheckBox();
        showPasswordCheckbox.setOpaque(true);
        showPasswordCheckbox.setText("");
        showPasswordCheckbox.setToolTipText("Show / Hidden Password");
        panel1.add(showPasswordCheckbox, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(17, 18), null, 0, false));
        final Spacer spacer3 = new Spacer();
        contentPane.add(spacer3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        contentPane.add(spacer4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        contentPane.add(applicationHeaderPanelGUI, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        emailTextField.setNextFocusableComponent(passwordField);
        passwordField.setNextFocusableComponent(loginButton);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }

    class CostomDocumentListener implements DocumentListener {
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateLoginButtonStatus();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateLoginButtonStatus();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateLoginButtonStatus();
        }
    }

}
