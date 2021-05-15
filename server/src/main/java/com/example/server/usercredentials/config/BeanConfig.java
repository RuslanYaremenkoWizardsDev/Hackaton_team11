package com.example.server.usercredentials.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.time.Clock;

@Configuration
public class BeanConfig {

    @Bean
    public Clock getCurrentTime() {
        return Clock.systemUTC();
    }
}
