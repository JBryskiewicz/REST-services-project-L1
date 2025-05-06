package pl.aeh.rest_services_project_l1.domain.geo_db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Metadata {
    private int currentOffset;
    private int totalCount;
}
