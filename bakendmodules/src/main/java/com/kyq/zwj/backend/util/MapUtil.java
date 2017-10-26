package com.kyq.zwj.backend.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Description:
 * Copyright: © 2017 CSNT. All rights reserved.
 * Company:CSTC
 *
 * @version 1.0
 * @author: kyq1024
 * @timestamp: 2017-10-25 15:00
 */
public class MapUtil {
    /**
     * Map判空
     */
    public static boolean isEmpty(Map data) {
        boolean isEmpty = true;
        if (data != null && data.size() > 0) {
            isEmpty = false;
        }
        return isEmpty;
    }

    /**
     * Map判非空
     */
    public static boolean isNotEmpty(Map data) {
        return !isEmpty(data);
    }

    /**
     * Map重构，字段名全部变小写,去下划线变驼峰
     */
    public static Map refactorCamelKey(Map srcMap) {
        if (MapUtil.isNotEmpty(srcMap)) {
            Map desMap = new HashMap();
            Set keySet = srcMap.keySet();
            for (Iterator it = keySet.iterator(); it.hasNext(); ) {
                String next = (String) it.next();
                desMap.put(StringUtil.camelAndRemoveUnderline(next.toLowerCase()), srcMap.get(next));
            }
            return desMap;
        } else {
            return srcMap;
        }
    }
}
