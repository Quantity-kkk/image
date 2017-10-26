package com.kyq.zwj.backend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * Description:
 * Copyright: © 2017 CSNT. All rights reserved.
 * Company:CSTC
 *
 * @version 1.0
 * @author: kyq1024
 * @timestamp: 2017-10-25 10:14
 */
public class LaneImageFileManager {
    private Integer stationNo;
    private Integer laneNo;
    private Date opeTime;
    private Integer laneType;
    private Integer imageType;
    private String fileName;
    private Long fileSize;
    private String fdfsGroupName;
    private String fdfsFilePath;
    private String vehPlate;
    private Integer vehiclePlateColor;
    private Long cardId;
    private Integer backUp1;
    private Integer backUp2;
    private Integer backUp3;
    private String backUp4;
    private String backUp5;


    public Integer getStationNo() {
        return stationNo;
    }

    public void setStationNo(Integer stationNo) {
        this.stationNo = stationNo;
    }

    public Integer getLaneNo() {
        return laneNo;
    }

    public void setLaneNo(Integer laneNo) {
        this.laneNo = laneNo;
    }

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    public Date getOpeTime() {
        return opeTime;
    }

    public void setOpeTime(Date opeTime) {
        this.opeTime = opeTime;
    }

    public Integer getLaneType() {
        return laneType;
    }

    public void setLaneType(Integer laneType) {
        this.laneType = laneType;
    }

    public Integer getImageType() {
        return imageType;
    }

    public void setImageType(Integer imageType) {
        this.imageType = imageType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFdfsGroupName() {
        return fdfsGroupName;
    }

    public void setFdfsGroupName(String fdfsGroupName) {
        this.fdfsGroupName = fdfsGroupName;
    }

    public String getFdfsFilePath() {
        return fdfsFilePath;
    }

    public void setFdfsFilePath(String fdfsFilePath) {
        this.fdfsFilePath = fdfsFilePath;
    }

    public String getVehPlate() {
        return vehPlate;
    }

    public void setVehPlate(String vehPlate) {
        this.vehPlate = vehPlate;
    }

    public Integer getVehiclePlateColor() {
        return vehiclePlateColor;
    }

    public void setVehiclePlateColor(Integer vehiclePlateColor) {
        this.vehiclePlateColor = vehiclePlateColor;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public Integer getBackUp1() {
        return backUp1;
    }

    public void setBackUp1(Integer backUp1) {
        this.backUp1 = backUp1;
    }

    public Integer getBackUp2() {
        return backUp2;
    }

    public void setBackUp2(Integer backUp2) {
        this.backUp2 = backUp2;
    }

    public Integer getBackUp3() {
        return backUp3;
    }

    public void setBackUp3(Integer backUp3) {
        this.backUp3 = backUp3;
    }

    public String getBackUp4() {
        return backUp4;
    }

    public void setBackUp4(String backUp4) {
        this.backUp4 = backUp4;
    }

    public String getBackUp5() {
        return backUp5;
    }

    public void setBackUp5(String backUp5) {
        this.backUp5 = backUp5;
    }
}



