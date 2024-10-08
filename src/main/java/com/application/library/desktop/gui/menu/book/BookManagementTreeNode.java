package com.application.library.desktop.gui.menu.book;

import com.application.library.desktop.constants.IconConstants;
import com.application.library.desktop.enumerations.MenuOptions;
import com.application.library.desktop.enumerations.UserRole;
import com.application.library.desktop.gui.menu.IMenu.ImageIconTreeNode;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Set;

public class BookManagementTreeNode extends DefaultMutableTreeNode implements ImageIconTreeNode {

    public BookManagementTreeNode() {
        super(MenuOptions.BOOK_MANAGEMENT.getTitle());
    }

    @Override
    public ImageIcon getImageIconPath() {
        return IconConstants.MENU_BOOK_MANAGEMENT_ICON;
    }

    @Override
    public Set<UserRole> getAccessibleRoles() {
        return Set.of(UserRole.ROLE_LIBRARIAN, UserRole.ROLE_ADMIN, UserRole.ROLE_USER);
    }
}
