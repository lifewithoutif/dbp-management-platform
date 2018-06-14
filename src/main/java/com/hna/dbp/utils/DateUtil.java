package com.hna.dbp.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static String getDate(Long time){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制
        String PTime = simpleDateFormat.format(new Date(time));
        return PTime;
    }
    public static Long paseDate(Date date){
        long time = date.getTime();
        return time;
    }

    public static Date paseStirng(String s) throws Exception{
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制
            Date parse = simpleDateFormat.parse(s);
        return parse;
    }

    public static Date plusNoneYear(Date date){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.YEAR,1);//日期加1年
//        rightNow.add(Calendar.MONTH,3);//日期加3个月
//        rightNow.add(Calendar.DAY_OF_YEAR,10);//日期加10天
        Date dt=rightNow.getTime();
        return dt;
    }

    public static Date plusDay(Date date,int day){
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.DAY_OF_YEAR,day);//日期加day天
        Date dt=rightNow.getTime();
        return dt;
    }
}
