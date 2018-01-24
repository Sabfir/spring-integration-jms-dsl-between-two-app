//package com.opinta.producer;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MessageSender {
//    private static final Logger LOG = LoggerFactory.getLogger(MessageSender.class);
//
//    @Autowired
//    private JmsTemplate jmsTemplate;
//
//    public void send(String destination, String message) {
//        LOG.info("sending message='{}' to destination='{}'", message, destination);
//        jmsTemplate.convertAndSend(destination, message);
//    }
//}
