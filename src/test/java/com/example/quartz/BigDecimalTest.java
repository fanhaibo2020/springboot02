package com.example.quartz;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @ClassName BigDecimalTest
 * @Author fhb
 * @Date 2021/4/11 11:36
 * @Version 1.0
 **/
public class BigDecimalTest {

    @Test
    public void test999(){
//        int a = 2,b=3,c=7; //单个次数
//        int total = a+b+c; //总次数
//        int money = 937; //总金额
//        //要计算单个金额的平均分配
//        System.out.println("total="+total);
//        BigDecimal

        int a=1,b=1,c=1;
        int t=a+b+c;
        BigDecimal money = new BigDecimal(10);
        BigDecimal a1= new BigDecimal(a).multiply(money).divide(new BigDecimal(t), 2, BigDecimal.ROUND_HALF_UP);
        System.out.println("a1="+a1);

        BigDecimal b1= new BigDecimal(b).multiply(money).divide(new BigDecimal(t), 2, BigDecimal.ROUND_HALF_UP);
        System.out.println("b1="+b1);

        BigDecimal c1= new BigDecimal(c).multiply(money).divide(new BigDecimal(t), 2, BigDecimal.ROUND_HALF_UP);
        System.out.println("c1="+c1);
        BigDecimal d1 = a1.add(b1).add(c1);
        System.out.println("d1="+d1);
        if(d1.compareTo(money)>0){
           c1 = new BigDecimal(c).multiply(money).divide(new BigDecimal(t), 2, BigDecimal.ROUND_DOWN); //向下取
            System.out.println("除法舍五入向下取值,c1="+c1);
        }else if(d1.compareTo(money)<0){
            c1 = new BigDecimal(c).multiply(money).divide(new BigDecimal(t), 2, BigDecimal.ROUND_UP); //向上取
            System.out.println("除法舍五入向上取值,,c1="+c1);
        }
        BigDecimal d11 = a1.add(b1).add(c1);
        System.out.println("d11="+d11);
    }

}
