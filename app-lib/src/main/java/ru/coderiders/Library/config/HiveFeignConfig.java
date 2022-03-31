package ru.coderiders.Library.config;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.coderiders.Library.client.HiveErrorDecoder;

@Configuration
public class HiveFeignConfig {
    @Bean
    public ErrorDecoder hiveErrorDecoder() {
        return new HiveErrorDecoder();
    }
}
