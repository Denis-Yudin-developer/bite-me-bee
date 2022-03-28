package ru.coderiders.Library.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import ru.coderiders.Library.rest.dto.openweather.WeatherDto;

@FeignClient(name = "openWeatherFeignClient", url = "https://api.openweathermap.org")
public interface OpenWeatherFeignClient {

    @GetMapping(value = "/data/2.5/weather?q=Penza,ru&APPID=${open-weather-appid}&lang=ru&units=metric", produces = "application/json")
    WeatherDto getWeather();
}
