package ru.coderiders.commons.client;

import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;

public class BeeFamilyFeignConfig {
    @Bean
    public ErrorDecoder familyErrorDecoder() {
        return new BeeFamilyErrorDecoder();
    }
}
