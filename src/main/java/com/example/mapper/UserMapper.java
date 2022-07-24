package com.example.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.User;
import com.example.entity.vo.UserStatusVO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 这里的mapper和dao层的作用是一样的，就类似于bo层和service层
 * @Author fhb
 * @Date 2020/9/20 16:30
 * @Version 1.0
 **/
@Repository  //该注解可以不要，只是bo层注入时会有红色提示不能注入，但不影响使用
public interface UserMapper extends BaseMapper<User> {

    @Select("select userId,userName,city from User where userId=#{uid}")
    List<User> queryBySelectAnno1(@Param("uid") String id);  //此处的uid是参数id的别名,与上面的参数对应

    @Select("SELECT * FROM USER a LEFT JOIN userstatus b ON a.userId = b.userId\n" +
            "\tLEFT JOIN city c ON b.cityUuid = c.cityUuid;")
    //查询的结果*的全体对象的结果集为UserStatusVO
    List<UserStatusVO> jointQueryBySelectAnno2();

    //自定义SQL,ew对应的就是Constants.WRAPPER，ew.SqlSelect：所需要查找的字段,ew.SqlSelect：所需要查找的字段
    @Select("SELECT ${ew.sqlSelect} FROM USER a LEFT JOIN userstatus b ON a.userId = b.userId\n"+
       "\tLEFT JOIN city c ON b.cityUuid = c.cityUuid ${ew.customSqlSegment}")
    List<UserStatusVO> jointConditionQueryBySelectAnno3(@Param(Constants.WRAPPER) QueryWrapper wrapper);

    @Select("SELECT ${ew.sqlSelect} FROM USER a LEFT JOIN userstatus b ON a.userId = b.userId\n"+
            "\tLEFT JOIN city c ON b.cityUuid = c.cityUuid ${ew.customSqlSegment}")
    IPage<UserStatusVO> jointConditionPageQueryBySelectAnno4(Page paging, @Param(Constants.WRAPPER) QueryWrapper wrapper);

}
