package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * 这里的mapper和dao层的作用是一样的，就类似于bo层和service层
 * @Author fhb
 * @Date 2020/9/20 16:30
 * @Version 1.0
 **/
@Repository  //该注解可以不要，只是bo层注入时会有红色提示不能注入，但不影响使用
public interface UserMapper extends BaseMapper<User> {

}
