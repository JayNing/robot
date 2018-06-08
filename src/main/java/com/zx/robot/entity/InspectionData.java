package com.zx.robot.entity;

import java.io.Serializable;
import java.util.Date;
/**
 * 机器人数据实体类
 * */
public class InspectionData implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;

    private Double hight;

    private Double highh;

    private Double middlet;

    private Double middleh;

    private Double lowt;

    private Double lowh;

    private Double armt;

    private Double windspeed;



    private Double robotx;

    private Double roboty;

    private Double roboth;

    private Double battery;

    private String target;

    private Date createtime;

    public InspectionData() {
    }

    public InspectionData(Double hight, Double highh, Double middlet, Double middleh, Double lowt, Double lowh,
                          Double armt, Double windspeed, Double robotx, Double roboty, Double roboth, Double battery,
                          String target, Date createtime) {
        this.hight = hight;
        this.highh = highh;
        this.middlet = middlet;
        this.middleh = middleh;
        this.lowt = lowt;
        this.lowh = lowh;
        this.armt = armt;
        this.windspeed = windspeed;
        this.robotx = robotx;
        this.roboty = roboty;
        this.roboth = roboth;
        this.battery = battery;
        this.target = target;
        this.createtime = createtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getHight() {
        return hight;
    }

    public void setHight(Double hight) {
        this.hight = hight;
    }

    public Double getHighh() {
        return highh;
    }

    public void setHighh(Double highh) {
        this.highh = highh;
    }

    public Double getMiddlet() {
        return middlet;
    }

    public void setMiddlet(Double middlet) {
        this.middlet = middlet;
    }

    public Double getMiddleh() {
        return middleh;
    }

    public void setMiddleh(Double middleh) {
        this.middleh = middleh;
    }

    public Double getLowt() {
        return lowt;
    }

    public void setLowt(Double lowt) {
        this.lowt = lowt;
    }

    public Double getLowh() {
        return lowh;
    }

    public void setLowh(Double lowh) {
        this.lowh = lowh;
    }

    public Double getArmt() {
        return armt;
    }

    public void setArmt(Double armt) {
        this.armt = armt;
    }

    public Double getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(Double windspeed) {
        this.windspeed = windspeed;
    }

    public Double getRobotx() {
        return robotx;
    }

    public void setRobotx(Double robotx) {
        this.robotx = robotx;
    }

    public Double getRoboty() {
        return roboty;
    }

    public void setRoboty(Double roboty) {
        this.roboty = roboty;
    }

    public Double getRoboth() {
        return roboth;
    }

    public void setRoboth(Double roboth) {
        this.roboth = roboth;
    }

    public Double getBattery() {
        return battery;
    }

    public void setBattery(Double battery) {
        this.battery = battery;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target == null ? null : target.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "InspectionData{" +
                "id=" + id +
                ", hight=" + hight +
                ", highh=" + highh +
                ", middlet=" + middlet +
                ", middleh=" + middleh +
                ", lowt=" + lowt +
                ", lowh=" + lowh +
                ", armt=" + armt +
                ", windspeed=" + windspeed +
                ", robotx=" + robotx +
                ", roboty=" + roboty +
                ", roboth=" + roboth +
                ", battery=" + battery +
                ", target='" + target + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}