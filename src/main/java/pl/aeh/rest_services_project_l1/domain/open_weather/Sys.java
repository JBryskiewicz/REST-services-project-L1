package pl.aeh.rest_services_project_l1.domain.open_weather;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sys {
    private int type;
    private long id;
    private String country;
    public long sunrise;
    public long sunset;
}
