package com.example.server.tournament.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    private static final String ENABLE_SIMPLE_BROKER_DESTINATION_TOPIC = "/topic/";
    private static final String APPLICATION_DESTINATION_PREFIX = "/app";
    private static final String ENDPOINT_PATH = "/websocket";
    private static final String ALLOWED_ORIGINS = "*";

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker(ENABLE_SIMPLE_BROKER_DESTINATION_TOPIC);
        config.setApplicationDestinationPrefixes(APPLICATION_DESTINATION_PREFIX);
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint(ENDPOINT_PATH)
                .setAllowedOrigins(ALLOWED_ORIGINS);
    }
}