package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bo.UserBO;
import com.example.entity.User;
import com.example.entity.vo.UserStatusVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

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

    @GetMapping("selectByWrapper7.do")
    @ApiOperation("条件构造器查询7")
    public List<User> selectByWrapper7() throws Exception{
        return userBO.selectByWrapper7();
    }

    @GetMapping("selectByWrapper8.do")
    @ApiOperation("条件构造器查询8")
    public List<User> selectByWrapper8() throws Exception{
        return userBO.selectByWrapper8();
    }

    @GetMapping("selectByWrapper9.do")
    @ApiOperation("条件构造器查询9")
    public List<User> selectByWrapper9() throws Exception{
        return userBO.selectByWrapper9();
    }

    @GetMapping("selectByWrapperEntity.do")
    @ApiOperation("条件构造器查询10")
    public List<User> selectByWrapperEntity() throws Exception{
        return userBO.selectByWrapperEntity();
    }

    @GetMapping("selectByWrapperAllEq.do")
    @ApiOperation("条件构造器查询11")
    public List<User> selectByWrapperAllEq() throws Exception{
        return userBO.selectByWrapperAllEq();
    }

    @GetMapping("selectByWrapperMaps.do")
    @ApiOperation("条件构造器查询12")
    public List<Map<String,Object>> selectByWrapperMaps() throws Exception{
        return userBO.selectByWrapperMaps();
    }

    @GetMapping("selectLambda1.do")
    @ApiOperation("lambda表达式查询1")
    public List<User> selectLambda1() throws Exception{
        return userBO.selectLambda1();
    }

    @GetMapping("selectLambda2.do")
    @ApiOperation("lambda表达式查询2")
    public List<User> selectLambda2() throws Exception{
        return userBO.selectLambda2();
    }

    @GetMapping("selectPage.do")
    @ApiOperation("分页查询")
    public IPage<User> selectPage() throws Exception{
        return userBO.selectPage();
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

    @PostMapping("batchInsert.do")
    @ApiOperation("批量insert用户数据")
    public void batchInsert() throws Exception{
        userBO.batchInsert();
    }

    @PostMapping("deleteUserById.do")
    @ApiOperation("根据id删除(delete)用户数据")
    public void deleteUserById(String id) throws Exception{
        userBO.deleteUserById(id);
    }

    @PostMapping("deleteByMap.do")
    @ApiOperation("deleteByMap删除用户数据")
    public void deleteByMap() throws Exception{
        userBO.deleteByMap();
    }

    @PostMapping("deleteBatchIds.do")
    @ApiOperation("deleteBatchIds删除用户数据")
    public void deleteBatchIds() throws Exception{
        userBO.deleteBatchIds();
    }

    @PostMapping("deleteByWrapper.do")
    @ApiOperation("deleteByWrapper删除用户数据")
    public void deleteByWrapper() throws Exception{
        userBO.deleteByWrapper();
    }

    @PostMapping("updateById.do")
    @ApiOperation("updateById修改数据")
    public void updateById() throws Exception{
        userBO.updateById();
    }

    @PostMapping("updateByWrapper1.do")
    @ApiOperation("updateByWrapper1修改数据")
    public void updateByWrapper1() throws Exception{
        userBO.updateByWrapper1();
    }

    @PostMapping("updateByWrapper2.do")
    @ApiOperation("updateByWrapper2修改数据")
    public void updateByWrapper2() throws Exception{
        userBO.updateByWrapper2();
    }

    @PostMapping("updateByWrapperLambda.do")
    @ApiOperation("updateByWrapperLambda修改数据")
    public void updateByWrapperLambda() throws Exception{
        userBO.updateByWrapperLambda();
    }

    @GetMapping("queryBySelectAnno1.do")
    @ApiOperation("Select注解查询1")
    public List<User> queryBySelectAnno1() throws Exception{
        return userBO.queryBySelectAnno1();
    }

    @GetMapping("jointQueryBySelectAnno2.do")
    @ApiOperation("Select注解多表连接查询")
    public List<UserStatusVO> jointQueryBySelectAnno2() throws Exception{
        return userBO.jointQueryBySelectAnno2();
    }

    @GetMapping("jointConditionQueryBySelectAnno3.do")
    @ApiOperation("Select注解多表带条件连接查询")
    public List<UserStatusVO> jointConditionQueryBySelectAnno3() throws Exception{
        return userBO.jointConditionQueryBySelectAnno3();
    }

    @GetMapping("jointConditionQueryBySelectAnno4.do")
    @ApiOperation("Select注解多表带条件连接、分页查询")
    public IPage<UserStatusVO> jointConditionPageQueryBySelectAnno4(Page paging) throws Exception{
        return userBO.jointConditionPageQueryBySelectAnno4(paging);
    }

}
