package com.opinta.helper;

import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class MyHelper {

    public Message<String> printMessage(Message<String> message) {
        System.out.println("        " + message.getPayload());
        return message;
    }
}
