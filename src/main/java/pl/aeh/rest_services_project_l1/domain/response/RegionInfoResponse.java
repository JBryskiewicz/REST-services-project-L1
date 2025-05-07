package pl.aeh.rest_services_project_l1.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegionInfoResponse {
    // From GeoDB
    private String city;
    private String region;
    private String country;

    // From OpenWeather
    private double temp;
    private double feels_like;
    private int pressure;
    private double windSpeed; // kmh
    private int cloudDensity; // %
    private int visibility;
    private long sunrise; // millis from epoch
    private long sunset; // millis from epoch

}
