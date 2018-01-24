//package com.opinta.integration;
//
//import javax.jms.ConnectionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.integration.config.EnableIntegration;
//import org.springframework.integration.dsl.IntegrationFlow;
//import org.springframework.integration.dsl.IntegrationFlows;
//import org.springframework.integration.dsl.jms.Jms;
//
//@Configuration
//@EnableIntegration
//public class IntegrationConfig {
//    @Autowired
//    private ConnectionFactory connectionFactory;
//    @Value("${queue.boot}")
//    private String destination;
//    @Value("${queue.boot-reply}")
//    private String destinationReply;
//
//    @Bean
//    public IntegrationFlow requestFlow() {
//        return IntegrationFlows
//                .from("request.ch")
//                .routeToRecipients(r ->
//                        r.ignoreSendFailures(false)
//                        .recipient("request.ch.1", "true")
//                        .recipient("request.ch.2", "true"))
//                .get();
//    }
//
//    @Bean
//    public IntegrationFlow sendReceiveFlow() {
//        return IntegrationFlows
//                .from("request.ch.1")
//                .handle(Jms.outboundGateway(connectionFactory)
//                        .receiveTimeout(45000)
//                        .requestDestination(destination)
//                        .replyDestination(destinationReply)
//                        .correlationKey("JMSCorrelationID"), e -> e.requiresReply(true))
//                .channel("response.ch")
//                .get();
//    }
//
//    @Bean
//    public IntegrationFlow postSendFlow() {
//        return IntegrationFlows
//                .from("request.ch.2")
//                .handle(m -> System.out.println("OPINTA3: " + m.getPayload()))
//                .get();
//    }
//}
