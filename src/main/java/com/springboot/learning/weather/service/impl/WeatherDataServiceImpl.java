package com.springboot.learning.weather.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.learning.weather.service.IWeatherDataService;
import com.springboot.learning.weather.vo.WeatherResponse;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service("weatherDataService")
public class WeatherDataServiceImpl implements IWeatherDataService {

    private static transient Log log = LogFactory.getLog(WeatherDataServiceImpl.class);

    private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";

    private static final long TIME_OUT = 60L;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public WeatherResponse getDataByCityId(String cityId) {
        String uri = WEATHER_URI+"citykey="+ cityId;

        return this.doGetWeather(uri);
    }

    @Override
    public WeatherResponse getDataByCityName(String cityName) {
        String uri = WEATHER_URI+ "city=" +cityName;

        return this.doGetWeather(uri);
    }

    private WeatherResponse doGetWeather(String uri){
        String key = uri;
        String strBody = null;
        WeatherResponse resp = null;
        ObjectMapper mapper = new ObjectMapper();
        //先查缓存，缓存有的取缓存中的数据
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
       if(stringRedisTemplate.hasKey(key)){
           log.info("Redis has data.");
           strBody = ops.get(key);
       } else {
           log.info("Redis has not data.");
           //缓存没有，再调用服务接口来获取缓存
           ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
           if(respString.getStatusCodeValue() == 200){
               strBody = respString.getBody();
           }
           ops.set(uri,strBody,TIME_OUT, TimeUnit.SECONDS);

       }

        try{
            resp = mapper.readValue(strBody,WeatherResponse.class);
        }catch (IOException e){
            log.error("error: ",e);
        }
        return resp;
    }
}
