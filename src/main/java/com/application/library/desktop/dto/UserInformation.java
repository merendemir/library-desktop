package com.application.library.desktop.dto;

import com.application.library.desktop.request.dto.user.BaseUserDto;
import com.application.library.desktop.request.dto.user.LoginResponseDto;

public class UserInformation {
    private Long id;
    private String email;
    private String firstName;
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static UserInformation of (LoginResponseDto responseDto) {
        BaseUserDto responseDtoUser = responseDto.getUser();

        UserInformation userInformation = new UserInformation();

        userInformation.setId(responseDtoUser.getId());
        userInformation.setEmail(responseDtoUser.getEmail());
        userInformation.setFirstName(responseDtoUser.getFirstName());
        userInformation.setLastName(responseDtoUser.getLastName());

        return userInformation;
    }
}
