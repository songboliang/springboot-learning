package com.springboot.learning.weather.vo;

import java.util.List;

/**
 *
 * 天气信息
 *
 * @Author songbl
 */

public class Weather {

    private String city;
    private String api;
    private String ganmao;            //remindInfo
    private String wendu;             //temperature
    private YesterDay yesterday;
    private List<Forecast> forecast;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    public YesterDay getYesterday() {
        return yesterday;
    }

    public void setYesterday(YesterDay yesterday) {
        this.yesterday = yesterday;
    }

    public List<Forecast> getForecast() {
        return forecast;
    }

    public void setForecast(List<Forecast> forecast) {
        this.forecast = forecast;
    }
}
