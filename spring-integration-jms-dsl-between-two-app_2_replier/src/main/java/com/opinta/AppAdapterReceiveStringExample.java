//package com.opinta;
//
//import javax.jms.ConnectionFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.ImportResource;
//import org.springframework.integration.annotation.IntegrationComponentScan;
//import org.springframework.integration.config.EnableIntegration;
//import org.springframework.integration.dsl.IntegrationFlow;
//import org.springframework.integration.dsl.IntegrationFlows;
//import org.springframework.integration.dsl.core.Pollers;
//import org.springframework.integration.dsl.jms.Jms;
//import org.springframework.integration.scheduling.PollerMetadata;
//
//@SpringBootApplication
//@Configuration
//@IntegrationComponentScan
//@EnableIntegration
//@ImportResource("classpath:integration-context.xml")
//public class AppAdapterReceiveStringExample implements ApplicationRunner {
//    @Value("${queue.adapter}")
//    private String destination;
//    private String channel = "channel_1";
//
//    public static void main( String[] args ) {
//        SpringApplication.run(AppAdapterReceiveStringExample.class, args);
//    }
//
//    @Override
//    public void run(ApplicationArguments applicationArguments) {
//        System.out.println("START - spring-integration-jms-between-two-app_2 - ADAPTER");
//    }
//
//    @Bean
//    public IntegrationFlow inboundFlow(ConnectionFactory connectionFactory) {
//        return IntegrationFlows
//                .from(Jms.inboundAdapter(connectionFactory)
//                        .destination(destination))
//                .transform(payload -> payload) // dummy transformer
//                .handle(String.class, ((payload, headers) -> payload)) // dummy handle
//                .channel(channel)
//                .get();
//    }
//
//    // dummy flow
//    @Bean
//    public IntegrationFlow subscriberResult_1() {
//        return IntegrationFlows
//                .from(channel)
//                .handle(message -> {
//                    System.out.println("ADAPTER_MESSAGE_READ: " + message.getPayload());
//                })
//                .get();
//    }
//
//    // default poller
//    @Bean(name = PollerMetadata.DEFAULT_POLLER)
//    public PollerMetadata poller() {
//        return Pollers.fixedRate(500).get();
//    }
//}
