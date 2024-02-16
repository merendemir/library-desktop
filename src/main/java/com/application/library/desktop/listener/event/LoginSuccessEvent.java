package com.application.library.desktop.listener.event;

import com.application.library.desktop.request.dto.user.LoginResponseDto;
import org.springframework.context.ApplicationEvent;

public class LoginSuccessEvent extends ApplicationEvent {
    private final LoginResponseDto loginResponseDto;

    public LoginSuccessEvent(Object source,LoginResponseDto loginResponseDto) {
        super(source);
        this.loginResponseDto = loginResponseDto;
    }

    public LoginResponseDto getLoginResponseDto() {
        return loginResponseDto;
    }
}
