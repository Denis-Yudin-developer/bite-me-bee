package ru.coderiders.commons.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.coderiders.commons.rest.dto.openweather.WeatherDto;

@FeignClient(name = "openWeatherFeignClient", url = "${open-weather-client.url}")
public interface OpenWeatherFeignClient {
    @GetMapping(value = "/data/2.5/weather?q=Penza,ru&APPID=${open-weather-client.app-id}&lang=ru&units=metric",
            produces = "application/json")
    WeatherDto getWeather();
}
