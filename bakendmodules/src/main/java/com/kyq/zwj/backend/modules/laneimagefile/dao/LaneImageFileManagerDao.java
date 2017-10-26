package com.kyq.zwj.backend.modules.laneimagefile.dao;

import com.kyq.zwj.backend.entity.LaneImageFileManager;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * Copyright: © 2017 CSNT. All rights reserved.
 * Company:CSTC
 *
 * @version 1.0
 * @author: kyq1024
 * @timestamp: 2017-10-24 14:23
 */
public interface LaneImageFileManagerDao {
    LaneImageFileManager selectOne(LaneImageFileManager laneImageFileManager);

    List<LaneImageFileManager> selectList(Map map);

}
