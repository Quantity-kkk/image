package com.kyq.zwj.backend.modules.lanelogfile.action;

import com.kyq.zwj.backend.entity.LaneImageFileManager;
import com.kyq.zwj.backend.entity.LaneLogFileManager;
import com.kyq.zwj.backend.modules.laneimagefile.action.LaneImageFileController;
import com.kyq.zwj.backend.modules.laneimagefile.services.intf.LaneImageFileMangerIntf;
import com.kyq.zwj.backend.modules.lanelogfile.services.impl.LaneLogFileServiceImpl;
import com.kyq.zwj.backend.modules.lanelogfile.services.intf.LaneLogFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Description:
 * Copyright: © 2017 CSNT. All rights reserved.
 * Company:CSTC
 *
 * @version 1.0
 * @author: kyq1024
 * @timestamp: 2017-10-25 16:29
 */
@Scope("singleton")
@Controller
@RequestMapping("/lanelog")
public class LaneLogFileController {

    @Resource(name="laneilogfile-manager")
    LaneLogFileService laneLogFileService;

    private static final Logger logger = LoggerFactory.getLogger(LaneLogFileController.class);

    /**
     * 获取单条日志信息
     * 参数：车辆通行收费站、车道号、时间、车牌号、等关键信息
     * 返回：日志信息
     * */
    @ResponseBody
    @RequestMapping(value="getLaneLogFileInfo.do", method= RequestMethod.GET)
    public Map getLaneImageFileInfo(LaneLogFileManager laneLogFileManager, HttpServletRequest req, HttpServletResponse resp){
        return laneLogFileService.getLaneLogFileInfo(laneLogFileManager);
    }

    /**
     * 获取单条日志信息
     * 参数：车辆通行收费站、车道号、时间、车牌号、等关键信息
     * 返回：日志信息
     * */
    @ResponseBody
    @RequestMapping(value="getBatchLaneLogFileInfo.do", method= RequestMethod.GET)
    public Map getBatchLaneImageFileInfo(LaneLogFileManager laneLogFileManager){
        return laneLogFileService.getBatchLaneLogFileInfo(laneLogFileManager);
    }

    //主要是上传文件.
    @ResponseBody
    @RequestMapping(value="postLaneLogFile.do", method= RequestMethod.POST)
    public Map postLaneImageFile(HttpServletRequest req){
        //处理参数
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) req;
//        CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("uploadFiles");
        List<MultipartFile> files = multipartRequest.getFiles("uploadFiles");
        //ps:参数是根据文件名来进行的标识。将文件存储之后需要将文件下载地址返回到前台
        //保存数据
        return laneLogFileService.addLaneLogFileInfo(files);
    }

}
