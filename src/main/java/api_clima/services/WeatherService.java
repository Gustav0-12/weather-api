package api_clima.services;

import api_clima.entities.Weather;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${api.spring.key}")
    private String KEY;

    private String getUrl() {
        return "https://api.openweathermap.org/data/2.5/weather?q={cityName}&appid=" + KEY + "&units=metric";
    }

    public Weather getWeatherByCity(String cityName) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Weather> resp = restTemplate.getForEntity(getUrl(), Weather.class, cityName);
        return resp.getBody();
    }
}
