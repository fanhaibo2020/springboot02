package com.example.entity;

import lombok.Data;

/**
 * @ClassName City
 * @Author fhb
 * @Date 2020/11/1 23:32
 * @Version 1.0
 **/
@Data
public class City {
    private String cityUuid;
    private String cityName;
    private String type;
    private String parentCityUuid;
    private String order;
}
