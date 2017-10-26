package com.kyq.zwj.backend.util;

/**
 * Description:
 * Copyright: © 2017 CSNT. All rights reserved.
 * Company:CSTC
 *
 * @version 1.0
 * @author: kyq1024
 * @timestamp: 2017-10-25 13:46
 */
public class StringUtil {
    /**
     * 判断是否为非空字符串（不为null或者空串）
     */
    public static boolean isNotEmpty(Object... objs) {
        if (objs == null) {
            return false;
        } else {
            for (Object obj : objs) {
                if (obj == null || "".equals(obj)) return false;
            }
            return true;
        }
    }

    /**
     * 重构字符串，如果为null，返回空串
     */
    public static String replaceNull(Object obj) {
        if (isEmpty(obj)) {
            return "";
        } else {
            return obj.toString();
        }
    }

    /**
     * 重构字符串，如果为null，返回strDefault
     */
    public static String replaceNull(Object obj, String strDefault) {
        if (isEmpty(obj)) {
            return strDefault;
        } else {
            return obj.toString();
        }
    }
    /**
     * 判断是否为空字符串（null或者空串）
     */
    public static boolean isEmpty(Object... objs) {
        if (objs == null) {
            return true;
        } else {
            for (Object obj : objs) {
                if (obj != null && !"".equals(obj)) return false;
            }
            return true;
        }
    }

    /**
     * 去下划线变驼峰
     */
    public static String camelAndRemoveUnderline(String str) {
        if (isEmpty(str)) {
            return str;
        } else {
            String strRet = str.toLowerCase();
            int underlineLoc = str.indexOf("_");
            while (underlineLoc >= 0 && underlineLoc <= (strRet.length() - 1)) {
                if (underlineLoc == (strRet.length() - 1)) {
                    strRet = strRet.substring(0, underlineLoc);
                } else {
                    strRet = strRet.substring(0, underlineLoc)
                            + strRet.substring(underlineLoc + 1, underlineLoc + 2).toUpperCase()
                            + strRet.substring(underlineLoc + 2);
                    underlineLoc = strRet.indexOf("_");
                }
            }
            return strRet;
        }
    }
}
