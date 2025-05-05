package pl.aeh.rest_services_project_l1.domain.open_weather;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wind {
    private double speed;
    private double deg;
    private double gust;
}
