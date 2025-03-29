package ecommerce.travel.utility.controller;

import ecommerce.travel.utility.dto.weather.WeatherRptProxyDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@CrossOrigin(value = "http://localhost:8080")
public class UtilityController {

    @GetMapping("/hazard")
    @ApiOperation(value = "Find Today Weather Hazard in next 24 hours")
    public WeatherRptProxyDTO findWeatherHazardin24Hours() throws Exception {
        try{
            WeatherRptProxyDTO rpt = new WeatherRptProxyDTO();
            rpt.setSuccess("false");
            return rpt;
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
