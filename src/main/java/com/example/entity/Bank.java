package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @ClassName Bank
 * @Author fhb
 * @Date 2020/11/1 23:35
 * @Version 1.0
 **/
@Data
public class Bank {
    @TableId("userUuid")
    private String userUuid;
    @TableField("userName")
    private String userName;
    @TableField("money")
    private String money;
}
