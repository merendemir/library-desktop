package com.application.library.desktop.service;

import com.application.library.desktop.constants.RequestPathConstants;
import com.application.library.desktop.request.dto.user.BaseUserSaveRequestDto;
import com.application.library.desktop.request.dto.user.LoginRequestDto;
import com.application.library.desktop.request.dto.user.LoginResponseDto;
import com.application.library.desktop.request.dto.user.UserSaveRequestDto;
import com.application.library.desktop.request.view.UserDTO;
import com.application.library.desktop.request.view.UserListDTO;
import com.application.library.desktop.utils.ResponseHandler;
import com.application.library.desktop.utils.pagination.PaginationResponseDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

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

    public Long saveUser(UserSaveRequestDto userSaveRequestDto) {
        return getDataOnResponseHandler(clientService.doPost(RequestPathConstants.SAVE_USER_PATH, userSaveRequestDto, ResponseHandler.class), Long.class);
    }

    public PaginationResponseDto<List<UserListDTO>> getAllUser(Map<String, String> params) {
        return getDataOnResponseHandlerByPagination(clientService.doGet(RequestPathConstants.GET_ALL_USERS, params, ResponseHandler.class), new TypeReference<PaginationResponseDto<List<UserListDTO>>>() {
        });
    }

    public Long updateActiveUserInfo(BaseUserSaveRequestDto requestDto) {
        return getDataOnResponseHandler(clientService.doPut(RequestPathConstants.UPDATE_ACTIVE_USER_INFO_PATH, requestDto, ResponseHandler.class), Long.class);
    }

    public Long updateUserInfo(Long userId, UserSaveRequestDto requestDto) {
        String path = MessageFormat.format(RequestPathConstants.UPDATE_USER_BY_ID, userId);
        return getDataOnResponseHandler(clientService.doPut(path, requestDto, ResponseHandler.class), Long.class);
    }

    public UserDTO getUserById(Long userId) {
        String path = MessageFormat.format(RequestPathConstants.GET_USER_BY_ID, userId);
        return getDataOnResponseHandler(clientService.doGet(path, ResponseHandler.class), UserDTO.class);
    }

    public Long deleteUserById(Long userId) {
        String path = MessageFormat.format(RequestPathConstants.DELETE_USER_BY_ID, userId);
        return getDataOnResponseHandler(clientService.doDelete(path, ResponseHandler.class), Long.class);
    }


    private <T> T getDataOnResponseHandler(ResponseHandler<T> responseHandler, Class<T> responseType) {
        if (responseHandler == null || responseHandler.getData() == null) return null;
        return objectMapper.convertValue(responseHandler.getData(), responseType);
    }

    private <T> T getDataOnResponseHandlerByPagination(ResponseHandler<T> responseHandler, TypeReference<T> responseType) {
        if (responseHandler == null || responseHandler.getData() == null) return null;
        return objectMapper.convertValue(responseHandler.getData(), responseType);
    }
}
