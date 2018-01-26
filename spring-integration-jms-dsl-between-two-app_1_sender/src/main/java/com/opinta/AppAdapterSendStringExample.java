//package com.opinta;
//
//import com.opinta.gateway.StringMessageGateway;
//import java.io.IOException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.ImportResource;
//import org.springframework.integration.annotation.IntegrationComponentScan;
//import org.springframework.integration.config.EnableIntegration;
//import org.springframework.integration.dsl.IntegrationFlow;
//import org.springframework.integration.dsl.IntegrationFlows;
//import org.springframework.integration.dsl.jms.Jms;
//import org.springframework.jms.core.JmsTemplate;
//
//@SpringBootApplication
//@Configuration
//@IntegrationComponentScan
//@EnableIntegration
//@ImportResource("classpath:integration-context.xml")
//public class AppAdapterSendStringExample implements ApplicationRunner {
//    @Autowired
//    private StringMessageGateway messageGateway;
//    @Value("${queue.adapter}")
//    private String destination;
//    @Value("${channel.request.start}")
//    private String channel;
//
//    public static void main( String[] args ) {
//        ConfigurableApplicationContext ctx = SpringApplication.run(AppAdapterSendStringExample.class, args);
//        ctx.close();
//    }
//
//    @Override
//    public void run(ApplicationArguments applicationArguments) throws IOException, InterruptedException {
//        System.out.println("=====SENDER_ADAPTER=====");
//        messageGateway.putToChannel("SENDER_ADAPTER");
//    }
//
//    @Bean
//    public IntegrationFlow amqpOutbound(JmsTemplate jmsTemplate) {
//        return IntegrationFlows.from(channel)
//                .handle(String.class, ((payload, headers) -> {
//                    System.out.println("MESSAGE SENT: " + payload);
//                    return payload;
//                }))
//                .handle(Jms.outboundAdapter(jmsTemplate)
//                        .destination(destination))
//                .get();
//    }
//}
