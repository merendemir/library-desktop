package com.application.library.desktop.utils;

import com.application.library.desktop.constants.SystemVariables;
import com.application.library.desktop.enumerations.UserRole;
import com.application.library.desktop.gui.home.models.AccessRestricted;

import java.util.Set;

public class AccessControlUtils {

    public static boolean hasMatchingAuthority(AccessRestricted accessRestricted) {
        Set<UserRole> userRoles = SystemVariables.USER_ROLES;

        System.out.println("userRoles: " + userRoles);
        if (userRoles == null || userRoles.isEmpty()) return true; //todo: remove this line after testing

        Set<UserRole> accessibleRoles = accessRestricted.getAccessibleRoles();

        return userRoles.stream()
                .anyMatch(accessibleRoles::contains);
    }

}
