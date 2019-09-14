package com.tglm.bbs.Util;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author mlgt
 * @date 2019/9/14
 */
public class DateUtil {

    public static Timestamp dateToTimestamp(Date date) throws ParseException {
        String strDate = dateToStr(date);
        return strToTimestamp(strDate);


    }

    public static String dateToStr(java.util.Date date) {
        String strFormat = "yy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormat);
        return simpleDateFormat.format(date);
    }

    public static Timestamp strToTimestamp(String strDate) throws ParseException {
        String strFormat = "yy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormat);
        Date date = simpleDateFormat.parse(strDate);
        return new Timestamp(date.getTime());
    }

    public static Date timestampToDate(Timestamp timestamp){
        return timestamp;


    }

}
