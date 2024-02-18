package com.application.library.desktop.gui.home.impl.panel.main;

import com.application.library.desktop.constants.MessageConstants;
import com.application.library.desktop.enumerations.MenuOptions;
import com.application.library.desktop.enumerations.NotificationType;
import com.application.library.desktop.gui.home.impl.book.BooksPanel;
import com.application.library.desktop.gui.home.impl.settings.SettingsPanel;
import com.application.library.desktop.gui.home.impl.user.UserPanel;
import com.application.library.desktop.gui.home.impl.user.UsersPanel;
import com.application.library.desktop.listener.event.NotificationEvent;
import com.application.library.desktop.request.dto.user.UserSaveRequestDto;
import com.application.library.desktop.service.HttpRequestService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class MainPanel extends JPanel {

    private final Map<MenuOptions, IMainPanel> cardMap = new HashMap<>();
    private final BooksPanel homePanel = new BooksPanel();
    private final BooksPanel booksPanel = new BooksPanel();
    private final UserPanel addUserPanel = new UserPanel(this::saveUser);
    private final UsersPanel usersPanel;
    private final SettingsPanel settingsPanel;
    private final HttpRequestService httpRequestService;
    private final ApplicationEventPublisher applicationEventPublisher;


    public MainPanel(UsersPanel usersPanel, SettingsPanel settingsPanel, HttpRequestService httpRequestService, ApplicationEventPublisher applicationEventPublisher) {
        this.usersPanel = usersPanel;
        this.settingsPanel = settingsPanel;
        this.httpRequestService = httpRequestService;
        this.applicationEventPublisher = applicationEventPublisher;

        setLayout(new CardLayout(0, 0));

        fillCardMap();

        cardMap.forEach((menuOption, panel) -> {
            add(menuOption.getCardName(), (JPanel) panel);
        });
    }

    public void updateCardLayout(MenuOptions menuOption) {
        cardMap.get(menuOption).selected();
        CardLayout cardLayout = (CardLayout) getLayout();
        cardLayout.show(this, menuOption.getCardName());
    }

    private void fillCardMap() {
        cardMap.put(MenuOptions.HOME, homePanel);
        cardMap.put(MenuOptions.BOOKS, booksPanel);
        cardMap.put(MenuOptions.ADD_BOOK, booksPanel);
        cardMap.put(MenuOptions.USERS, usersPanel);
        cardMap.put(MenuOptions.ADD_USER, addUserPanel);
        cardMap.put(MenuOptions.SETTINGS, settingsPanel);
    }

    private void saveUser() {
        UserSaveRequestDto requestDto = addUserPanel.getUserSaveRequestDto();
        Long savedUserId = httpRequestService.saveUser(requestDto);

        if (savedUserId != null) {
            addUserPanel.selected();
            applicationEventPublisher.publishEvent(new NotificationEvent(this, MessageConstants.USER_SAVE_SUCCESS, NotificationType.SUCCESS));
        }
    }
}
