package com.kyq.zwj.backend.modules.laneimagefile.dto;

import com.kyq.zwj.backend.entity.LaneImageFileManager;

import java.util.List;

/**
 * Description: 数据传输对象。用于接收参数.如果批量数据，可以使用此参数接受.
 * Copyright: © 2017 CSNT. All rights reserved.
 * Company:CSTC
 *
 * @version 1.0
 * @author: kyq1024
 * @timestamp: 2017-10-25 10:14
 */
public class LaneImageFileManagerDto extends LaneImageFileManager{
    private List<LaneImageFileManager> fileManagers;

    public List<LaneImageFileManager> getFileManagers() {
        return fileManagers;
    }

    public void setFileManagers(List<LaneImageFileManager> fileManagers) {
        this.fileManagers = fileManagers;
    }
}



