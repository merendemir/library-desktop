package com.application.library.desktop.constants;

import com.application.library.desktop.dto.UserInformation;
import com.application.library.desktop.enumerations.ApplicationFrames;
import com.application.library.desktop.enumerations.UserRole;

import java.awt.*;
import java.util.Set;

public class SystemVariables {
    public static final Color SYSTEM_DEFAULT_COLOR = new Color(165, 152, 133);
    public static boolean isDeveloperMode = false;
    public static String AUTHORIZATION_TOKEN;
    public static UserInformation USER_INFORMATION;
    public static Set<UserRole> USER_ROLES;
    public static ApplicationFrames CURRENT_FRAME = ApplicationFrames.LOGIN_FRAME;
}
