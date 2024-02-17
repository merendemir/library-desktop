package com.application.library.desktop.gui.home.impl.panel.main;

import com.application.library.desktop.enumerations.MenuOptions;
import com.application.library.desktop.gui.home.impl.book.BooksPanel;
import com.application.library.desktop.gui.home.impl.user.AddUserPanel;
import com.application.library.desktop.gui.home.impl.user.UsersPanel;
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
    private final AddUserPanel addUserPanel;
    private final UsersPanel usersPanel;


    public MainPanel(AddUserPanel addUserPanel, UsersPanel usersPanel) {
        this.addUserPanel = addUserPanel;
        this.usersPanel = usersPanel;

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
    }
}
