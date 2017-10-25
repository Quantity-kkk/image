package com.kyq.zwj.backend.modules.laneimagefile.services.impl;

import com.kyq.zwj.backend.entity.User;
import com.kyq.zwj.backend.modules.laneimagefile.services.intf.LaneImageFileManger;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Copyright: © 2017 CSNT. All rights reserved.
 * Company:CSTC
 *
 * @version 1.0
 * @author: kyq1024
 * @timestamp: 2017-10-24 17:17
 */
@Transactional
@Service("laneimagefile-manager")
public class LaneImageFileImpl  implements LaneImageFileManger{

    @Autowired
    SqlSessionFactory sqlSessionFactory;
    @Override
    public Map getFileData() {
        List data = SqlSessionUtils.getSqlSession(sqlSessionFactory).selectList("findRecords","select * from user");
        Map out = new HashMap<>();

        String statement = "me.gacl.mapping.userMapper.getUser";//映射sql的标识字符串
        //执行查询返回一个唯一user对象的sql
//        User user = SqlSessionUtils.getSqlSession(sqlSessionFactory).selectOne(statement, 1);
        out.put("姓名","张三");
        out.put("性别","女");
        out.put("data",data);
        return out;
    }
}
