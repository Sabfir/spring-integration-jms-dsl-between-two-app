//package com.opinta.consumer;
//
//import java.util.concurrent.CountDownLatch;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.jms.annotation.JmsListener;
//import org.springframework.stereotype.Component;
//
//@Component
//public class MessageReceiver {
//    private static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);
//
//    private CountDownLatch latch = new CountDownLatch(1);
//
//    @JmsListener(destination = "${queue.boot}")
//    public void receive(String message) {
//        LOG.info("received message='{}'", message);
//        latch.countDown();
//    }
//
//    public CountDownLatch getLatch() {
//        return latch;
//    }
//}
