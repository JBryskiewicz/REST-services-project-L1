package pl.aeh.rest_services_project_l1.domain.open_weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {
    private String base;
    private Main main;
    private int visibility;
    private Wind wind;
    private Clouds clouds;
    private long dt;
    private Sys sys;
    private int timezone;
    private long id;
    private String name;
    private int cod; // Currents on demand

    public void printDetails() {
        System.out.println(
                "base: " + base + "\n" +
                "main: " + main.toString() + "\n" +
                "visibility: " + visibility + "\n" +
                "wind: " + wind.toString() + "\n" +
                "clouds: " + clouds.toString() + "\n" +
                "sys: " + sys.toString() + "\n" +
                "name: " + name + "\n" +
                "cod: " + cod + "\n" +
                "dt: " + dt + "\n" +
                "timezone: " + timezone + "\n" +
                "id: " + id + "\n"
        );
    }
}