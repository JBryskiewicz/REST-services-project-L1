package pl.aeh.rest_services_project_l1.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.aeh.rest_services_project_l1.domain.open_weather.Weather;

// TODO currently testing options...

@Service
public class OpenWeatherService {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_KEY = "940c40b1ca9ae7e0a2f5d4c31983d2b5";

    public void printFirstWeatherRecord(String city) {
        try {
            String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city +
                    "&units=metric&appid=" + API_KEY;
            Weather weather = restTemplate.getForObject(url, Weather.class);

            if (weather != null) {
                weather.printDetails();
            } else {
                System.out.println("Brak danych pogodowych.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
