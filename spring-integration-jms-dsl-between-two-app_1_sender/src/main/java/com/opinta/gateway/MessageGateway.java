package com.opinta.gateway;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface MessageGateway {

    @Gateway(requestChannel = "request.ch")
    void putToChannel(String message);
}
