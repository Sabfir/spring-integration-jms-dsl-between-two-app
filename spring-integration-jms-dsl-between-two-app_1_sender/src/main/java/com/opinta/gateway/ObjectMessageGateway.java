package com.opinta.gateway;

import com.opinta.dto.Client;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

@MessagingGateway
public interface ObjectMessageGateway {

    @Gateway(requestChannel = "request.ch")
    void putToChannel(Client message);
}
