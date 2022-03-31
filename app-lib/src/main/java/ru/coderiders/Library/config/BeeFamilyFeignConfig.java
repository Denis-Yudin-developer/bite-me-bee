package ru.coderiders.Library.config;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.coderiders.Library.client.BeeFamilyErrorDecoder;

@Configuration
public class BeeFamilyFeignConfig {
    @Bean
    public ErrorDecoder familyErrorDecoder() {
        return new BeeFamilyErrorDecoder();
    }
}
