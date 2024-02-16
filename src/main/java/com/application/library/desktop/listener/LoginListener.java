package com.application.library.desktop.listener;

import com.application.library.desktop.constants.MessageConstants;
import com.application.library.desktop.constants.SystemVariables;
import com.application.library.desktop.dto.UserInformation;
import com.application.library.desktop.enumerations.ApplicationFrames;
import com.application.library.desktop.enumerations.NotificationType;
import com.application.library.desktop.listener.event.ChangeFrameEvent;
import com.application.library.desktop.listener.event.LoginSuccessEvent;
import com.application.library.desktop.listener.event.NotificationEvent;
import com.application.library.desktop.request.dto.user.LoginResponseDto;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class LoginListener {
    private final ApplicationEventPublisher applicationEventPublisher;

    public LoginListener(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Async
    @EventListener
    public void onLoginSuccessEvent(LoginSuccessEvent event) {
        LoginResponseDto loginResponseDto = event.getLoginResponseDto();
        updateSystemVariablesByLoginResponseDto(loginResponseDto);

        applicationEventPublisher.publishEvent(new ChangeFrameEvent(this, ApplicationFrames.MAIN_FRAME));
        applicationEventPublisher.publishEvent(new NotificationEvent(this, MessageConstants.LOGIN_SUCCESS, NotificationType.SUCCESS));
    }

    private void updateSystemVariablesByLoginResponseDto(LoginResponseDto loginResponseDto) {
        SystemVariables.AUTHORIZATION_TOKEN = loginResponseDto.getAuthorizationToken();
        SystemVariables.USER_ROLES = loginResponseDto.getUser().getAuthorities();
        SystemVariables.USER_INFORMATION = UserInformation.of(loginResponseDto);
    }
}
