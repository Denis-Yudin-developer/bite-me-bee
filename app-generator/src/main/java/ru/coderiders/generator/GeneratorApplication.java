package ru.coderiders.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"ru.coderiders.generator", "ru.coderiders.commons"})
@EnableFeignClients(basePackages = {"ru.coderiders.commons"})
public class GeneratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(GeneratorApplication.class, args);
    }
}
