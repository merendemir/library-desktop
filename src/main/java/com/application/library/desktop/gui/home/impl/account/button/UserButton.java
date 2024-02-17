package com.application.library.desktop.gui.home.impl.account.button;

import com.application.library.desktop.constants.IconConstants;
import com.application.library.desktop.constants.MessageConstants;
import com.application.library.desktop.constants.TitleConstants;
import com.application.library.desktop.enumerations.ApplicationFrames;
import com.application.library.desktop.enumerations.NotificationType;
import com.application.library.desktop.listener.event.ChangeFrameEvent;
import com.application.library.desktop.listener.event.NotificationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
public class UserButton extends JButton {
    private final JPopupMenu popupMenu = new JPopupMenu();
    private final ApplicationEventPublisher applicationEventPublisher;
    private final UserUpdateAccountDialog userUpdateAccountDialog;

    public UserButton(ApplicationEventPublisher applicationEventPublisher, UserUpdateAccountDialog userUpdateAccountDialog) {
        this.applicationEventPublisher = applicationEventPublisher;
        this.userUpdateAccountDialog = userUpdateAccountDialog;
        setIcon(IconConstants.USER_ICON);
        setMenuItem();

        addActionListener(e -> popupMenu.show(this, 0, this.getHeight()));
    }

    private void setMenuItem() {
        JMenuItem updateProfileMenuItem = new JMenuItem(TitleConstants.UPDATE_ACCOUNT, IconConstants.UPDATE_PROFILE_ICON);
        updateProfileMenuItem.addActionListener(e -> {
            userUpdateAccountDialog.showDialog();
        });

        JMenuItem logoutMenuItem = new JMenuItem(TitleConstants.LOGOUT, IconConstants.LOGOUT_ICON);
        logoutMenuItem.addActionListener(e -> {
            applicationEventPublisher.publishEvent(new ChangeFrameEvent(this, ApplicationFrames.LOGIN_FRAME));
            applicationEventPublisher.publishEvent(new NotificationEvent(this, MessageConstants.LOGOUT_SUCCESS, NotificationType.SUCCESS));
        });

        popupMenu.add(updateProfileMenuItem);
        popupMenu.add(logoutMenuItem);
    }
}
