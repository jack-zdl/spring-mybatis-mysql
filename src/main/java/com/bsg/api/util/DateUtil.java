package com.bsg.api.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/** @description 日期处理工具类
 * Created by zhang on 2017/3/29.
 */
public class DateUtil {

    public static Date getCurrentDateTime(){
        return new Date();
    }

    /**
     * @description 时间转换为字符串
     * @param date
     * @return
     */
    public static String dateToString(Date date){
        if (date !=null){
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    public static String dateTimeToString(Date date){
        if(date != null){
            return  "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
}
