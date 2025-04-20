package ecommerce.travel.utility.service;

import ecommerce.travel.utility.entity.Eventlog;

import java.util.List;

public interface UtilityService {

    /*
    description: find weather hazard broadcast in 24 hours
    author: Althea Chu
    lastModified: 2025-03-29
    */
    List<String> findWeatherHazardIn24Hours() throws Exception;
}
