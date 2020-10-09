package com.example.bo;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName UserBO
 * @Author fhb
 * @Date 2020/9/20 16:17
 * @Version 1.0
 **/
@Service
public class UserBO {

    @Autowired
    private UserMapper userDao;

    //查询所有用户信息
    public List<User> findAllUser() throws Exception{
        return userDao.selectList(null);
    }

    /**
     * 根据id查询用户信息
     * @param id
     * @return
     * @throws Exception
     */
    public User findUserById(String id) throws Exception{
       return userDao.selectById(id);  //selectById是baseMapper中已定义好的方法
    }

    /**
     * 批量查询用户信息
     * @return
     * @throws Exception
     */
    public List<User> queryUsersByBatchIds() throws Exception{
        List<String> list = Arrays.asList("1","3");
       return userDao.selectBatchIds(list);
    }

    /**
     * 通过map查询用户信息
     * @return
     * @throws Exception
     */
    public List<User> queryUsersByMap() throws Exception{
       Map<String,Object> map = new HashMap<>();
       map.put("gender","女"); //键值对应数据库中的字段，而不是实体类中的字段
       return userDao.selectByMap(map);
    }

    /**
     * 运用条件构造器进行查询1
     * @return
     * @throws Exception
     */
    public List<User> conditionQuery1() throws Exception{
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(); //条件构造器
        queryWrapper.like("userName","李").eq("gender","男"); //这里的key值对应的是数据库中的字段
        List<User> userList = userDao.selectList(queryWrapper);
        return userList;
    }

    /**
     * 运用条件构造器进行查询2
     * @return
     * @throws Exception
     */
    public List<User> conditionQuery2() throws Exception{
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(); //条件构造器
        queryWrapper.like("userName","李").between("age",20,40).isNotNull("city"); //这里的key值对应的是数据库中的字段
        List<User> userList = userDao.selectList(queryWrapper);
        return userList;
    }

    /**
     * 运用条件构造器进行查询3
     * @return
     * @throws Exception
     */
    public List<User> conditionQuery3() throws Exception{
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(); //条件构造器
        queryWrapper.likeLeft("userName","李").or().ge("age",30).orderByDesc("age")
        .orderByAsc("userId"); //这里的key值对应的是数据库中的字段
        List<User> userList = userDao.selectList(queryWrapper);
        return userList;
    }

    /** 添加用户数据**/
    public int insertUser(User user) throws Exception{
      int row = userDao.insert(user);
      System.out.println("添加数据影响行数="+row);
      return row;
    }

    /** 根据id删除用户数据**/
    public int deleteUserById(String id) throws Exception{
       int row = userDao.deleteById(id);
        System.out.println("删除数据影响行数="+row);
        return row;
    }

}
