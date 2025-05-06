package pl.aeh.rest_services_project_l1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.aeh.rest_services_project_l1.service.OpenWeatherService;
import pl.aeh.rest_services_project_l1.service.RapidGeoDBService;

@SpringBootApplication
public class RestServicesProjectL1Application {

	public static void main(String[] args) {
		SpringApplication.run(RestServicesProjectL1Application.class, args);
	}

	@Bean
	CommandLineRunner run(OpenWeatherService weatherService, RapidGeoDBService rapidGeoDBService) {
		return args -> {
			weatherService.getWeather("Warsaw").printDetails();
			rapidGeoDBService.searchCities();
		};
	}
}
