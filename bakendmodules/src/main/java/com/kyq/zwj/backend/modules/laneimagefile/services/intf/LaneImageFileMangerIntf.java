package com.kyq.zwj.backend.modules.laneimagefile.services.intf;

import com.kyq.zwj.backend.entity.LaneImageFileManager;
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
public interface LaneImageFileMangerIntf {
    Map getFileData();

    Map getLaneImageFileInfo(LaneImageFileManager param);

    Map getBatchLaneImageFileInfo(LaneImageFileManager param);

    Map addLaneImageFileInfo(List<MultipartFile> files);
}
