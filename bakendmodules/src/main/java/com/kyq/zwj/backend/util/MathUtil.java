package com.kyq.zwj.backend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Description:  MathUtil
 * Copyright: © 2014 CSNT. All rights reserved.
 * Company:CSNT
 *
 * @author kyq1024
 * @version 1.0
 * @timestamp: 2017-10-26 19:13
 */
public class MathUtil {
    private static final Logger tracer = LoggerFactory.getLogger(MathUtil.class);

    /**
     * 在范围内取随机整数
     */
    public static int getRandomInt(int min, int max) {
        int gap = max - min + 1;
        return (int) (Math.random() * gap + min);
    }

    /**
     * 字符串转Number类型
     */
    public static Object asNumber(Object object, Class dstClass) {
        Object retObj = object;

        if (!object.getClass().equals(dstClass)) {
            String valStr = object.toString();
            if (Integer.class.equals(dstClass) || int.class.equals(dstClass)) {
                if ("true".equalsIgnoreCase(valStr)) {
                    retObj = 1;
                } else if ("false".equalsIgnoreCase(valStr)) {
                    retObj = 0;
                } else {
                    retObj = StringUtil.isNotEmpty(valStr) ? Integer.valueOf(valStr) : null;
                }
            } else if (Long.class.equals(dstClass) || long.class.equals(dstClass)) {
                if ("true".equalsIgnoreCase(valStr)) {
                    retObj = 1L;
                } else if ("false".equalsIgnoreCase(valStr)) {
                    retObj = 0L;
                } else {
                    retObj = StringUtil.isNotEmpty(valStr) ? Long.valueOf(valStr) : null;
                }
            } else if (Double.class.equals(dstClass)) {
                retObj = StringUtil.isNotEmpty(valStr) ? Double.valueOf(valStr) : null;
            } else if (Float.class.equals(dstClass)) {
                retObj = StringUtil.isNotEmpty(valStr) ? Float.valueOf(valStr) : null;
            } else if (BigDecimal.class.equals(dstClass)) {
                retObj = StringUtil.isNotEmpty(valStr) ? BigDecimal.valueOf(Double.valueOf(valStr)) : null;
            }
        }
        return retObj;
    }

    /**
     * 字符串转Integer类型
     */
    public static Integer asInteger(Object object) {
        return (Integer) asNumber(object, Integer.class);
    }

    /**
     * 字符串转Long类型
     */
    public static Long asLong(Object object) {
        return (Long) asNumber(object, Long.class);
    }

}
