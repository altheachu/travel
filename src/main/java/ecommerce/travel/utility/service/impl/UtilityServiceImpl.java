package ecommerce.travel.utility.service.impl;

import ecommerce.travel.utility.dto.weather.WeatherHazardItemProxyDTO;
import ecommerce.travel.utility.dto.weather.WeatherHazardLocationProxyDTO;
import ecommerce.travel.utility.dto.weather.WeatherRecordProxyDTO;
import ecommerce.travel.utility.dto.weather.WeatherRptProxyDTO;
import ecommerce.travel.utility.service.UtilityService;
import ecommerce.travel.utility.utils.WeatherConstant;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.util.*;

@Service
public class UtilityServiceImpl implements UtilityService {

    @Value("${weather.rpt.key}")
    private String weatherApiKey;

    private RestTemplate createUnsafeRestTemplate() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    }
            };
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
            requestFactory.setHttpClient(org.apache.http.impl.client.HttpClients.custom()
                    .setSSLContext(sslContext)
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .build());
            return new RestTemplate(requestFactory);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<String> findWeatherHazardIn24Hours() throws Exception{
        final String url = "https://opendata.cwa.gov.tw/api/v1/rest/datastore/W-C0033-002?Authorization={authorization}";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("authorization", "CWA-6F4180A1-F987-41D2-90BF-593FB8E407CA");
        WeatherRptProxyDTO res = createUnsafeRestTemplate().getForObject(url, WeatherRptProxyDTO.class, paramMap);
        if(isHarzardQuerySuccess(res)){
            return formatHazardData(res.getRecords().getRecord());
        }
        return null;
    }

    private boolean isHarzardQuerySuccess(WeatherRptProxyDTO weatherRptProxyDTO){
        return weatherRptProxyDTO != null && "true".equals(weatherRptProxyDTO.getSuccess())? true: false;
    }

    private List<String> formatHazardData(List<WeatherRecordProxyDTO> weatherRecordProxyDTOs){

        List<String> resultStrs = new ArrayList<>();

        for(WeatherRecordProxyDTO record : weatherRecordProxyDTOs){
            String startDate = record.getDatasetInfo().getValidTime().getStartTime();
            String endDate = record.getDatasetInfo().getValidTime().getEndTime();
            //TODO in 24 hours check
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
}
