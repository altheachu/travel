package ecommerce.travel.utility.dto.weather;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherRecordTimeProxyDTO {

    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date startTime;

    @DateTimeFormat( pattern = "yyyy-MM-dd HH:mm:ss" )
    private Date endTime;
}
