package com.application.library.desktop.gui.menu.user;

import com.application.library.desktop.enumerations.MenuOptions;
import com.application.library.desktop.enumerations.UserRole;
import com.application.library.desktop.gui.menu.IMenu.ImageIconTreeNode;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Set;


public class UserManagementTreeNode extends DefaultMutableTreeNode implements ImageIconTreeNode {

    public UserManagementTreeNode() {
        super(MenuOptions.USER_MANAGEMENT.getTitle());
    }

    @Override
    public String getImageIconPath() {
        return "/icons/menu-book-management.png";
    }

    @Override
    public Set<UserRole> getAccessibleRoles() {
        return Set.of(UserRole.ROLE_LIBRARIAN, UserRole.ROLE_ADMIN);
    }
}
