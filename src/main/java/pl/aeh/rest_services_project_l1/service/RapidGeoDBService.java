package pl.aeh.rest_services_project_l1.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.aeh.rest_services_project_l1.domain.geo_db.CitySearch;

@Service
public class RapidGeoDBService {

    private final String REQUEST_URL = "https://wft-geo-db.p.rapidapi.com/v1/geo/cities";
    private final String API_KEY = "f0900d73femsh4aa84ce79f332c8p1eafe2jsnbe2c326ac440";
    private final RestTemplate restTemplate = new RestTemplate();

    public CitySearch getCityByName(String cityName, String countryId) {
        String url = REQUEST_URL + "?namePrefix=" + cityName + "&countryIds=" + countryId;

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-RapidAPI-Key", API_KEY);
        headers.set("X-RapidAPI-Host", "wft-geo-db.p.rapidapi.com");

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<CitySearch> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                requestEntity,
                CitySearch.class
        );

        return response.getBody();

    }
}
