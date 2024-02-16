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
            applicationEventPublisher.publishEvent(new NotificationEvent(this, MessageConstants.AUTHORIZATION_ERROR, NotificationType.WARNING));
            applicationEventPublisher.publishEvent(new ChangeFrameEvent(this, ApplicationFrames.LOGIN_FRAME));
        } catch (RequestNotSuccessException requestNotSuccessException) {
            try {
                ErrorResponseHandler errorResponseHandler = objectMapper.readValue(requestNotSuccessException.getResponse().body(), ErrorResponseHandler.class);
                applicationEventPublisher.publishEvent(new NotificationEvent(this, errorResponseHandler.getErrorMessage(), NotificationType.WARNING));
            } catch (Throwable throwable) {
                applicationEventPublisher.publishEvent(new NotificationEvent(this, MessageConstants.SERVER_ERROR, NotificationType.ERROR));
                throwable.printStackTrace();
            }
        } catch (Throwable throwable) {
            applicationEventPublisher.publishEvent(new NotificationEvent(this, MessageConstants.SERVER_ERROR, NotificationType.ERROR));
            throwable.printStackTrace();
        }

        return null;
    }

}
