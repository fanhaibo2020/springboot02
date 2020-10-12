package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName User
 * @Author fhb
 * @Date 2020/9/13 15:29
 * @Version 1.0
 **/
@Data
@TableName("user")  //指定数据库中的表名
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId("userId")  //指定数据库中的表名的主键及主键字段名
    private String userId;

    @TableField("userName")
    private String userName;

    @TableField("gender")
    private String gender;

    @TableField("age")
    private String age;

    @TableField(value="city")
    private String city;

    @TableField(exist = false)  //表明不是数据库中的字段
    private String remark;  //transient解决数据库表中没有该字段插入时报错，该字段不参与序列化，不建议使用，使用上面带注解的形式

}
