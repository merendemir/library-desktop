package com.application.library.desktop.service;

import com.application.library.desktop.constants.RequestPathConstants;
import com.application.library.desktop.request.dto.user.BaseUserSaveRequestDto;
import com.application.library.desktop.request.dto.user.LoginRequestDto;
import com.application.library.desktop.request.dto.user.LoginResponseDto;
import com.application.library.desktop.request.view.UserDTO;

import com.application.library.desktop.utils.ResponseHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import javax.swing.*;

@Service
public class HttpRequestService {


    private final HttpClientService clientService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public HttpRequestService(HttpClientService clientService) {
        this.clientService = clientService;
    }

    public Long register(BaseUserSaveRequestDto baseUserSaveRequestDto) {
        return getDataOnResponseHandler(clientService.doPost(RequestPathConstants.REGISTER_PATH, baseUserSaveRequestDto, ResponseHandler.class), Long.class);
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        return getDataOnResponseHandler(clientService.doPost(RequestPathConstants.LOGIN_PATH, loginRequestDto, ResponseHandler.class), LoginResponseDto.class);
    }

    public UserDTO getUser(Long id) {
        return getDataOnResponseHandler(clientService.doGet("/api/users/" + id, ResponseHandler.class), UserDTO.class);
    }

    private <T> T getDataOnResponseHandler(ResponseHandler<T> responseHandler, Class<T> responseType) {
        if (responseHandler == null) return null;
        if (responseHandler.getData() == null && responseHandler.getErrorMessage() != null) {
            JOptionPane.showMessageDialog(null, responseHandler.getErrorMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        return objectMapper.convertValue(responseHandler.getData(), responseType);
    }


}
