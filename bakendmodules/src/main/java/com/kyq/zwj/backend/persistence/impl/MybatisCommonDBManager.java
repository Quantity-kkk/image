package com.kyq.zwj.backend.persistence.impl;

import com.kyq.zwj.backend.persistence.intf.CommonDBManger;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * Copyright: © 2017 CSNT. All rights reserved.
 * Company:CSTC
 *
 * @version 1.0
 * @author: kyq1024
 * @timestamp: 2017-10-26 22:07
 */
@Scope("singleton")
@Component
public class MybatisCommonDBManager implements CommonDBManger{

    @Autowired
    SqlSessionFactory sqlSessionFactory;
    /**
     * 根据自定义的sql语句查找数据
     * */
    @Override
    public List<Map<String, Object>> findByNativeSQL(String nativeSql) {
        return SqlSessionUtils.getSqlSession(sqlSessionFactory).selectList("com.kyq.zwj.backend.modules.common.dao.CommonDao.findByNativeSQL",nativeSql);
    }

    @Override
    public Integer updateByNativeSQL(String nativeSql) {
        return null;
    }

    @Override
    public void deleteByNativeSQL(String nativeSql) {

    }

    @Override
    public Integer insertByNativeSQL(String nativeSql) {
        return null;
    }
}
