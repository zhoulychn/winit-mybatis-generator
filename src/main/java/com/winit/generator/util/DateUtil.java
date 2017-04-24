/**
 * Project Name:image-util
 * File Name:DateUtil.java
 * Package Name:org.wuqiong.imageutil.util
 * Date:2015年12月25日下午3:45:56
 * Copyright (c) 2015, CANNIKIN(http://http://code.taobao.org/p/cannikin/src/) All Rights Reserved.
 *
*/

package com.winit.generator.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ClassName:DateUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2015年12月25日 下午3:45:56 <br/>
 * 
 * @author qiyongkang
 * @version
 * @since JDK 1.6
 * @see
 */
public class DateUtil {
    public static String PATTERN_YMD = "yyyy-MM-dd";
    public static String PATTERN_MD = "MM-dd";

    public static Date strFormatYMDDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_YMD);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    public static Date strFormatMDDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_MD);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            return null;
        }
    }

    public static String dateFormatYMDStr(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_YMD);
        return sdf.format(date);
    }

    public static long calExcuteSecondTime(Date startTime, Date endTime) {
        long second = 0;

        long millisecond = endTime.getTime() - startTime.getTime();

        second = millisecond / 1000;

        return second;
    }

    public static long calExcuteMilliTime(Date startTime, Date endTime) {
        long millisecond = endTime.getTime() - startTime.getTime();
        return millisecond;
    }

    public static String getTodayWeek() {
        Calendar calendar = Calendar.getInstance();
        int week = calendar.get(Calendar.DAY_OF_WEEK);
        String str = "";
        switch (week) {
        case 1:
            str = "星期天";
            break;
        case 2:
            str = "星期一";
            break;
        case 3:
            str = "星期二";
            break;
        case 4:
            str = "星期三";
            break;
        case 5:
            str = "星期四";
            break;
        case 6:
            str = "星期五";
            break;
        case 7:
            str = "星期六";
            break;
        }

        return str;
    }
    
    /**
     * 
     * 日期按指定pattern转为字符串
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDataToStr(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static void main(String[] args) {
        System.out.println(getTodayWeek());
    }
}
