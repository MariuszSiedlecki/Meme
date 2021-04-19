package pl.controller.Api;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeatherPOJO {
     public Double temp;
     public Double humidity;
     public Double clouds;
     public Double wind;
     public Double pressure;
     public String description;
}
