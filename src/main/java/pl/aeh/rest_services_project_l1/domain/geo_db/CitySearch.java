package pl.aeh.rest_services_project_l1.domain.geo_db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CitySearch {
    private List<CityData> data;
    private Metadata metadata;
}
