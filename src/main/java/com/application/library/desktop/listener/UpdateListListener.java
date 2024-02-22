package com.application.library.desktop.listener;

import com.application.library.desktop.gui.home.impl.panel.main.MainPanel;
import com.application.library.desktop.gui.home.impl.user.UsersPanel;
import com.application.library.desktop.listener.event.UpdateShelvesListEvent;
import com.application.library.desktop.listener.event.UpdateUserListEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class UpdateListListener {

    private final UsersPanel usersPanel;
    private final MainPanel mainPanel;


    public UpdateListListener(UsersPanel usersPanel, MainPanel mainPanel) {
        this.usersPanel = usersPanel;

        this.mainPanel = mainPanel;
    }

    @Async
    @EventListener
    public void onUpdateUserListEvent(UpdateUserListEvent event) {
        usersPanel.sendRequestAndUpdateUsersTable();
    }

    @Async
    @EventListener
    public void onUpdateUpdateShelvesListEvent(UpdateShelvesListEvent event) {
        mainPanel.updateShelvesList();
    }

}
