//package com.opinta.integration;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.integration.config.EnableIntegration;
//import org.springframework.integration.dsl.IntegrationFlow;
//
//@Configuration
//@EnableIntegration
//public class OneMoreExample {
//
//    @Bean
//    IntegrationFlow inboundFlow(
//            ErrorManager errorManager,
//            SeatSelector seatSelector,
//            Prioritizer priority
//    ) {
//        return IntegrationFlows
//                .from(Http.inboundGateway("/example/bookmyflight"))
//                .enrichHeaders(headerEnricherSpec -> headerEnricherSpec.header("original_request", request)
//                        .header("priority", priority.for(request)))
//                .channel(channel -> channel.priority())
//                .transform(//build credit card charge request with info from flight booking request)
//                .enrich(e -> e.requestChannel("paymentChannel")
//                        .header("paymentInfo", this.paymentInfo)
//                        .handle((payload, headers) -> seatSelector.selectSeat(payload))
//                        .publishSubscribeChannel(Executors.newCachedThreadPool(), s -> s
//                                .subscribe(subflow -> subflow
//                                        .<String>handle((payload, headers) -> "You have successfully booked your seat.")
//                                        .subscribe(subflow -> subflow
//                                                .enrichHeaders(headerEnricherSpec -> headerEnricherSpec.header("mail_to", payload.getEmailAddress()))
//                                                .handle(Mail.outboundAdapter("smtp.gmail.com")
//                                                        .credentials(ExampleCiConfig.AUTHENTICATION_EMAIL, ExampleCiConfig.AUTHENTICATION_PASSWORD)
//                                                        .javaMailProperties(mailProperties()))
//                                                .channel(channel -> channel.queue("outboundEmail"))))
//                                .get();
//    }
//
//    @Bean
//    public Prioritizer prioritizer() {
//        return new Prioritizer() {
//            public int for(Object request) {
//                //Figures out from the request whether the customer came from the airline's website or from a
//                //comparison website, and assigns a higher priority to the first type of customer
//            }
//        }.get();
//    }
//
//    @Bean
//    public ErrorManager errorManager() {
//        return new ErrorManager() {
//            public Object handleError(int statusCode, Object payload) {
//                //Handle different HTTP status codes and, if call successful, return payload
//            }
//        }
//    }
//
//    @Bean
//    public SeatSelector seatSelector() {
//        return new SeatSelector() {
//            public Object selectSeat(Object payload) {
//                //make call to some external seat selection service
//            }
//        }
//    }
//
//    Properties mailProperties() {
//        Properties properties = new Properties();
//        properties.put("mail.smtp.ssl.enable", true);
//        return properties;
//    }
//
//}