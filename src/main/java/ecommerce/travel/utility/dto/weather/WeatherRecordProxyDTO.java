package ecommerce.travel.utility.dto.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherRecordProxyDTO {

    private WeatherRecordDataProxyDTO datasetInfo;

    private WeatherRecordContentsProxyDTO contents;

    private WeatherRecordHazardProxyDTO hazardCondition;
}
