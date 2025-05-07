package pl.aeh.rest_services_project_l1.service;

import org.springframework.stereotype.Service;
import pl.aeh.rest_services_project_l1.domain.geo_db.CityData;
import pl.aeh.rest_services_project_l1.domain.geo_db.CitySearch;
import pl.aeh.rest_services_project_l1.domain.open_weather.Weather;
import pl.aeh.rest_services_project_l1.domain.response.RegionInfoResponse;

@Service
public class UtilityService {

    public RegionInfoResponse constructRegionInfo(CitySearch city, Weather weather, String cityName) {
        city.getData().forEach(System.out::println);
        CityData cityData = city
                .getData()
                .stream()
                .filter(c -> c.getCity().toLowerCase().equals(cityName))
                .toList()
                .get(0);

        RegionInfoResponse regionInfo = new RegionInfoResponse();

        regionInfo.setCity(cityData.getCity());
        regionInfo.setRegion(cityData.getRegion());
        regionInfo.setCountry(cityData.getCountry());
        regionInfo.setTemp(weather.getMain().getTemp());
        regionInfo.setFeels_like(weather.getMain().getFeels_like());
        regionInfo.setPressure(weather.getMain().getPressure());
        regionInfo.setWindSpeed(weather.getWind().getSpeed());
        regionInfo.setVisibility(weather.getVisibility());
        regionInfo.setSunrise(weather.getSys().getSunrise());
        regionInfo.setSunset(weather.getSys().getSunset());

        return regionInfo;
    }
}
