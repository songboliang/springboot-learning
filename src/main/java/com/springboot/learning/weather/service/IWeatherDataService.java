package com.springboot.learning.weather.service;

import com.springboot.learning.weather.vo.WeatherResponse;

public interface IWeatherDataService {

    WeatherResponse getDataByCityId(String cityId);


    WeatherResponse getDataByCityName(String cityName);

}
