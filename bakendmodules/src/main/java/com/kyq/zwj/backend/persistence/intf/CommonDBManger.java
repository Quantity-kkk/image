package com.kyq.zwj.backend.persistence.intf;

import java.util.List;
import java.util.Map;

/**
 * Description: 通用操作类
 * Copyright: © 2017 CSNT. All rights reserved.
 * Company:CSTC
 *
 * @version 1.0
 * @author: kyq1024
 * @timestamp: 2017-10-26 22:07
 */
public interface CommonDBManger {
    /**
     * 根据Native SQL进行查询
     */
    List<Map<String, Object>> findByNativeSQL(String nativeSql);

    /**
     * 根据Native SQL进行更新
     */
    Integer updateByNativeSQL(String nativeSql);

    /**
     * 根据Native SQL进行删除
     * */
    void deleteByNativeSQL(String nativeSql);

    /**
     * 根据Native SQL进行插入
     * */
    Integer insertByNativeSQL(String nativeSql);
}
