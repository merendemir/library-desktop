package com.application.library.desktop.gui.home.impl.user.menu;

import com.application.library.desktop.constants.IconConstants;
import com.application.library.desktop.constants.MessageConstants;
import com.application.library.desktop.constants.TitleConstants;
import com.application.library.desktop.enumerations.NotificationType;
import com.application.library.desktop.gui.home.impl.user.UsersPanel;
import com.application.library.desktop.listener.event.NotificationEvent;
import com.application.library.desktop.listener.event.UpdatePanelDataEvent;
import com.application.library.desktop.request.view.UserDTO;
import com.application.library.desktop.service.HttpRequestService;
import com.application.library.desktop.utils.ApplicationContextHelper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
public class UserPopupMenu extends JPopupMenu {

    private Long selectedUserId;

    JMenuItem updateUserMenuItem = new JMenuItem(TitleConstants.UPDATE_USER, IconConstants.UPDATE_ACCOUNT_ICON);
    JMenuItem deleteUserMenuItem = new JMenuItem(TitleConstants.DELETE_USER, IconConstants.DELETE_USER_ICON);

    private final UpdateUserDialog updateUserDialog;
    private final HttpRequestService httpRequestService;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ApplicationContextHelper applicationContextHelper;

    public UserPopupMenu(UpdateUserDialog updateUserDialog, HttpRequestService httpRequestService, ApplicationEventPublisher applicationEventPublisher, ApplicationContextHelper applicationContextHelper) {
        this.updateUserDialog = updateUserDialog;
        this.httpRequestService = httpRequestService;
        this.applicationEventPublisher = applicationEventPublisher;
        this.applicationContextHelper = applicationContextHelper;

        add(updateUserMenuItem);
        add(deleteUserMenuItem);

        setComponentActions();
    }

    private void setComponentActions() {
        updateUserMenuItem.addActionListener(e -> {
            UserDTO userDTO = httpRequestService.getUserById(selectedUserId);
            updateUserDialog.showDialog(userDTO);
        });

        deleteUserMenuItem.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(applicationContextHelper.getCurrentFrame(),
                    MessageConstants.DELETE_USER_CONFIRMATION, TitleConstants.DELETE_USER,
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            if (choice != JOptionPane.YES_OPTION) return;

            Long userId = httpRequestService.deleteUserById(selectedUserId);

            if (userId != null) {
                applicationEventPublisher.publishEvent(new UpdatePanelDataEvent(this, UsersPanel.class));
                applicationEventPublisher.publishEvent(new NotificationEvent(this, MessageConstants.USER_DELETE_SUCCESS, NotificationType.SUCCESS));
            }
        });
    }

    public void setSelectedUserId(Long selectedUserId) {
        this.selectedUserId = selectedUserId;
    }
}

