package com.example.bo;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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
     * SQL语句：SELECT userId,userName,gender,age FROM user WHERE (userName LIKE ? AND gender = ?)
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
     * SQL:SELECT userId,userName,gender,age FROM user WHERE (userName LIKE ? AND age BETWEEN ? AND ? AND city IS NOT NULL)
     * @throws Exception
     */
    public List<User> conditionQuery2() throws Exception{
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(); //条件构造器
        queryWrapper.like("userName","王").between("age",20,40).isNotNull("city"); //这里的key值对应的是数据库中的字段
        List<User> userList = userDao.selectList(queryWrapper);
        return userList;
    }

    /**
     * 运用条件构造器进行查询3
     * SQL：SELECT userId,userName,gender,age FROM user WHERE (userName LIKE ? OR age >= ?) ORDER BY age DESC,userId ASC
     * @return
     * @throws Exception
     */
    public List<User> conditionQuery3() throws Exception{
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(); //条件构造器
        queryWrapper.likeLeft("userName","王").or().ge("age",30).orderByDesc("age")
        .orderByAsc("userId"); //这里的key值对应的是数据库中的字段
        List<User> userList = userDao.selectList(queryWrapper);
        return userList;
    }

    /**
     * 运用条件构造器进行查询4
     * SQL:SELECT userId,userName,gender,age FROM user WHERE (userName LIKE ? AND (age < ? OR city IS NOT NULL))
     * @return
     * @throws Exception
     */
    public List<User> selectByWrapper4() throws Exception{
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("userName","王").and(wq->wq.lt("age",40).or().isNotNull("city"));
        List<User> userList = userDao.selectList(queryWrapper);
        return userList;
    }

    /**
     * 运用条件构造器进行查询5
     * SQL:SELECT userId,userName,gender,age FROM user WHERE (userName LIKE ? AND (age < ? AND age > ? AND city IS NOT NULL))
     * @return
     * @throws Exception
     */
    public List<User> selectByWrapper5() throws Exception{
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("userName","王").and(wq->wq.lt("age",40).gt("age",20).isNotNull("city"));
        List<User> userList = userDao.selectList(queryWrapper);
        return userList;
    }

    /**
     * 运用条件构造器进行查询6
     * SQL:SELECT userId,userName,gender,age FROM user WHERE ((age < ? OR city IS NOT NULL) AND userName LIKE ?)
     * @return
     * @throws Exception
     */
    public List<User> selectByWrapper6() throws Exception{
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.nested(wq->wq.lt("age",40).or().isNotNull("city")).likeRight("userName","王");
        List<User> userList = userDao.selectList(queryWrapper);
        return userList;
    }

    /**
     * SQL：SELECT userId,userName,gender,age FROM user WHERE (userId IN (?,?,?))
     * @return
     * @throws Exception
     */
    public List<User> selectByWrapper7() throws Exception{
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("userId",Arrays.asList(1,2,3));
        List<User> userList = userDao.selectList(queryWrapper);
        return userList;
    }

    /**
     * SQL:SELECT userId,userName,gender,age FROM user WHERE (userId IN (?,?,?,?)) limit 2
     * @return
     * @throws Exception
     */
    public List<User> selectByWrapper8() throws Exception{
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("userId",Arrays.asList(1,2,3,4)).last("limit 2");
        List<User> userList = userDao.selectList(queryWrapper);
        return userList;
    }

    /**
     * 选择指定的字段名，select没有指定的会设置为null！！！（这种实用性应该不大,但也要知道怎么用）
     * SQL:SELECT userId,userName FROM user WHERE (userName LIKE ? AND age < ?)
     * @return
     * @throws Exception
     */
    public List<User> selectByWrapper9() throws Exception{
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("userId","userName").like("userName","王").lt("age",40);
        List<User> userList = userDao.selectList(queryWrapper);
        return userList;
    }

    /**
     *SQL：SELECT userId,userName,gender,age FROM user WHERE userName=? AND age=?
     * @return
     * @throws Exception
     */
    public List<User> selectByWrapperEntity() throws Exception{
        User whereUser = new User();
        whereUser.setUserName("小王");
        whereUser.setAge("20");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(whereUser);
        return userDao.selectList(queryWrapper);
    }

    /**
     * SQL:SELECT userId,userName,gender,age FROM user WHERE (age = ?)
     * @return
     * @throws Exception
     */
    public List<User> selectByWrapperAllEq() throws Exception{
        Map<String,Object> params = new HashMap<>();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        params.put("userName","小王");
        params.put("age","20");
        //下方如果k的值(即下方的v值)不等于"小王","userName"的条件不会添加到sql语句中
        queryWrapper.allEq((k,v)->!k.equals("小王"),params);
        return userDao.selectList(queryWrapper);
    }

    /**
     * SQL：SELECT userId,userName,age FROM user WHERE (userName LIKE ? AND age < ?)
     * @return
     * @throws Exception
     */
    public List<Map<String,Object>> selectByWrapperMaps() throws Exception{
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("userId","userName","age").like("userName","王").lt("age",40);
        return userDao.selectMaps(queryWrapper);
    }

    /**
     *SQL:SELECT userId,userName,gender,age FROM user WHERE (userName LIKE ? AND age < ?)
     * @return
     * @throws Exception
     */
    public List<User> selectLambda1() throws Exception{
//        LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(User::getUserName,"王").lt(User::getAge,40);
        return userDao.selectList(lambdaQueryWrapper);
    }

    /**
     *SQL:SELECT userId,userName,gender,age,city FROM user WHERE (userName LIKE ? AND (age < ? OR city IS NOT NULL))
     * @return
     * @throws Exception
     */
    public List<User> selectLambda2() throws Exception{
        LambdaQueryWrapper<User> lambda = new QueryWrapper<User>().lambda();
        lambda.like(User::getUserName,"王").and(lam->lam.lt(User::getAge,40).or().isNotNull(User::getCity));
        return userDao.selectList(lambda);
    }


    /**
     *
     * @return
     * @throws Exception
     */
    public IPage<User> selectPage() throws Exception{
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge("age",10);
        Page<User> page = new Page<User>(2,3); //当前页，以及每页的条数
        IPage<User> iPage = userDao.selectPage(page,queryWrapper);
        System.out.println("总页数="+iPage.getPages());
        System.out.println("总记录数="+iPage.getTotal());
        List<User> userList = iPage.getRecords();
        System.out.println("userList="+userList);
        return iPage;
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
