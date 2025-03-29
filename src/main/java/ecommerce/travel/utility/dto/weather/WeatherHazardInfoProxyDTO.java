package ecommerce.travel.utility.dto.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherHazardInfoProxyDTO {

    private String language;
    private String phenomena;
    private String significance;
    private WeatherHazardAreasProxyDTO affectedAreas;
}
