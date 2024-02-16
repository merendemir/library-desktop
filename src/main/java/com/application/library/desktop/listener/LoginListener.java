package com.application.library.desktop.listener;

import com.application.library.desktop.constants.SystemVariables;
import com.application.library.desktop.dto.UserInformation;
import com.application.library.desktop.listener.event.LoginSuccessEvent;
import com.application.library.desktop.request.dto.user.LoginResponseDto;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class LoginListener {

    @Async
    @EventListener
    public void onLoginSuccessEvent(LoginSuccessEvent event) {
        LoginResponseDto loginResponseDto = event.getLoginResponseDto();
        updateSystemVariablesByLoginResponseDto(loginResponseDto);
    }

    private void updateSystemVariablesByLoginResponseDto(LoginResponseDto loginResponseDto) {
        SystemVariables.AUTHORIZATION_TOKEN = loginResponseDto.getAuthorizationToken();
        SystemVariables.USER_ROLES = loginResponseDto.getUser().getAuthorities();
        SystemVariables.USER_INFORMATION = UserInformation.of(loginResponseDto);
    }
}
