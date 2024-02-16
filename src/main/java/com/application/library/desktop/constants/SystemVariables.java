package com.application.library.desktop.constants;

import com.application.library.desktop.dto.UserInformation;
import com.application.library.desktop.enumerations.ApplicationFrames;
import com.application.library.desktop.enumerations.UserRole;

import java.util.Set;

public class SystemVariables {
    public static String AUTHORIZATION_TOKEN;
    public static UserInformation USER_INFORMATION;
    public static Set<UserRole> USER_ROLES;
    public static ApplicationFrames CURRENT_FRAME = ApplicationFrames.LOGIN_FRAME;
}
