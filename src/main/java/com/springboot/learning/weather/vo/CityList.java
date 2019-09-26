package com.springboot.learning.weather.vo;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="c")
@XmlAccessorType(XmlAccessType.FIELD)
public class CityList {

    private List<City> cityList;

}
