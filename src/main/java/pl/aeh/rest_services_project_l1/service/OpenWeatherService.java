package pl.aeh.rest_services_project_l1.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.aeh.rest_services_project_l1.domain.open_weather.Weather;

@Service
public class OpenWeatherService {

    private final String REQUEST_URL = "https://api.openweathermap.org/data/2.5/weather?q=";
    private final String API_KEY = "940c40b1ca9ae7e0a2f5d4c31983d2b5";
    private final RestTemplate restTemplate = new RestTemplate();

    private String getFullUrl(String city) {
        return REQUEST_URL + city + "&units=metric&appid=" + API_KEY;
    }

    public Weather getWeather(String city) {
        String url = this.getFullUrl(city);
        return restTemplate.getForObject(url, Weather.class);
    }

}
