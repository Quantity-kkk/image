package com.kyq.zwj.backend.modules.laneimagefile.services.impl;

import com.kyq.zwj.backend.entity.LaneImageFileManager;
import com.kyq.zwj.backend.modules.laneimagefile.services.intf.LaneImageNameParser;
import com.kyq.zwj.backend.util.BeanUtils;
import com.kyq.zwj.backend.util.DateUtil;
import com.kyq.zwj.backend.util.MathUtil;
import com.kyq.zwj.backend.util.StringUtil;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * Description:
 * Copyright: © 2017 CSNT. All rights reserved.
 * Company:CSTC
 *
 * @version 1.0
 * @author: kyq1024
 * @timestamp: 2017-10-26 15:56
 */
@Scope
@Service("qh-lane-image-name-parser")
public class QHLaneImageNameParserImpl implements LaneImageNameParser{

    @Override
    public LaneImageFileManager decodeFileParams(String fileName) {
        if(StringUtil.isEmpty(fileName)){
            throw new RuntimeException("图片文件名称有误，无法解析");
        }

        String[] params = fileName.split("_");
        if(params.length!=9){
            throw new RuntimeException("文件命名规则有误，无法解析到相关参数！");
        }

        Map convertMap = new HashMap<>();
        //命名规则，Pic_图片类型_收费站_车道号_出入口类型_车牌号_车牌颜色_通行卡卡号_时间.jpg
        convertMap.put("imageType", MathUtil.asInteger(params[1]));//图片类型,1表示车道抓拍，2表示车牌识别图片(预留).
        convertMap.put("stationNo", MathUtil.asInteger(params[2]));//收费站编号
        convertMap.put("laneNo", MathUtil.asInteger(params[3]));//车道号
        convertMap.put("laneType", MathUtil.asInteger(params[4]));//出入口类型
        convertMap.put("vehPlate", params[5]);//车牌号
        convertMap.put("vehiclePlateColor", MathUtil.asInteger(params[6]));//车牌颜色
        convertMap.put("cardId", MathUtil.asLong(params[7]));//通行卡卡号
        convertMap.put("opeTime", DateUtil.parseDate(params[8],"yyyyMMddHHmmss"));//操作时间 20170905121212
        BeanUtils.map2Bean(convertMap,LaneImageFileManager.class);

        return  BeanUtils.map2Bean(convertMap,LaneImageFileManager.class);
    }
}
