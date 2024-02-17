package com.application.library.desktop.gui.home.impl.user;

import com.application.library.desktop.constants.MessageConstants;
import com.application.library.desktop.enumerations.NotificationType;
import com.application.library.desktop.enumerations.UserRole;
import com.application.library.desktop.gui.home.impl.panel.main.IMainPanel;
import com.application.library.desktop.listener.event.NotificationEvent;
import com.application.library.desktop.request.dto.user.UserSaveRequestDto;
import com.application.library.desktop.service.HttpRequestService;
import com.application.library.desktop.utils.access.AccessControlUtils;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;

@Service
public class AddUserPanel extends JPanel implements IMainPanel {

    private final HttpRequestService httpRequestService;
    private final ApplicationEventPublisher applicationEventPublisher;

    public AddUserPanel(HttpRequestService httpRequestService, ApplicationEventPublisher applicationEventPublisher) {
        this.httpRequestService = httpRequestService;
        this.applicationEventPublisher = applicationEventPublisher;
        setLayout(new BorderLayout());
        add(contentPane, BorderLayout.CENTER);

        roleUserCheckBox.setText(UserRole.ROLE_USER.name());
        roleAdminCheckBox.setText(UserRole.ROLE_ADMIN.name());
        roleLibrarianCheckBox.setText(UserRole.ROLE_LIBRARIAN.name());
        updateSaveButtonStatus();
        addComponentActions();
    }

    private void addComponentActions() {
        showPasswordCheckbox.addActionListener(e -> {
            if (showPasswordCheckbox.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        });

        saveButton.addActionListener(e -> {
            UserSaveRequestDto requestDto = new UserSaveRequestDto();
            requestDto.setEmail(emailTextField.getText());
            requestDto.setFirstName(firstNameField.getText());
            requestDto.setLastName(lastNameField.getText());
            requestDto.setPassword(new String(passwordField.getPassword()));


            Set<UserRole> roles = new HashSet<>();
            if (roleUserCheckBox.isSelected()) {
                roles.add(UserRole.ROLE_USER);
            }
            if (roleAdminCheckBox.isSelected()) {
                roles.add(UserRole.ROLE_ADMIN);
            }
            if (roleLibrarianCheckBox.isSelected()) {
                roles.add(UserRole.ROLE_LIBRARIAN);
            }

            requestDto.setRoles(roles);

            Long savedUserId = httpRequestService.saveUser(requestDto);
            if (savedUserId != null) {
                applicationEventPublisher.publishEvent(new NotificationEvent(this, MessageConstants.USER_SAVE_SUCCESS, NotificationType.SUCCESS));
                clearFields();
            }
        });

        emailTextField.getDocument().addDocumentListener(new CostomDocumentListener());
        passwordField.getDocument().addDocumentListener(new CostomDocumentListener());
        firstNameField.getDocument().addDocumentListener(new CostomDocumentListener());
        lastNameField.getDocument().addDocumentListener(new CostomDocumentListener());
        roleUserCheckBox.addActionListener(e -> updateSaveButtonStatus());
        roleAdminCheckBox.addActionListener(e -> updateSaveButtonStatus());
        roleLibrarianCheckBox.addActionListener(e -> updateSaveButtonStatus());
    }

    private void clearFields() {
        emailTextField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        passwordField.setText("");
        roleUserCheckBox.setSelected(false);
        roleAdminCheckBox.setSelected(false);
        roleLibrarianCheckBox.setSelected(false);
    }

    private void updateSaveButtonStatus() {
        boolean isEmailEmpty = emailTextField.getText().isEmpty();
        boolean isPasswordEmpty = passwordField.getPassword().length == 0;
        boolean isFirstNameEmpty = firstNameField.getText().isEmpty();
        boolean isLastNameEmpty = lastNameField.getText().isEmpty();
        boolean isRoleSelected = roleUserCheckBox.isSelected() || roleAdminCheckBox.isSelected() || roleLibrarianCheckBox.isSelected();

        saveButton.setEnabled(!isEmailEmpty && !isPasswordEmpty && !isFirstNameEmpty && !isLastNameEmpty && isRoleSelected);
    }

    @Override
    public void selected() {
        clearFields();
        boolean isAdmin = AccessControlUtils.hasAdminAuthority();
        if (!isAdmin) {
            roleAdminCheckBox.setSelected(false);
            roleAdminCheckBox.setEnabled(false);
            roleUserCheckBox.setSelected(true);
        }

        roleUserCheckBox.setEnabled(isAdmin);
        roleLibrarianCheckBox.setEnabled(isAdmin);
        roleAdminCheckBox.setEnabled(isAdmin);
    }


    //COMPONENTS
    private JPanel contentPane;
    private JTextField emailTextField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckbox;
    private JTextField lastNameField;
    private JTextField firstNameField;
    private JCheckBox roleUserCheckBox;
    private JCheckBox roleLibrarianCheckBox;
    private JCheckBox roleAdminCheckBox;
    private JButton saveButton;

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
        panel1.setLayout(new GridLayoutManager(6, 3, new Insets(0, 25, 0, 25), -1, -1));
        contentPane.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Password (*)");
        panel1.add(label1, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        emailTextField = new JTextField();
        emailTextField.setToolTipText("Email");
        panel2.add(emailTextField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel3, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        passwordField = new JPasswordField();
        passwordField.setText("");
        passwordField.setToolTipText("Password");
        passwordField.setVisible(true);
        panel3.add(passwordField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Email (*)");
        panel1.add(label2, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        showPasswordCheckbox = new JCheckBox();
        showPasswordCheckbox.setOpaque(true);
        showPasswordCheckbox.setText("");
        showPasswordCheckbox.setToolTipText("Show / Hidden Password");
        panel1.add(showPasswordCheckbox, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(17, 18), null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel4, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lastNameField = new JTextField();
        lastNameField.setToolTipText("Lastname");
        panel4.add(lastNameField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel5, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        firstNameField = new JTextField();
        firstNameField.setToolTipText("Firstname");
        panel5.add(firstNameField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Lastname (*)");
        panel1.add(label3, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("Fistname (*)");
        panel1.add(label4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel6, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        roleUserCheckBox = new JCheckBox();
        roleUserCheckBox.setText("CheckBox");
        panel6.add(roleUserCheckBox, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        roleLibrarianCheckBox = new JCheckBox();
        roleLibrarianCheckBox.setText("CheckBox");
        panel6.add(roleLibrarianCheckBox, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        roleAdminCheckBox = new JCheckBox();
        roleAdminCheckBox.setText("CheckBox");
        panel6.add(roleAdminCheckBox, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("User Roles (*)");
        panel1.add(label5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        saveButton = new JButton();
        saveButton.setForeground(new Color(-16760533));
        saveButton.setText("Save User");
        panel1.add(saveButton, new GridConstraints(5, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        contentPane.add(spacer2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
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
            updateSaveButtonStatus();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateSaveButtonStatus();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateSaveButtonStatus();
        }
    }

}
