package ru.coderiders.bitemebee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"ru.coderiders.bitemebee", "ru.coderiders.commons"})
@EnableFeignClients(basePackages = {"ru.coderiders.commons"})
public class BiteMeBeeApplication {
    public static void main(String[] args) {
        SpringApplication.run(BiteMeBeeApplication.class, args);
    }
}
