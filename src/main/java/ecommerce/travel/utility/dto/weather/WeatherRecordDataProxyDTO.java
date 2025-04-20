package ecommerce.travel.utility.dto.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherRecordDataProxyDTO {

    private String datasetDescription;

    private String datasetLanguage;

    private WeatherRecordTimeProxyDTO validTime;

    private String issueTime;

    private String update;
}
