package ru.coderiders.BiteMeBee;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"ru.coderiders.BiteMeBee", "ru.coderiders.Library"})
@EnableFeignClients(basePackages = {"ru.coderiders.Library"})
public class BiteMeBeeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiteMeBeeApplication.class, args);
	}
}
