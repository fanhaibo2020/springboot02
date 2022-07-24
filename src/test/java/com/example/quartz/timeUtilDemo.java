package com.example.quartz;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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

//        long firstLong = 1600876800000L; // 2020-09-24 00:00:00对应的时间戳
//        long secondLong = 1603727999000L; // 2020-10-26 23:59:59对应的时间戳
//        String first = sdf.format(firstLong);
//        String second = sdf.format(secondLong);
//        Date firstdate = sdf.parse(first);
//        Date seconddate = sdf.parse(second);
//
//        int cnt = longOfTwoDate(firstdate, seconddate)+1; //此处要加1是因为first为一天的开始，second为一天的结束
//        //即时它们的解析的字符串是一样的,但实际上应该也是相差了一天的。
//        System.out.println("cnt="+cnt);

        String startStr = "20210101";
        String endStr = "20211231";
        String str = createYearWeek(startStr, endStr);
        System.out.println(str);

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

    //生成自然周数据
    /**
     * 创建自然周
     * @param startStr 开始日期
     * @param endStr 结束日期
     * @return 周信息字符串
     */
    public static String createYearWeek(String startStr, String endStr) {
        if (startStr == null || "".equals(startStr) || endStr == null || "".equals(endStr)) {
            return "起止日期不能为空";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date start = null;
        Date end = null;
        Date startDate;
        Date endDate;
        int dayOfWeek;
        boolean goOn = true;
        int no = 0;
        try {
            start = sdf.parse(startStr);
            end = sdf.parse(endStr);
        } catch (ParseException e) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
            try {
                start = simpleDateFormat.parse(startStr);
                end = simpleDateFormat.parse(endStr);
            } catch (ParseException pe) {
                pe.printStackTrace();
            }
        }
        if (start.compareTo(end) > -1) {
            return "结束日期必须大于开始日期";
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        List<Map<String, String>> weekList = new ArrayList<>();
        if (dayOfWeek > 1 && dayOfWeek < 7) {
            do {
                startDate = start;
                calendar.add(Calendar.DATE, (8 - dayOfWeek));
                endDate = calendar.getTime();
                no += 1 ;
                Map<String, String> map = new HashMap<>();
                map.put("startOfWeek", sdf.format(startDate));
                map.put("weekNo", no + "");
                if (endDate.compareTo(end) < 0) {
                    map.put("endOfWeek", sdf.format(endDate));
                    weekList.add(map);
                    calendar.add(Calendar.DATE, 1);
                    start = calendar.getTime();
                    dayOfWeek = 2;
                } else {
                    map.put("endOfWeek", sdf.format(end));
                    weekList.add(map);
                    goOn = false;
                }
            } while (goOn);
        } else {
            no += 1 ;
            startDate = start;
            if (dayOfWeek == 1) {
                endDate = start;
            } else {
                calendar.add(Calendar.DATE, 1);
                endDate = calendar.getTime();
            }
            Map<String, String> map = new HashMap<>();
            map.put("startOfWeek", sdf.format(startDate));
            map.put("endOfWeek", sdf.format(endDate));
            map.put("weekNo", no + "");
            weekList.add(map);
            calendar.add(Calendar.DATE, 1);
            start = calendar.getTime();
            do {
                startDate = start;
                calendar.add(Calendar.DATE, 6);
                endDate = calendar.getTime();
                no += 1 ;
                map = new HashMap<>();
                map.put("startOfWeek", sdf.format(startDate));
                map.put("weekNo", no + "");
                if (endDate.compareTo(end) < 0) {
                    map.put("endOfWeek", sdf.format(endDate));
                    weekList.add(map);
                    calendar.add(Calendar.DATE, 1);
                    start = calendar.getTime();
                } else {
                    map.put("endOfWeek", sdf.format(end));
                    weekList.add(map);
                    goOn = false;
                }
            } while (goOn);
        }
        return weekList.toString();
    }


}
