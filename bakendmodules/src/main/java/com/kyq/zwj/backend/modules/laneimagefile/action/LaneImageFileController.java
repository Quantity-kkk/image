package com.kyq.zwj.backend.modules.laneimagefile.action;

import com.kyq.zwj.backend.modules.laneimagefile.services.intf.LaneImageFileManger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Description:
 * Copyright: © 2017 CSNT. All rights reserved.
 * Company:CSTC
 *
 * @version 1.0
 * @author: kyq1024
 * @timestamp: 2017-10-24 16:25
 */
@Scope("singleton")
@Controller
@RequestMapping("/laneimage")
public class LaneImageFileController {

    @Resource(name="laneimagefile-manager")
    LaneImageFileManger laneImageFileManger;

    private static final Logger logger = LoggerFactory.getLogger(LaneImageFileController.class);
    /**
     * 实现rest风格的请求
     * */
    @ResponseBody
    @RequestMapping(value="getImageInfo.do", method= RequestMethod.GET)
    public Map getUserInfo(){
        logger.info("test logger...");
//        throw new RuntimeException("test");
        return laneImageFileManger.getFileData();
    }
}
