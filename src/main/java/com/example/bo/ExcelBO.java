package com.example.bo;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.entity.User;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ExcelBO
 * @Author fhb
 * @Date 2022/6/19 16:25
 * @Version 1.0
 **/
@Service
public class ExcelBO {

    @Resource
    UserBO userBO;

    /**
     * excel获取抽取记录
     * @param businessUnitName 业务单位名称
     * @return
     * @throws Exception
     */
    public void getExtractRecordByExcel(String businessUnitName,HttpServletResponse response) throws Exception{
//        QueryWrapper<JSONObject> queryWrapper=new QueryWrapper<>();
//        queryWrapper.like(StringUtils.isNotBlank(businessUnitName), "business_unit_name", businessUnitName);
//        queryWrapper.orderByDesc("extraction_time"); //抽取时间降序排列
//        List<ProjectExtractVO> projectExtractVOList = projectContractExtractMapperExt.getProjectContractExtractByList(queryWrapper);
//        getConfirmAttendCompany(projectExtractVOList); //包装抽中公司的数据
          List<User> usersList= userBO.findAllUser();
          exportExcel(usersList,response);

    }

    private void exportExcel(List<User> usersList,HttpServletResponse response) throws Exception{
        //表头标题
        String[] title = {"项目名称","建设单位","项目编号","工作内容","抽取地点","抽取时间","预算金额(单位:元)","中标承包商","业务单位"};
        //导出excel表名
        String fileName = "测试表单名称.xls";
        //内容
        String[][] content = new String[usersList.size()][title.length];

        getContent(usersList,content); //组装数据

        HSSFWorkbook wb = new HSSFWorkbook(); //excel文档对象
        HSSFSheet sheet = wb.createSheet(fileName); //excel的sheet
        HSSFCellStyle cellStyle = wb.createCellStyle();  //锁定样式
        cellStyle.setLocked(true); //设置全表锁定
        sheet.protectSheet("123456"); //设置[审阅]编辑受限中的密码,不加这个的话,编辑受限不会生效的

//        sheet.autoSizeColumn(0); //自适应列宽(这个为什么不生效!!!)
        //设置单元格的宽度
//        sheet.setColumnWidth(0,27*256); //第1列的宽度
//        sheet.setColumnWidth(1,27*256); //第2列的宽度
//        sheet.setColumnWidth(2,27*256); //第3列的宽度
//        sheet.setColumnWidth(3,27*256); //第4列的宽度
//        sheet.setColumnWidth(4,27*256); //第5列的宽度
//        sheet.setColumnWidth(5,27*256); //第6列的宽度
//        sheet.setColumnWidth(6,27*256); //第7列的宽度
//        sheet.setColumnWidth(7,27*256); //第8列的宽度
//        sheet.setColumnWidth(8,27*256); //第9列的宽度

        //在锁定了sheet之后, 会发现一个问题, 就是列宽都不能改变了
        Map<Integer, Integer> maxWidth = new HashMap<>(); //存储最大列宽

        //设置字体
        Font font = wb.createFont();
        font.setFontName("宋体"); //设置字体
        font.setFontHeightInPoints((short)12); //设置字体大小
        font.setBold(true); //粗体
        cellStyle.setFont(font);  //将样式设置应用具体单元格
        //输出表头
        HSSFRow row = sheet.createRow(0); //excel的行(第一行的表头)
        HSSFCell cell = null; //excel的单元格
        //填充表头数据
        for(int i=0;i< title.length;i++){
            cell = row.createCell(i);
            cell.setCellValue(title[i]); //向单元格中设置值
            cell.setCellStyle(cellStyle); //设置单元格样式
            row.setHeight((short)320); //设置行高
            maxWidth.put(i, title[i].getBytes().length * 256+256);
            if(i==4){
                System.out.println("抽取地点："+title[i].getBytes().length);
            }

        }
        //填充内容参数
        for(int i = 0;i<content.length;i++){
            HSSFRow row1 = sheet.createRow(i+1);
            //j表示第xx列
            for (int j=0;j< content[i].length;j++){
                  cell = row1.createCell(j);
                  cell.setCellValue(content[i][j]); //向单元格中设置值
                  cell.setCellStyle(cellStyle); //设置单元格样式
                  row1.setHeight((short)320); //设置行高
                  //列宽参数
                  int length = cell.getStringCellValue().getBytes().length * 256+ 256;
//                  if(j==4){
//                      System.out.println("抽取地点内容="+cell.getStringCellValue().getBytes().length);
//                  }
                  maxWidth.put(j, Math.max(length, maxWidth.get(j)));
            }
        }
        //设置列宽
        setColumnWidth(sheet,maxWidth);

        try{
            String exportFileName = URLEncoder.encode(fileName, "UTF-8");
            response.setCharacterEncoding("UTF-8");
//            response.setContentType("application/vnd.ms-excel;charset=utf-8"); //设置contentType为excel格式
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + exportFileName);
        }catch(Exception e){
            e.printStackTrace();
        }
        //创建页面输出流对象
        ServletOutputStream outputStream = response.getOutputStream();
        //把文件写入输出流对象中
        wb.write(outputStream);
        outputStream.close();
    }

    private String[][] getContent(List<User> projectExtractVOList,String[][] content) throws Exception{
        if(projectExtractVOList !=null && projectExtractVOList.size()>0){
            for(int i=0;i<projectExtractVOList.size();i++){
                content[i][0] = projectExtractVOList.get(i).getUserId(); //项目名称
                content[i][1] = projectExtractVOList.get(i).getUserName(); //建设单位
                content[i][2] = projectExtractVOList.get(i).getGender(); //项目编号
                content[i][3] = projectExtractVOList.get(i).getAge(); //工作内容
                content[i][4] = projectExtractVOList.get(i).getPassword(); //抽取地点
                content[i][5] = projectExtractVOList.get(i).getUserId().toString(); //抽取时间
                content[i][6] = projectExtractVOList.get(i).getUserName().toString(); //预算金额(单位:元)
                content[i][7] = projectExtractVOList.get(i).getPassword(); //中标承包商
                content[i][8] = projectExtractVOList.get(i).getAge(); //业务单位
            }
        }
        return content;
    }

    //设置列宽
    private void setColumnWidth(HSSFSheet sheet,Map<Integer, Integer> maxWidth){
        for (int i = 0; i < 9; i++) {
            sheet .setColumnWidth(i, maxWidth.get(i));
//            if(i==4){
//                System.out.println("实际大小="+maxWidth.get(i));
//            }
        }

    }

}
