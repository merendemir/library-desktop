package com.application.library.desktop.supplier;

import com.application.library.desktop.constants.MessageConstants;
import com.application.library.desktop.enumerations.ApplicationFrames;
import com.application.library.desktop.enumerations.NotificationType;
import com.application.library.desktop.exceptions.AuthorizationException;
import com.application.library.desktop.exceptions.RequestNotSuccessException;
import com.application.library.desktop.listener.event.ChangeFrameEvent;
import com.application.library.desktop.listener.event.NotificationEvent;
import com.application.library.desktop.utils.ErrorResponseHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.net.http.HttpResponse;

@Service
public class ClientSupplier {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final ApplicationEventPublisher applicationEventPublisher;

    public ClientSupplier(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public <T> T executeClientSend(SupplierCallback<T> supplier) {
        try {
            return supplier.get();
        } catch (AuthorizationException authorizationException) {
            manageAuthorizationException();
        } catch (RequestNotSuccessException requestNotSuccessException) {
            manageRequestNotSuccessException(requestNotSuccessException);
        } catch (Throwable throwable) {
            applicationEventPublisher.publishEvent(new NotificationEvent(this, MessageConstants.CONNECTION_ERROR, NotificationType.ERROR));
            throwable.printStackTrace();
        }

        return null;
    }

    private void manageAuthorizationException() {
        applicationEventPublisher.publishEvent(new ChangeFrameEvent(this, ApplicationFrames.LOGIN_FRAME));
        applicationEventPublisher.publishEvent(new NotificationEvent(this, MessageConstants.AUTHORIZATION_ERROR, NotificationType.WARNING));
    }

    private void manageRequestNotSuccessException(RequestNotSuccessException requestNotSuccessException) {
        try {
            HttpResponse<String> response = requestNotSuccessException.getResponse();

            NotificationType notificationType = is4xxClientError(response.statusCode()) ? NotificationType.WARNING : NotificationType.ERROR;
            ErrorResponseHandler errorResponseHandler = objectMapper.readValue(response.body(), ErrorResponseHandler.class);
            applicationEventPublisher.publishEvent(new NotificationEvent(this, errorResponseHandler.getErrorMessage(), notificationType));
        } catch (Throwable throwable) {
            applicationEventPublisher.publishEvent(new NotificationEvent(this, MessageConstants.SERVER_ERROR, NotificationType.ERROR));
            throwable.printStackTrace();
        }
    }

    private boolean is4xxClientError(int statusCode) {
        return statusCode >= 400 && statusCode < 500;
    }

}
