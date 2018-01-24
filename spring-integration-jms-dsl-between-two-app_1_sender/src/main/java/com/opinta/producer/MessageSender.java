//package com.opinta.producer;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.stereotype.Component;
//
//import static java.lang.String.format;
//
//@Component
//public class MessageSender {
//    @Autowired
//    private JmsTemplate jmsTemplate;
//
//    public void send(String destination, String message) {
//        System.out.println(format("sending message='%s' to destination='%s'", message, destination));
//
//        jmsTemplate.convertAndSend(destination, message);
//    }
//}
