package ru.coderiders.bitemebee;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@SecurityScheme(name = "bitemebee", scheme = "basic", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@SpringBootApplication(scanBasePackages = {"ru.coderiders.bitemebee", "ru.coderiders.commons"})
@EnableFeignClients(basePackages = {"ru.coderiders.commons"})
public class BiteMeBeeApplication {
    public static void main(String[] args) {
        SpringApplication.run(BiteMeBeeApplication.class, args);
    }
}
