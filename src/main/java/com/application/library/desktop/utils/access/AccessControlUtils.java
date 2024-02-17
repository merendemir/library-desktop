package com.application.library.desktop.utils.access;

import com.application.library.desktop.constants.SystemVariables;
import com.application.library.desktop.enumerations.UserRole;

import java.util.Set;

public class AccessControlUtils {

    public static boolean hasMatchingAuthority(AccessRestricted accessRestricted) {
        Set<UserRole> userRoles = SystemVariables.USER_ROLES;

        if (userRoles == null || userRoles.isEmpty()) return false;

        Set<UserRole> accessibleRoles = accessRestricted.getAccessibleRoles();

        return userRoles.stream()
                .anyMatch(accessibleRoles::contains);
    }

    public static boolean hasAdminAuthority() {
        Set<UserRole> userRoles = SystemVariables.USER_ROLES;
        if (userRoles == null || userRoles.isEmpty()) return false;
        return userRoles.contains(UserRole.ROLE_ADMIN);
    }
}
