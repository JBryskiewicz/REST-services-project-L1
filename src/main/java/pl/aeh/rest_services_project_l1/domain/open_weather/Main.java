package pl.aeh.rest_services_project_l1.domain.open_weather;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Main {
    private double temp;
    private double feels_like;
    private double temp_min;
    private double temp_max;
    private int pressure;
    private int humidity;
}
