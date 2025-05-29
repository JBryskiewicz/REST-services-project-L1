package pl.aeh.rest_services_project_l1.web;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.aeh.rest_services_project_l1.domain.geo_db.CitySearch;
import pl.aeh.rest_services_project_l1.domain.open_weather.Weather;
import pl.aeh.rest_services_project_l1.domain.response.RegionInfoResponse;
import pl.aeh.rest_services_project_l1.domain.user_views.UserView;
import pl.aeh.rest_services_project_l1.service.app.OpenWeatherService;
import pl.aeh.rest_services_project_l1.service.app.RapidGeoDBService;
import pl.aeh.rest_services_project_l1.service.app.UserViewService;
import pl.aeh.rest_services_project_l1.service.app.UtilityService;

import java.util.List;

@RestController()
@AllArgsConstructor
@RequestMapping("/api/main")
public class AppController {

    private final OpenWeatherService openWeatherService;
    private final RapidGeoDBService rapidGeoDBService;
    private final UtilityService utilityService;
    private final UserViewService userViewService;

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

    @GetMapping("/getAllForUser/{id}")
    public ResponseEntity<List<UserView>> getAllForUser(@PathVariable String id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {
            Long userId = Long.parseLong(id);
            List<UserView> views = this.userViewService.getAllUserViews(userId);
            return new ResponseEntity<>(views, HttpStatus.OK);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/saveView")
    public ResponseEntity<UserView> saveUserView(@RequestBody UserView userView) {
        if (userView == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserView view = this.userViewService.saveUserView(userView);
        return ResponseEntity.ok(view);
    }

    @PutMapping("/editView")
    public ResponseEntity<UserView> editUserView(@RequestBody UserView userView) {
        if (userView == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserView view = this.userViewService.saveUserView(userView);
        return ResponseEntity.ok(view);
    }

    @GetMapping("/getUserView/{id}")
    public ResponseEntity<UserView> getUserView(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(this.userViewService.getUserView(id));
    }

    @DeleteMapping("/deleteUserView/{id}")
    public ResponseEntity<Void> deleteUserView(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        this.userViewService.deleteUserView(id);
        return ResponseEntity.noContent().build();
    }
}
