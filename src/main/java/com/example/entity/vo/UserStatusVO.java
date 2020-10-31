package com.example.entity.vo;

import lombok.Data;

/**
 * @ClassName UserStatusVO  多表连接查询的结果对象
 * @Author fhb
 * @Date 2020/10/25 16:34
 * @Version 1.0
 **/
@Data
public class UserStatusVO {

    private String userId;
    private String userName;
    private String gender;
    private String age;
    private String statusId;
    private String statusName;
    private String cityUuid;
    private String cityName;



}
