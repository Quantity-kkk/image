package com.kyq.zwj.backend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description:
 * Copyright: © 2017 CSNT. All rights reserved.
 * Company:CSTC
 *
 * @version 1.0
 * @author: kyq1024
 * @timestamp: 2017-10-26 19:33
 */
public class DateUtil {
    private static final Logger tracer = LoggerFactory.getLogger(DateUtil.class);
    /**
     * 格式化日期字符串
     * yyyy-MM-dd HH:mm:ss SSS
     */
    public static String formatDate(Date date, String datePattern) {
        String ret = null;
        if (date != null) {
            try {
                DateFormat dateFormat = new SimpleDateFormat(datePattern);
                ret = dateFormat.format(date);
            } catch (Exception e) {
                tracer.error(""+e);
                throw new RuntimeException(e);
            }
        }
        return ret;
    }

    /**
     * 解析日期字符串，转换成日期
     */
    public static Date parseDate(String strDate, String datePattern) {
        Date retDate = null;
        if (strDate != null) {
            if (strDate.contains("T")) {
                strDate = strDate.replace("T", " ");
            }
            if (strDate.length() <= 10) {
                strDate = strDate + " 00:00:00";
            }
            SimpleDateFormat sf = new SimpleDateFormat(datePattern);
            try {
                retDate = sf.parse(strDate);
            } catch (Exception e) {
                tracer.error(""+e);
                throw new RuntimeException(e);
            }
        }
        return retDate;
    }
}
