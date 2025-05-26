package pl.aeh.rest_services_project_l1.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.aeh.rest_services_project_l1.domain.geo_db.CitySearch;
import pl.aeh.rest_services_project_l1.domain.open_weather.Weather;
import pl.aeh.rest_services_project_l1.domain.response.RegionInfoResponse;
import pl.aeh.rest_services_project_l1.service.app.OpenWeatherService;
import pl.aeh.rest_services_project_l1.service.app.RapidGeoDBService;
import pl.aeh.rest_services_project_l1.service.app.UtilityService;

@RestController()
@AllArgsConstructor
@RequestMapping("/api/main")
public class AppController {

    private final OpenWeatherService openWeatherService;
    private final RapidGeoDBService rapidGeoDBService;
    private final UtilityService utilityService;

    @GetMapping("/check")
    public ResponseEntity<Void> check() {
       return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getLocationData/{cityName}/{countryId}")
    public ResponseEntity<RegionInfoResponse> getLocationData(@PathVariable String cityName, @PathVariable String countryId) {

        CitySearch foundCity = this.rapidGeoDBService.getCityByName(cityName, countryId);
        Weather weather = this.openWeatherService.getWeather(cityName);

        if (foundCity == null || weather == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        RegionInfoResponse regionInfoResponse = this.utilityService.constructRegionInfo(foundCity, weather, cityName);

        return new ResponseEntity<>(regionInfoResponse, HttpStatus.OK);
    }
}
