package com.kyq.zwj.backend.persistence;

import com.kyq.zwj.backend.persistence.intf.CommonDBManger;
import com.kyq.zwj.backend.spring.SpringContextUtil;

/**
 * Description:
 * Copyright: © 2017 CSNT. All rights reserved.
 * Company:CSTC
 *
 * @version 1.0
 * @author: kyq1024
 * @timestamp: 2017-10-26 22:09
 */
public class PersistenceFactory {
    private static class LazyHolder {
        private static final CommonDBManger INSTANCE = (CommonDBManger) SpringContextUtil.getBean("mybatisCommonDBManager");
    }
    public static CommonDBManger getInstance() {
        return PersistenceFactory.LazyHolder.INSTANCE;
    }
}
