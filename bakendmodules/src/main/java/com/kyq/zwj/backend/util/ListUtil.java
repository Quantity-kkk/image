package com.kyq.zwj.backend.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Copyright: © 2017 CSNT. All rights reserved.
 * Company:CSTC
 *
 * @version 1.0
 * @author: kyq1024
 * @timestamp: 2017-10-26 20:04
 */
public class ListUtil {
    /**
     * List判空
     */
    public static boolean isEmpty(List data) {
        return data != null && data.size() > 0;
    }

    /**
     * List判非空
     */
    public static boolean isNotEmpty(List data) {
        return !isEmpty(data);
    }

    /**
     * List重构，字段名全部变小写,下划线变驼峰字符串
     */
    public static List refactorCamelKey(List srcList) {
        if (ListUtil.isNotEmpty(srcList)) {
            List desList = new ArrayList();
            for (Object aSrcList : srcList) {
                Map next = (Map) aSrcList;
                desList.add(MapUtil.refactorCamelKey(next));
            }
            return desList;
        } else {
            return srcList;
        }
    }
}
