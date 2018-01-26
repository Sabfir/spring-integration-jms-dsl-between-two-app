//package com.opinta;
//
//import javax.jms.ConnectionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.ImportResource;
//import org.springframework.integration.dsl.IntegrationFlow;
//import org.springframework.integration.dsl.IntegrationFlows;
//import org.springframework.integration.dsl.jms.Jms;
//
//@SpringBootApplication
//@Configuration
//@ImportResource("classpath:integration-context.xml")
//public class AppGatewayExample implements ApplicationRunner {
//    @Value("${queue.gateway}")
//    private String destination;
//    @Value("${queue.gateway-reply}")
//    private String destinationReply;
//
//    public static void main( String[] args ) {
//        SpringApplication.run(AppGatewayExample.class, args);
//    }
//
//    @Override
//    public void run(ApplicationArguments applicationArguments) {
//        System.out.println("START - spring-integration-jms-between-two-app_2 - GATEWAY");
//    }
//
//    @Bean
//    public IntegrationFlow inboundFlow(ConnectionFactory connectionFactory) {
//        return IntegrationFlows
//                .from(Jms.inboundGateway(connectionFactory)
//                        .destination(destination)
////                        .defaultReplyDestination(new ActiveMQTempQueue(destinationReply)) // it will use it only if the message doesn't content data in JMSReplyTo field
//                )
//                .transform((String payload) -> {
//                    System.out.println("GATEWAY_MESSAGE_READ: " + payload);
//                    return payload + " -> RECEIVER_APP -> SEND_REPLY";
//                })
//                .get();
//    }
//}
