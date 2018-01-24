//package com.opinta;
//
//import com.opinta.consumer.MessageReceiver;
//import com.opinta.producer.MessageSender;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import static java.util.concurrent.TimeUnit.MILLISECONDS;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@DirtiesContext
//public class AppTest {
//    @Autowired
//    private MessageSender messageSender;
//    @Autowired
//    private MessageReceiver messageReceiver;
//    @Value("${queue.boot}")
//    private String destination;
//
//    @Test
//    public void testReceive() throws Exception {
//        messageSender.send(destination, "Hello Boot!");
//
//        messageReceiver.getLatch().await(2000, MILLISECONDS);
//        assertThat(messageReceiver.getLatch().getCount()).isEqualTo(0);
//    }
//}
