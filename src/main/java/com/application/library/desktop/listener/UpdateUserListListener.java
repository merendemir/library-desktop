package com.application.library.desktop.listener;

import com.application.library.desktop.gui.home.impl.user.UsersPanel;
import com.application.library.desktop.listener.event.UpdateUserListEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserListListener {

    private final UsersPanel usersPanel;

    public UpdateUserListListener(UsersPanel usersPanel) {
        this.usersPanel = usersPanel;

    }

    @Async
    @EventListener
    public void onLoginSuccessEvent(UpdateUserListEvent event) {
        usersPanel.sendRequestAndUpdateUsersTable();
    }

}
