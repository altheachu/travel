package ecommerce.travel.utility.dto.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherHazardAreasProxyDTO {

    private List<WeatherHazardLocationProxyDTO> location;
}
