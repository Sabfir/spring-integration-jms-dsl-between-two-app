//package com.opinta;
//
//import com.opinta.gateway.StringMessageGateway;
//import java.io.IOException;
//import javax.jms.ConnectionFactory;
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
//import org.springframework.integration.dsl.core.Pollers;
//import org.springframework.integration.dsl.jms.Jms;
//import org.springframework.integration.scheduling.PollerMetadata;
//
//@SpringBootApplication
//@Configuration
//@IntegrationComponentScan
//@EnableIntegration
//@ImportResource("classpath:integration-context.xml")
//public class AppGatewayExample implements ApplicationRunner {
//    @Autowired
//    private StringMessageGateway messageGateway;
//    @Autowired
//    private ConnectionFactory connectionFactory;
//    @Value("${queue.gateway}")
//    private String destination;
//    @Value("${queue.gateway-reply}")
//    private String destinationReply;
//    @Value("${channel.request.start}")
//    private String channel;
//    @Value("${channel.request.first}")
//    private String channel_1;
//    @Value("${channel.request.second}")
//    private String channel_2;
//    @Value("$channel.reply")
//    private String channel_reply;
//    @Value("$channel.subscriber.result_1")
//    private String channel_subscriber_result_1;
//    @Value("$channel.subscriber.result_2")
//    private String channel_subscriber_result_2;
//
//    public static void main( String[] args ) {
//        ConfigurableApplicationContext ctx = SpringApplication.run(AppGatewayExample.class, args);
//        ctx.close();
//    }
//
//    @Override
//    public void run(ApplicationArguments applicationArguments) throws IOException, InterruptedException {
//        System.out.println("=====SENDER_GATEWAY=====");
//        messageGateway.putToChannel("SENDER_GATEWAY");
//        Thread.sleep(500);
//        System.out.println();
//        System.out.println("---------------------------");
//        System.out.println("PRESS ENTER TO SEND NEXT MESSAGE .....");
//        System.in.read();
//        System.out.println("=====SENDER_APP_2=====");
//        messageGateway.putToChannel("SENDER_APP_2");
//        Thread.sleep(500);
//        System.out.println();
//        System.out.println("---------------------------");
//        System.out.println("PRESS ENTER TO SEND THE LAST MESSAGE .....");
//        System.in.read();
//        System.out.println("=====SENDER_APP_3=====");
//        messageGateway.putToChannel("SENDER_APP_3");
//    }
//
//    // routing example
//    @Bean
//    public IntegrationFlow requestFlow() {
//        return IntegrationFlows
//                .from(channel)
//                .handle(String.class, (payload, headerMap) -> {
//                    System.out.println("===TWO RECIPIENTS===");
//                    return payload;
//                })
//                // routing example
//                .routeToRecipients(r ->
//                        r.ignoreSendFailures(false)
//                                .recipient(channel_1, "true")
//                                .recipient(channel_2, "true"))
//                .get();
//    }
//
//    // publish-subscriber | sending to jms | other example
//    @Bean
//    public IntegrationFlow sendReceiveFlow() {
//        return IntegrationFlows
//                .from(channel_1)
//                .<String, String>transform(payload -> payload + " -> SEND_MESSAGE")
//                .transform("@myTransformer.transform(payload)")
//
////                // publish subscriber example (in parallel using newCachedThreadPool
////                .publishSubscribeChannel(Executors.newCachedThreadPool(), spec -> spec
////                        .subscribe(s -> s
////                                .handle(String.class, (payload, headerMap) -> {
////                                    System.out.println("OPINTA2_1_SUBSCRIBER_1: " + payload);
////                                    return payload;
////                                })
////                                .channel(c -> c.queue(channel_subscriber_result_1)))
////                        .subscribe(sub -> sub
////                                .handle(Jms.outboundGateway(connectionFactory)
////                                        .receiveTimeout(5000)
////                                        .requestDestination(destination)
////                                        .replyDestination(destinationReply)
////                                        .correlationKey("JMSCorrelationID"), e -> e.requiresReply(true))
////                                .handle(String.class, ((payload, headers) -> {
////                                    System.out.println("OPINTA2_2_SUBSCRIBER_2: " + payload);
////                                    return payload;
////                                }))
////                                .channel(c -> c.queue(channel_subscriber_result_2))))
//
//                // few handlers example
//                .handle(String.class, (payload, headerMap) -> {
//                    System.out.println("    FIRST_RECIPIENT");
//                    return payload;
//                })
//                .handle("myHelper", "printMessage", e -> e.requiresReply(false))
//
//                // outbound gateway with reply example
//                .handle(Jms.outboundGateway(connectionFactory)
//                        .receiveTimeout(5000)
//                        .requestDestination(destination)
//                        .replyDestination(destinationReply) // if you want to have specific queue, otherwise it will create something like this: temp-queue://ID:WIN-CKV23BQ7RD7-62950-1516892245396-1:2:1
//                        .correlationKey("JMSCorrelationID"), e -> e.requiresReply(true))
//
//                .handle(String.class, (payload, headerMap) -> {
//                    System.out.println("        " + payload);
//                    return payload;
//                })
//
//                .channel(channel_reply)
//                .get();
//    }
//
//    // default poller
//    @Bean(name = PollerMetadata.DEFAULT_POLLER)
//    public PollerMetadata poller() {
//        return Pollers.fixedRate(500).get();
//    }
//
//    // dummy flow
//    @Bean
//    public IntegrationFlow postSendFlow() {
//        return IntegrationFlows
//                .from(channel_2)
//                .handle(String.class, (payload, header) -> {
//                    System.out.println("    SECOND_RECIPIENT");
//                    return payload;
//                })
//                .handle(message -> System.out.println("        " + message.getPayload()))
//                .get();
//    }
//
//    // dummy flow
//    @Bean
//    public IntegrationFlow responseFlow() {
//        return IntegrationFlows
//                .from(channel_reply)
//                .handle(message -> System.out.println("        " + message.getPayload() + " -> REPLY_GOT"))
//                .get();
//    }
//
//    // dummy flow
//    @Bean
//    public IntegrationFlow subscriberResult_1() {
//        return IntegrationFlows
//                .from(channel_subscriber_result_1)
//                .handle(message -> System.out.println("OPINTA2_3_GOT_RESULTS_FROM_SUBSCRIBER_1: " + message.getPayload()))
//                .get();
//    }
//
//    // dummy flow
//    @Bean
//    public IntegrationFlow subscriberResult_2() {
//        return IntegrationFlows
//                .from(channel_subscriber_result_2)
//                .handle(message -> System.out.println("OPINTA2_4_GOT_RESULTS_FROM_SUBSCRIBER_2: " + message.getPayload()))
//                .get();
//    }
//
//    // custom transformer example
//    @Bean
//    public Object myTransformer() {
//        return new Object() {
//            public String transform(String in) {
//                return in + "_UPS_";
//            }
//        };
//        // these two are equivalent
////        return new Some();
//    }
////    class Some extends Object {
////        public String transform(String in) {
////            return in + "_UPS_";
////        }
////    }
//
////    @Bean
////    // the channel name this method is going to read is testNoBuilderIntegrationFlow.input (by SI convention [method_name]+.input)
////    public IntegrationFlow testNoBuilderIntegrationFlow() {
////        return f -> f
////                .transform(m -> m)
////                .handle((p, h) -> p);
////    }
//}
