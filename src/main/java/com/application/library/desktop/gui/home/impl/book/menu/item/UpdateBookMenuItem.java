package com.application.library.desktop.gui.home.impl.book.menu.item;

import com.application.library.desktop.constants.IconConstants;
import com.application.library.desktop.constants.TitleConstants;
import com.application.library.desktop.enumerations.UserRole;
import com.application.library.desktop.utils.access.AccessRestricted;

import javax.swing.*;
import java.util.Set;

public class UpdateBookMenuItem extends JMenuItem implements AccessRestricted {

    public UpdateBookMenuItem() {
        super(TitleConstants.UPDATE_BOOK, IconConstants.MENU_UPDATE_ICON);
    }

    @Override
    public Set<UserRole> getAccessibleRoles() {
        return Set.of(UserRole.ROLE_ADMIN, UserRole.ROLE_LIBRARIAN);
    }
}
