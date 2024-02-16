package com.application.library.desktop.gui.home.tree.book;

import com.application.library.desktop.enumerations.MenuOptions;
import com.application.library.desktop.enumerations.UserRole;
import com.application.library.desktop.gui.home.models.ImageIconTreeNode;
import com.application.library.desktop.gui.home.models.MutableTreeNode;
import org.springframework.stereotype.Component;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.Set;

public class BookManagementTreeNode extends DefaultMutableTreeNode implements ImageIconTreeNode {

    public BookManagementTreeNode() {
        super(MenuOptions.BOOK_MANAGEMENT.getTitle());
    }

    @Override
    public String getImageIconPath() {
        return "/icons/menu-book-management.png";
    }

    @Override
    public Set<UserRole> getAccessibleRoles() {
        return Set.of(UserRole.ROLE_LIBRARIAN, UserRole.ROLE_ADMIN, UserRole.ROLE_USER);
    }
}
