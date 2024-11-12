package api_clima.controller;

import api_clima.entities.Weather;
import api_clima.services.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    WeatherService service;

    @GetMapping
    public ResponseEntity<Weather> getWeatherDetailsByCity(@RequestParam String cityName) {
        Weather weather = service.getWeatherByCity(cityName);
        return ResponseEntity.ok().body(weather);
    }
}
