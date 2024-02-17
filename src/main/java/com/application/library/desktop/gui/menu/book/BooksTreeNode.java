package com.application.library.desktop.gui.menu.book;

import com.application.library.desktop.constants.IconConstants;
import com.application.library.desktop.enumerations.MenuOptions;
import com.application.library.desktop.enumerations.UserRole;
import com.application.library.desktop.gui.menu.IMenu.SelectableTreeNode;
import com.application.library.desktop.listener.event.MenuSelectedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Set;

@Component
public class BooksTreeNode extends DefaultMutableTreeNode implements SelectableTreeNode {
    private final ApplicationEventPublisher applicationEventPublisher;

    private static final MenuOptions menuOptions = MenuOptions.BOOKS;

    public BooksTreeNode(ApplicationEventPublisher applicationEventPublisher) {
        super(menuOptions.getTitle());
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void treeSelected() {
        applicationEventPublisher.publishEvent(new MenuSelectedEvent(this, MenuOptions.BOOKS));
    }


    @Override
    public ImageIcon getImageIconPath() {
        return IconConstants.MENU_BOOK_ICON;
    }

    @Override
    public Set<UserRole> getAccessibleRoles() {
        return Set.of(UserRole.ROLE_LIBRARIAN, UserRole.ROLE_USER, UserRole.ROLE_ADMIN);
    }
}
