package com.kyq.zwj.backend.modules.lanelogfile.services.intf;

import com.kyq.zwj.backend.entity.LaneImageFileManager;
import com.kyq.zwj.backend.entity.LaneLogFileManager;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Description:
 * Copyright: © 2017 CSNT. All rights reserved.
 * Company:CSTC
 *
 * @version 1.0
 * @author: kyq1024
 * @timestamp: 2017-10-24 17:16
 */
public interface LaneLogFileService {
    Map getLaneLogFileInfo(LaneLogFileManager param);

    Map getBatchLaneLogFileInfo(LaneLogFileManager param);

    Map addLaneLogFileInfo(List<MultipartFile> files);
}
