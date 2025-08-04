package ecommerce.travel.utility.service.impl;

import ecommerce.travel.utility.dto.weather.WeatherHazardItemProxyDTO;
import ecommerce.travel.utility.dto.weather.WeatherHazardLocationProxyDTO;
import ecommerce.travel.utility.dto.weather.WeatherRecordProxyDTO;
import ecommerce.travel.utility.dto.weather.WeatherRptProxyDTO;
import ecommerce.travel.utility.service.UtilityService;
import ecommerce.travel.utility.utils.RabbitMqConstant;
import ecommerce.travel.utility.utils.WeatherConstant;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UtilityServiceImpl implements UtilityService {

    @Value("${weather.rpt.key}")
    private String weatherApiKey;

    @Autowired
    private RestTemplate restTemplate;

    public List<String> findWeatherHazardIn24Hours() throws Exception{
        try{
            final String url = "https://opendata.cwa.gov.tw/api/v1/rest/datastore/W-C0033-002?Authorization={authorization}";
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("authorization", weatherApiKey);
            WeatherRptProxyDTO res = restTemplate.getForObject(url, WeatherRptProxyDTO.class, paramMap);
            if(isHarzardQuerySuccess(res)){
                return formatHazardData(res.getRecords().getRecord());
            }
            return null;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    private boolean isHarzardQuerySuccess(WeatherRptProxyDTO weatherRptProxyDTO){
        return weatherRptProxyDTO != null && "true".equals(weatherRptProxyDTO.getSuccess())? true: false;
    }

    private List<String> formatHazardData(List<WeatherRecordProxyDTO> weatherRecordProxyDTOs){

        List<String> resultStrs = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        weatherRecordProxyDTOs = weatherRecordProxyDTOs.stream().filter(record-> {
            try {
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime nowPlus24Hours = now.plusHours(24);
                Date startDate = sdf.parse(record.getDatasetInfo().getValidTime().getEndTime());
                Date endDate = sdf.parse(record.getDatasetInfo().getValidTime().getEndTime());
                LocalDateTime startLocalDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime endLocalDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                boolean isEndIn24 = startLocalDate.isBefore(now) && endLocalDate.isBefore(nowPlus24Hours);
                boolean isStartIn24 = startLocalDate.isAfter(now) && startLocalDate.isBefore(nowPlus24Hours);
                return  isEndIn24 || isStartIn24;
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
        for(WeatherRecordProxyDTO record : weatherRecordProxyDTOs){
            List<WeatherHazardItemProxyDTO> hazard = record.getHazardConditions().getHazards().getHazard();
            String resultStr = "";
            for(WeatherHazardItemProxyDTO info : hazard){
                StringBuilder sb = new StringBuilder("");
                sb.append(WeatherConstant.phenomenaBilinqualMap.getOrDefault(info.getInfo().getPhenomena(), info.getInfo().getPhenomena()));
                sb.append(WeatherConstant.significanceBilinqualMap.getOrDefault(info.getInfo().getSignificance(), info.getInfo().getSignificance()) + "! ");
                for(WeatherHazardLocationProxyDTO location : info.getInfo().getAffectedAreas().getLocation()){
                    sb.append(WeatherConstant.locationBilinqualMap.getOrDefault(location.getLocationName(), location.getLocationName()));
                    sb.append(", ");
                }
                resultStr = sb.substring(0, sb.length() - 2);
            }
            resultStrs.add(resultStr);
        }
        return resultStrs;
    }

    /**
     * check message content in dead letter queue
     * @param message
     */
    /*
    @RabbitListener(queues = RabbitMqConstant.DEAD_LETTER_QUEUE)
    public void handleDLQ(Message message) {
        System.out.println("Headers: " + message.getMessageProperties().getHeaders());
        System.out.println("Payload: " + new String(message.getBody()));
    }
     */
}
