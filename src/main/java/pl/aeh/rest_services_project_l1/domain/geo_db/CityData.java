package pl.aeh.rest_services_project_l1.domain.geo_db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CityData {
    private String id;
    private String city;
    private String region;
    private String country;
    private Double latitude;
    private Double longitude;
    private Long population;
}
