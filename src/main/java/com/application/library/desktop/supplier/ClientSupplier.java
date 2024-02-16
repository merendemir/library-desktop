package com.application.library.desktop.supplier;

import com.application.library.desktop.constants.MessageConstants;
import com.application.library.desktop.enumerations.NotificationType;
import com.application.library.desktop.exceptions.AuthorizationException;
import com.application.library.desktop.exceptions.RequestNotSuccessException;
import com.application.library.desktop.listener.event.NotificationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class ClientSupplier {

    private final ApplicationEventPublisher applicationEventPublisher;

    public ClientSupplier(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public <T> T executeClientSend(SupplierCallback<T> supplier) {
        try {
            return supplier.get();
        } catch (AuthorizationException authorizationException) {
            applicationEventPublisher.publishEvent(new NotificationEvent(this, MessageConstants.AUTHORIZATION_ERROR, NotificationType.ERROR));
        } catch (RequestNotSuccessException requestNotSuccessException) {
            requestNotSuccessException.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return null;
    }

}
