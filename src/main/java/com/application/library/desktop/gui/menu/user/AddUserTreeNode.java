package com.application.library.desktop.gui.menu.user;

import com.application.library.desktop.constants.IconConstants;
import com.application.library.desktop.enumerations.MenuOptions;
import com.application.library.desktop.enumerations.UserRole;
import com.application.library.desktop.gui.home.impl.user.AddUserPanel;
import com.application.library.desktop.gui.menu.IMenu.SelectableTreeNode;
import com.application.library.desktop.listener.event.MenuSelectedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Set;

@Component
public class AddUserTreeNode extends DefaultMutableTreeNode implements SelectableTreeNode {
    private final ApplicationEventPublisher applicationEventPublisher;

    private static final MenuOptions menuOptions = MenuOptions.ADD_USER;

    public AddUserTreeNode(ApplicationEventPublisher applicationEventPublisher) {
        super(menuOptions.getTitle());
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void treeSelected() {
        applicationEventPublisher.publishEvent(new MenuSelectedEvent(this, menuOptions));
    }

    @Override
    public ImageIcon getImageIconPath() {
        return IconConstants.MENU_ADD_USER_ICON;
    }

    @Override
    public Set<UserRole> getAccessibleRoles() {
        return Set.of(UserRole.ROLE_LIBRARIAN, UserRole.ROLE_ADMIN);
    }
}
