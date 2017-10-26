package com.kyq.zwj.backend.modules.lanelogfile.dao;

import com.kyq.zwj.backend.entity.LaneImageFileManager;
import com.kyq.zwj.backend.entity.LaneLogFileManager;

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
public interface LaneLogFileManagerDao {
    LaneImageFileManager selectOne(LaneLogFileManager laneLogFileManager);

    List<LaneImageFileManager> selectList(Map map);

    void add(LaneLogFileManager laneLogFileManager);
}
