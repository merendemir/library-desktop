package com.application.library.desktop.utils.access;

import com.application.library.desktop.enumerations.UserRole;

import java.util.Set;

public interface AccessRestricted {
    Set<UserRole> getAccessibleRoles();
}
