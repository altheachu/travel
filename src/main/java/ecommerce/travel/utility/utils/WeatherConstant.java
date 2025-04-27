package ecommerce.travel.utility.utils;

import java.util.HashMap;
import java.util.Map;

public class WeatherConstant {

    public static Map<String, String> phenomenaBilinqualMap = Map.of(
            "濃霧","Heavy Fog",
            "大雨","Heavy Rain",
            "豪雨","Extremely Heavy Rain",
            "大豪雨","Torrential Rain",
            "超大豪雨","Extremely Torrential Rain",
            "陸上強風","Strong Wind",
            "颱風","Typhoon"
    );

    public static Map<String, String> significanceBilinqualMap = Map.of(
            "警報","Warning",
            "特報","Advisory"
    );

    public static Map<String, String> locationBilinqualMap;

    static {
        locationBilinqualMap = new HashMap<>();
        locationBilinqualMap.put("臺北市","Taipei City");
        locationBilinqualMap.put("新北市","New Taipei City");
        locationBilinqualMap.put("桃園市","Taoyuan City");
        locationBilinqualMap.put("臺中市","Taichung City");
        locationBilinqualMap.put("臺南市","Tainan City");
        locationBilinqualMap.put("高雄市","Kaohsiung City");
        locationBilinqualMap.put("基隆市","Keelung City");
        locationBilinqualMap.put("新竹市","Hsinchu City");
        locationBilinqualMap.put("嘉義市","Chiayi City");
        locationBilinqualMap.put("新竹縣","Hsinchu County");
        locationBilinqualMap.put("苗栗縣","Miaoli County");
        locationBilinqualMap.put("彰化縣","Changhua County");
        locationBilinqualMap.put("南投縣","Nantou County");
        locationBilinqualMap.put("雲林縣","Yunlin County");
        locationBilinqualMap.put("嘉義縣","Chiayi County");
        locationBilinqualMap.put("屏東縣","Pingtung County");
        locationBilinqualMap.put("宜蘭縣","Yilan County");
        locationBilinqualMap.put("花蓮縣","Hualien County");
        locationBilinqualMap.put("臺東縣","Taitung County");
        locationBilinqualMap.put("澎湖縣","Penghu County");
        locationBilinqualMap.put("金門縣","Kinmen County");
        locationBilinqualMap.put("連江縣","Lienchiang County");
        locationBilinqualMap.put("恆春半島","Hengchun Peninsula");
        locationBilinqualMap.put("蘭嶼綠島","Orchid Island, Green Island");
    }
}
