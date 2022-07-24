package com.example.controller;

import com.example.bo.ExcelBO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName ExcelController
 * @Author fhb
 * @Date 2022/6/19 16:20
 * @Version 1.0
 **/
@Slf4j
@RestController
@RequestMapping("/excel")
@Api(value="ExcelController",tags = "Excel导出接口控制类")
public class ExcelController {

    @Resource
    ExcelBO excelBO;

    @GetMapping(value = "exportExcel.do", produces = "application/octet-stream")
    @ApiOperation("导出抽取记录")
    @ApiImplicitParam(name = "businessUnitName", value = "业务单位名称", required = false)
    public void getExtractRecordByExcel(String businessUnitName,HttpServletResponse response) throws Exception{
        excelBO.getExtractRecordByExcel(businessUnitName,response);
    }

}
