package com.application.library.desktop.gui.menu.settings;

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
public class SettingsTreeNode extends DefaultMutableTreeNode implements SelectableTreeNode {

    private final ApplicationEventPublisher applicationEventPublisher;

    private static final MenuOptions menuOptions = MenuOptions.SETTINGS;

    public SettingsTreeNode(ApplicationEventPublisher applicationEventPublisher) {
        super(menuOptions.getTitle());
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public ImageIcon getImageIconPath() {
        return IconConstants.SETTINGS_ICON;
    }

    @Override
    public Set<UserRole> getAccessibleRoles() {
        return Set.of(UserRole.ROLE_ADMIN);
    }

    @Override
    public void treeSelected() {
        applicationEventPublisher.publishEvent(new MenuSelectedEvent(this, menuOptions));
    }
}
