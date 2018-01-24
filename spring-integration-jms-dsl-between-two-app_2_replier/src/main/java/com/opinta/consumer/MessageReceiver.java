//package com.opinta.consumer;
//
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.stereotype.Component;
//
//import static java.lang.String.format;
//
//@Component
//public class MessageReceiver {
//
//    @JmsListener(destination = "${queue.boot}")
//    public void receive(String message) {
//        System.out.println(format("received message='%s'", message));
//    }
//}
