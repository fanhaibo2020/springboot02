package com.example.quartz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName timeUtilDemo
 * @descripe 计算两个日期的时间相隔的天数
 * @Author fhb
 * @Date 2020/10/26 22:56
 * @Version 1.0
 **/
public class timeUtilDemo {

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) throws Exception{
//        String first = "2020-9-24";
//        String second = "2020-10-26";
//        Date firstdate = sdf.parse(first);
//        Date seconddate = sdf.parse(second);

        long firstLong = 1600876800000L; // 2020-09-24 00:00:00对应的时间戳
        long secondLong = 1603727999000L; // 2020-10-26 23:59:59对应的时间戳
        String first = sdf.format(firstLong);
        String second = sdf.format(secondLong);
        Date firstdate = sdf.parse(first);
        Date seconddate = sdf.parse(second);

        int cnt = longOfTwoDate(firstdate, seconddate)+1; //此处要加1是因为first为一天的开始，second为一天的结束
        //即时它们的解析的字符串是一样的,但实际上应该也是相差了一天的。
        System.out.println("cnt="+cnt);
    }

    public static int  longOfTwoDate(Date first,Date second) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(first);
        int cnt = 0;
        while(calendar.getTime().compareTo(second)!=0){
            calendar.add(Calendar.DATE, 1);
            cnt++;
        }
        return cnt;
    }

}
