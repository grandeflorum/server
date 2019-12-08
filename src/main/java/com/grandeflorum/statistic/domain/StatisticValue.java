package com.grandeflorum.statistic.domain;

/**
 * Created by 13260 on 2019/12/1.
 */
public class StatisticValue {

    private String dateValue;

    /**
     * 租金
     */
    private double zj;

    /**
     * 套数
     */
    private double ts;

    /**
     * 面积
     */
    private double mj;

    /**
     * 名称
     */
    private String name;

    /**
     * 行政区划名
     */
    private String regionName;

    /**
     * 同比
     */
    private double tb;

    /**
     * 同比标志
     */
    private String tbFlag;

    /**
     * 环比
     */
    private double hb;

    /**
     * 环比标志
     */
    private String hbFlag;


    public double getMj() {
        return mj;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMj(double mj) {
        this.mj = mj;
    }

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }

    public double getZj() {
        return zj;
    }

    public void setZj(double zj) {
        this.zj = zj;
    }

    public double getTs() {
        return ts;
    }

    public void setTs(double ts) {
        this.ts = ts;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public double getTb() {
        return tb;
    }

    public void setTb(double tb) {
        this.tb = tb;
    }


    public double getHb() {
        return hb;
    }

    public void setHb(double hb) {
        this.hb = hb;
    }

    public String getTbFlag() {
        return tbFlag;
    }

    public void setTbFlag(String tbFlag) {
        this.tbFlag = tbFlag;
    }

    public String getHbFlag() {
        return hbFlag;
    }

    public void setHbFlag(String hbFlag) {
        this.hbFlag = hbFlag;
    }
}
