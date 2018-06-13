package com.zx.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 *
 * @author ning
 * @create 2018-06-06 9:45
 **/
public class DateUtil {

    public static final String DEFAULT_FORMAT_DATE = "yyyy-MM-dd";
    public static final String DEFAULT_FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String ASSIGN_FORMAT_DATE_TIME = "yyyy/MM/dd HH:mm";
    public static final String ASSIGN_FORMAT_DATE = "yyyy/MM/dd";
    public static final String DEFAULT_FORMAT_TIME = "HH:mm:ss";
    public static final String FORMAT_TIME_MONTH = "yyyy-MM";
    public static final String FORMAT_TIME_HOUR = "yyyy-MM-dd HH";
    public static final String FORMAT_DATE_HOUR = "yyyyMMddHH";
    public static final String FORMAT_DATE_DAY = "yyyyMMdd";

    public static String formatDate(Date date, String pattern){
        String format = "";

        if (StringUtil.isEmpty(pattern)){
            pattern =DEFAULT_FORMAT_DATE_TIME;
        }
        DateFormat df = new SimpleDateFormat(pattern);
        format = df.format(date);

        return format;
    }

    public static String getBeforeDateString(){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        Date time = calendar.getTime();
        return formatDate(time,FORMAT_DATE_HOUR);
    }

    public static void main(String[] args){
//        String formatDate = formatDate(new Date(), null);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        System.out.println(calendar.getTime());
    }
}