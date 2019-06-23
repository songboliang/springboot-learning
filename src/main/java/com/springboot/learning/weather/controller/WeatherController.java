package com.springboot.learning.weather.controller;

import com.springboot.learning.weather.service.IWeatherDataService;
import com.springboot.learning.weather.vo.WeatherResponse;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 *
 */

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private IWeatherDataService weatherDataService;

    @GetMapping("/cityId/{cityId}")
    public WeatherResponse getWeatherByCityId(@PathVariable("cityId")String cityId){
        return  weatherDataService.getDataByCityId(cityId);
    }

    @GetMapping("/cityName/{cityName}")
    public WeatherResponse getWeatherByCityName(@PathVariable("cityName")String cityName){
        return weatherDataService.getDataByCityName(cityName);
    }


}
