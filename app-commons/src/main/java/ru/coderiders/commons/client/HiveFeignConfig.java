package ru.coderiders.commons.client;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class HiveFeignConfig {
    @Bean
    public ErrorDecoder hiveErrorDecoder() {
        return new HiveErrorDecoder();
    }
}
