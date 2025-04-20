package ecommerce.travel.utility.controller;

import ecommerce.travel.utility.dto.weather.WeatherRptProxyDTO;
import ecommerce.travel.utility.service.UtilityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/weather")
@CrossOrigin(value = "http://localhost:8080")
public class UtilityController {

    @Autowired
    private UtilityService utilityService;

    @GetMapping("/hazard")
    @ApiOperation(value = "Find Today Weather Hazard in next 24 hours")
    public List<String> findWeatherHazardin24Hours() throws Exception {
        try{
            return utilityService.findWeatherHazardIn24Hours();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
