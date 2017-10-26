package com.kyq.zwj.backend.modules.lanelogfile.services.impl;

import com.kyq.zwj.backend.entity.LaneImageFileManager;
import com.kyq.zwj.backend.entity.LaneLogFileManager;
import com.kyq.zwj.backend.helper.DFSFileHelper;
import com.kyq.zwj.backend.helper.SysconfigHelper;
import com.kyq.zwj.backend.modules.laneimagefile.services.intf.LaneImageNameParser;
import com.kyq.zwj.backend.modules.lanelogfile.services.intf.LaneLogFileService;
import com.kyq.zwj.backend.modules.lanelogfile.services.intf.LaneLogNameParser;
import com.kyq.zwj.backend.spring.SpringContextUtil;
import com.kyq.zwj.backend.util.BeanUtils;
import com.kyq.zwj.backend.util.ListUtil;
import com.kyq.zwj.backend.util.StringUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
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
@Service("laneilogfile-manager")
public class LaneLogFileServiceImpl implements LaneLogFileService {

    @Autowired
    SqlSessionFactory sqlSessionFactory;

    @Override
    public Map getLaneLogFileInfo(LaneLogFileManager param) {
        String statement = "com.kyq.zwj.backend.modules.lanelogfile.dao.LaneLogFileManagerDao.selectOne";//映射sql的标识字符串
        LaneLogFileManager laneLogFileManager = SqlSessionUtils.getSqlSession(sqlSessionFactory).selectOne(statement,param);

        Map out = new HashMap<>();
        Map dataMap = BeanUtils.bean2Map(laneLogFileManager);
        dataMap.put("downloadUrl",DFSFileHelper.getDownloadUrl(laneLogFileManager.getFdfsFilePath(),
                laneLogFileManager.getFdfsGroupName(),
                laneLogFileManager.getFileName()));
        out.put("success",true);
        out.put("data",dataMap);
        return out;
    }

    @Override
    public Map getBatchLaneLogFileInfo(LaneLogFileManager param){
        String statement = "com.kyq.zwj.backend.modules.lanelogfile.dao.LaneLogFileManagerDao.selectList";//映射sql的标识字符串
        List data = SqlSessionUtils.getSqlSession(sqlSessionFactory).selectList(statement,param);
//        data = ListUtil.refactorCamelKey(data);
        //下载链接获取
        data.forEach(x->{
            ((Map)x).put("downloadUrl",DFSFileHelper.getDownloadUrl(
                    (String)((Map)x).get("fdfsFilePath"),
                    (String)((Map)x).get("fdfsGroupName"),
                    (String)((Map)x).get("fileName")));
        });
        Map out = new HashMap<>();
        out.put("success",true);
        out.put("data", data);
        return out;
    }

    /**
     * 新增日志信息
     * */
    @Override
    public Map addLaneLogFileInfo(List<MultipartFile> files){
        Map out = new HashMap<>();
        if(!ListUtil.isEmpty(files)) {
            List<LaneLogFileManager> addRecords = new ArrayList<>();
            for (MultipartFile file : files) {
                try {
                    String fileName = StringUtil.replaceNull(file.getOriginalFilename(), file.getName());
                    String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
                    long fileSize = file.getSize();
                    //upload file
                    Map map = null;
                    try {
                        map = DFSFileHelper.upload(file.getBytes(), fileType, new HashMap());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    String groupName = (String) map.get("groupName");
                    String filePath = (String) map.get("filePath");

                    LaneLogNameParser laneLogNameParser =  SpringContextUtil.getBean(
                            SysconfigHelper.getProperty("lane_log_name_parser"));

                    LaneLogFileManager laneLogFileManager = laneLogNameParser.decodeFileParams(fileName);

                    laneLogFileManager.setFileSize(fileSize);
                    laneLogFileManager.setFdfsFilePath(filePath);
                    laneLogFileManager.setFdfsGroupName(groupName);
                    addRecords.add(laneLogFileManager);
                    //save data.
                } catch (Exception e) {
                    throw new RuntimeException("文件信息解析出错！");
                }
            }
            if(!ListUtil.isEmpty(addRecords)){
                //保存文件信息到数据库
                String statement = "com.kyq.zwj.backend.modules.lanelogfile.dao.LaneLogFileManagerDao.batchInsert";//映射sql的标识字符串
                int count = SqlSessionUtils.getSqlSession(sqlSessionFactory).insert(statement,addRecords);
                out.put("count",count);
            }
        }

        out.put("success",true);
        return out;
    }
}
