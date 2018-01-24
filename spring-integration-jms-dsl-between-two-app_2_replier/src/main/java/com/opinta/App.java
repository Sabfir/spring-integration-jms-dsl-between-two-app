package com.opinta;

import javax.jms.ConnectionFactory;
import org.apache.activemq.command.ActiveMQTempQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.jms.Jms;

@SpringBootApplication
@Configuration
@ImportResource("classpath:integration-context.xml")
public class App implements ApplicationRunner {
    @Autowired
    private ConnectionFactory connectionFactory;
    @Value("${queue.boot}")
    private String destination;
    @Value("${queue.boot-reply}")
    private String destinationReply;

    public static void main( String[] args ) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) {
        System.out.println("START - spring-integration-jms-between-two-app_2");
    }

    @Bean
    public IntegrationFlow inboundFlow() {
        return IntegrationFlows
                .from(Jms.inboundGateway(connectionFactory)
                        .destination(destination)
                        .defaultReplyDestination(new ActiveMQTempQueue(destinationReply)))
                .transform((String payload) -> {
                    System.out.println("OPINTA4 (in app2 inbound flow): " + payload);
                    return payload + " -> RECEIVER_APP -> SEND_REPLY";
                })
                .get();
    }
}
