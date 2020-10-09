package com.example.quartz;


import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName createCardIdTest
 * @describle 创建校园卡帐号
 * @Author fhb
 * @Date 2020/8/26 23:00
 * @Version 1.0
 **/
public class createCardIdTest {

    @Test
    public void aa(){
        SimpleDateFormat sdf = new SimpleDateFormat("yy");
        String prefix = sdf.format(new Date());
        System.out.println("prefix="+prefix);
        String number = "2000009.0"; //假如是db中最大的数  2010009.0
        String a = number.substring(2,number.length());
        System.out.println("length="+number.length());
        System.out.println("a="+a);
//        int b = Integer.parseInt(a);
        double b = Double.parseDouble(a);
        System.out.println("b="+b);
        int e = new Double(b).intValue();
        System.out.println("e="+e);
        String aa = "";
        int digit = 4 ; //待填充的4位数字
        if(number.length()>=7){
            digit = 5 ;
        }else if(number.length()>=8){
            digit = 6 ;
        }
        digit = digit-String.valueOf(e+1).length();
        for(int i=digit;i>=1;i--){
            aa = "0"+aa;
        }
        System.out.println("aa="+aa);
        String dd = prefix + aa + String.valueOf(e+1);
        System.out.println("dd="+dd);
     }

}
