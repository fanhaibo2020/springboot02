package com.example.entity;

import lombok.Data;

/**
 * @ClassName UserStatus
 * @Author fhb
 * @Date 2020/11/1 23:30
 * @Version 1.0
 **/
@Data
public class UserStatus {
    private String statusId;
    private String userId;
    private String statusName;
    private String cityUuid;
}
