package com.kyq.zwj.backend.modules.lanelogfile.services.impl;

import com.kyq.zwj.backend.entity.LaneImageFileManager;
import com.kyq.zwj.backend.entity.LaneLogFileManager;
import com.kyq.zwj.backend.modules.lanelogfile.services.intf.LaneLogNameParser;
import com.kyq.zwj.backend.util.BeanUtils;
import com.kyq.zwj.backend.util.DateUtil;
import com.kyq.zwj.backend.util.MathUtil;
import com.kyq.zwj.backend.util.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Copyright: © 2017 CSNT. All rights reserved.
 * Company:CSTC
 *
 * @version 1.0
 * @author: kyq1024
 * @timestamp: 2017-10-27 0:12
 */
@Scope
@Service("qh-lane-log-name-parser")
public class QHLaneImgeNameParserImpl implements LaneLogNameParser {
    @Override
    public LaneLogFileManager decodeFileParams(String fileName) {
        if(StringUtil.isEmpty(fileName)){
            throw new RuntimeException("日志文件名称有误，无法解析");
        }

        String[] params = fileName.split("_");
        if(params.length!=6){
            throw new RuntimeException("文件命名规则有误，无法解析到相关参数！");
        }
        /*
        * 命名规则： Log _车道软件厂商标识_收费站编号_车道编号_车道IP_yyyyMMdd.txt； 例如： Log_YG_10_35_81_10.63.98.123_20170905.txt
            命名规则说明：
            Log:为固定前缀，表示文件为日志。
            车道软件厂商标识：为了在省中心能按照不同的车道软件日志规则进行日志的智能解析。 易构:YG；华软： HR
            车道 IP：为运行车道软件的工控机 IP 地址。
            yyyyMMdd：表示日志文件记录的自然日的日志。
        */
        Map convertMap = new HashMap<>();
        //命名规则，Log _车道软件厂商标识_收费站编号_车道编号_车道IP_yyyyMMdd.txt；
        convertMap.put("softProFlag", MathUtil.asInteger(params[1]));//车道软件厂商标识：为了在省中心能按照不同的车道软件日志规则进行日志的智能解析。 易构:YG；华软： HR
        convertMap.put("stationNo", MathUtil.asInteger(params[2]));//收费站编号
        convertMap.put("laneNo", MathUtil.asInteger(params[3]));//车道号
        convertMap.put("backUp1", params[4]);//车道ip
        convertMap.put("opeTime", DateUtil.parseDate(params[8],"yyyyMMdd"));//日起 20170905121212
        BeanUtils.map2Bean(convertMap,LaneLogFileManager.class);

        return  BeanUtils.map2Bean(convertMap,LaneLogFileManager.class);
    }
}
