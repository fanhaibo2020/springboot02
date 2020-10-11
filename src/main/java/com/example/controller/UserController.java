package com.example.controller;

import com.example.bo.UserBO;
import com.example.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName UserController
 * @Author fhb
 * @Date 2020/9/16 18:34
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
@Api(value="用户Controller",tags = "用户信息相关接口控制类")
public class UserController {

    @Autowired
    private UserBO userBO;

    @GetMapping("findAllUser.do")
    @ApiOperation(value = "查询所有用户信息")
    public List<User> findAllUser() throws Exception{
        List<User> allUser = userBO.findAllUser();
        return allUser;

    }

    @GetMapping("findUserById.do")
    @ApiOperation(value = "根据uuid查询用户信息")
     @ApiParam(name="id",value="用户id",required=true) //设置参数必填
    public User findUserById(String id) throws Exception{
        return userBO.findUserById(id);
    }

    @GetMapping("queryUsersByBatchIds.do")
    @ApiOperation("批量查询用户信息")
    public List<User> queryUsersByBatchIds() throws Exception{
        return userBO.queryUsersByBatchIds();
    }

    @GetMapping("queryUsersByMap.do")
    @ApiOperation("通过map查询用户信息")
    public List<User> queryUsersByMap() throws Exception{
        return userBO.queryUsersByMap();
    }

    @GetMapping("conditionQuery1.do")
    @ApiOperation("条件构造器查询1")
    public List<User> conditionQuery1() throws Exception{
        return userBO.conditionQuery1();
    }

    @GetMapping("conditionQuery2.do")
    @ApiOperation("条件构造器查询2")
    public List<User> conditionQuery2() throws Exception{
        return userBO.conditionQuery2();
    }

    @GetMapping("conditionQuery3.do")
    @ApiOperation("条件构造器查询3")
    public List<User> conditionQuery3() throws Exception{
        return userBO.conditionQuery3();
    }

   @GetMapping("selectByWrapper4.do")
   @ApiOperation("条件构造器查询4")
   public List<User> selectByWrapper4() throws Exception{
        return userBO.selectByWrapper4();
   }

    @GetMapping("selectByWrapper5.do")
    @ApiOperation("条件构造器查询5")
    public List<User> selectByWrapper5() throws Exception{
        return userBO.selectByWrapper5();
    }

    @GetMapping("selectByWrapper6.do")
    @ApiOperation("条件构造器查询6")
    public List<User> selectByWrapper6() throws Exception{
        return userBO.selectByWrapper6();
    }

    /** 插入用户数据**/
    @PostMapping("insertUser.do")
    @ApiOperation("insert用户数据")
    public void insertUser() throws Exception{
        User user = new User();
        user.setUserId("5");
        user.setUserName("刘备");
        user.setGender("女");
        user.setAge("50");
        user.setRemark("我是一个备注信息哦***");
        userBO.insertUser(user);
    }

    @PostMapping("deleteUserById.do")
    @ApiOperation("根据id删除(delete)用户数据")
    public void deleteUserById(String id) throws Exception{
        userBO.deleteUserById(id);
    }

}
