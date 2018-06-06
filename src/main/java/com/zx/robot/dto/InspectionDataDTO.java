package com.zx.robot.dto;

import com.zx.robot.entity.InspectionData;

import java.io.Serializable;

/**
 * 前端展示数据封装类
 *
 * @author ning
 * @create 2018-06-06 10:07
 **/
public class InspectionDataDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    //最高温度
    private Double maxT;
    //最低温度
    private Double minT;
    //最高湿度
    private Double maxH;
    //最低湿度
    private Double minH;
    //最新点位数据
    private InspectionData inspectionData;

    public InspectionDataDTO() {
    }

    public InspectionDataDTO(Double maxT, Double minT, Double maxH, Double minH, InspectionData inspectionData) {
        this.maxT = maxT;
        this.minT = minT;
        this.maxH = maxH;
        this.minH = minH;
        this.inspectionData = inspectionData;
    }

    public Double getMaxT() {
        return maxT;
    }

    public void setMaxT(Double maxT) {
        this.maxT = maxT;
    }

    public Double getMinT() {
        return minT;
    }

    public void setMinT(Double minT) {
        this.minT = minT;
    }

    public Double getMaxH() {
        return maxH;
    }

    public void setMaxH(Double maxH) {
        this.maxH = maxH;
    }

    public Double getMinH() {
        return minH;
    }

    public void setMinH(Double minH) {
        this.minH = minH;
    }

    public InspectionData getInspectionData() {
        return inspectionData;
    }

    public void setInspectionData(InspectionData inspectionData) {
        this.inspectionData = inspectionData;
    }

    @Override
    public String toString() {
        return "InspectionDataDTO{" +
                "maxT=" + maxT +
                ", minT=" + minT +
                ", maxH=" + maxH +
                ", minH=" + minH +
                ", inspectionData=" + inspectionData +
                '}';
    }
}