package ecommerce.travel.utility.dto.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherRptProxyDTO {

    // query status
    private String success;

    private WeatherRptRecordsProxyDTO records;
}
