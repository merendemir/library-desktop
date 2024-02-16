package com.application.library.desktop.gui.home.models;

import com.application.library.desktop.enumerations.UserRole;

import java.util.Set;

public interface AccessRestricted {
    Set<UserRole> getAccessibleRoles();
}
