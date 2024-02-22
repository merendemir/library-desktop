package com.application.library.desktop.gui.home.impl.shelf.menu.item;

import com.application.library.desktop.constants.IconConstants;
import com.application.library.desktop.constants.TitleConstants;
import com.application.library.desktop.enumerations.UserRole;
import com.application.library.desktop.utils.access.AccessRestricted;

import javax.swing.*;
import java.util.Set;

public class UpdateShelfMenuItem extends JMenuItem implements AccessRestricted {

    public UpdateShelfMenuItem() {
        super(TitleConstants.UPDATE_SHELF, IconConstants.MENU_SHELVES_ICON);
    }

    @Override
    public Set<UserRole> getAccessibleRoles() {
        return Set.of(UserRole.ROLE_ADMIN);
    }
}
