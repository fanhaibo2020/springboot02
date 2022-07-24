package com.example.calendar;

import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @ClassName 日历测试类
 * @Author fhb
 * @Date 2020/10/14 23:06
 * @Version 1.0
 **/
public class calendarTest {

    public static void main(String[] args) throws Exception{
        getDates("2020-10-1", "2020-12-31");
        //四舍五入的计算
        calculate();
    }

    /**
     * 获取两个日期之间的所有日期
     *
     * @param startDate  开始日期
     * @param endDate   结束日期
     * @return  List集合
     */

    public static void getDates(String startDate, String endDate) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date_start = sdf.parse(startDate);
        Date date_end = sdf.parse(endDate);
        Date date =date_start;
        System.out.println("111="+date_start);
        System.out.println("222="+date_end);
        System.out.println("333="+date_start.getTime()); //1601481600000
        System.out.println("444="+date_end.getTime()); //1609344000000
        Calendar cd = Calendar.getInstance();//用Calendar 进行日期比较判断
        System.out.println("cd="+cd);
        while (date.getTime() <= date_end.getTime()){
            System.out.println("555="+sdf.format(date)); //打印如:2020-10-01
            cd.setTime(date);
            cd.add(Calendar.DATE, 1);//增加一天 放入集合
            date=cd.getTime();
            System.out.println("666="+sdf.format(date)); //2020-10-02
        }
    }

    //四舍五入计算
    public static void calculate() throws Exception{
        BigDecimal standard = new BigDecimal(1000);
        BigDecimal num1 = new BigDecimal(333);
        BigDecimal num2 = new BigDecimal(333);
        BigDecimal num3 = new BigDecimal(334);
        BigDecimal a = num1.divide(standard, 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal b = num2.divide(standard, 2, BigDecimal.ROUND_HALF_UP);
        BigDecimal c = num3.divide(standard, 2, BigDecimal.ROUND_HALF_UP);
        System.out.println("a="+a); //打印数据：
        System.out.println("b="+b);
        System.out.println("c="+c);


    }


}
