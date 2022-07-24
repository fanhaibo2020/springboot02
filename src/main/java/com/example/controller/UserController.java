package com.example.controller;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.bo.UserBO;
import com.example.entity.User;
import com.example.entity.vo.UserStatusVO;
import com.example.utils.JWTUtils;
import com.example.utils.PDFTemplateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserController
 * @Author fhb
 * @Date 2020/9/16 18:34
 * @Version 1.0
 **/
@Slf4j
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

    @GetMapping("/token/login.do")
    public Map<String,Object> login(String userName,String pwd) throws Exception{
        Map<String,Object> map = new HashMap<>();
        try{
            User user = userBO.login(userName,pwd);
            //生成token令牌
            Map<String,String> payload = new HashMap<>();
            payload.put("name",user.getUserName());
            payload.put("age",user.getAge());
            String token = JWTUtils.getToken(payload);
            map.put("state",true);
            map.put("msg","认证成功");
            map.put("token",token); //响应token,因为要存储在客户端,每次发请求都要携带它
        }catch(Exception e){
            map.put("state",false);
            map.put("msg",e.getMessage());
        }
        return map;
    }

    //
    @PostMapping("testToken.do")
    public Map<String,Object> testToken(String token) throws Exception{
        log.info("当前token为：{}",token);
        Map<String,Object> map = new HashMap<>();
        try{
            DecodedJWT verify = JWTUtils.getTokenInfo(token);
            map.put("state",true);
            map.put("msg","请求成功！");
            return map;
        }catch (SignatureVerificationException e){
            e.printStackTrace();
            map.put("msg","无效签名！");
        }catch (TokenExpiredException e){
            e.printStackTrace();
            map.put("msg","token过期！");
        }catch(AlgorithmMismatchException e){
            e.printStackTrace();
            map.put("msg","token算法不一致！");
        }catch (Exception e){
            e.printStackTrace();
            map.put("msg","token无效！");
        }
        map.put("state",false);
        return map;
    }

    @PostMapping("downloadPDF.do")
    @ApiOperation("下载pdf文件")
    public ResponseEntity<byte[]> exportPdf(HttpServletResponse response) throws Exception{
        ByteArrayOutputStream baos = null;
        OutputStream out = null;
        try {
            // 模板中的数据，实际运用从数据库中查询
            Map<String,Object> data = new HashMap<>();
            List<Map<String,Object>> detailList = new ArrayList<>();
            for(int i=0;i<100;i++){
                Map<String,Object> map = new HashMap<>();
                map.put("userName","张三"+(i+1));
                map.put("gender","男");
                map.put("idCardNo","88888888888888888"+(i+1));
                map.put("address","湖南省长沙市开福区四方坪街道三一大道第" + i + "号的xx栋xxx楼层xxx室");
                detailList.add(map);
            }
            data.put("detailList", detailList);
            baos = PDFTemplateUtil.createPDF(data, "pdf测试模板.ftl");
//            // 设置响应消息头，告诉浏览器当前响应是一个下载文件
//            response.setContentType( "application/x-msdownload");
//            // 告诉浏览器，当前响应数据要求用户干预保存到文件中，以及文件名是什么 如果文件名有中文，必须URL编码
//            String fileName = URLEncoder.encode("住房公示报告.pdf", "UTF-8");
//            response.setHeader( "Content-Disposition", "attachment;filename=" + fileName);
//            out = response.getOutputStream();
//            baos.writeTo(out);
//            baos.close();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment",URLEncoder.encode("公寓住房公示.pdf","UTF-8"));
            return new ResponseEntity<byte[]>(baos.toByteArray(),headers,HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("导出失败：" + e.getMessage());
        } finally{
            if(baos != null){
                baos.close();
            }
            if(out != null){
                out.close();
            }
        }
    }
}
