package com.grandeflorum.buildingTable.domain;

import javax.persistence.Column;

public class H {

    @Column(name="ID")
    private String id;

    /**
     * 不动产单元号
     */
    @Column(name="BDCDYH")
    private String bdcdyh;

    /**
     * 房屋编码
     */
    @Column(name="FWBM")
    private String fwbm;

    /**
     * 层号
     */
    @Column(name="CH")
    private int ch;

    /**
     * 坐落
     */
    @Column(name="ZL")
    private String zl;

    /**
     * 实际层数
     */
    @Column(name="SJCS")
    private int sjcs;

    /**
     * 户号
     */
    @Column(name="HH")
    private int hh;

    @Column(name="SHBW")
    private String shbw;

    /**
     * 户型
     */
    @Column(name="HX")
    private String hx;

    /**
     * 户型结构
     */
    @Column(name="HXJG")
    private String hxjg;

    /***
     * 房屋用途
     */
    @Column(name="FWYT1")
    private String fwyt1;

    /**
     * 实测建筑面积
     */
    @Column(name="SCJZMJ")
    private double scjzmj;

    /**
     * 房屋类型
     */
    @Column(name="FWLX")
    private String fwlx;

    /**
     * 门牌号
     */
    @Column(name="MPH")
    private String mph;

    /**
     * 单元号
     */
    @Column(name="DYH")
    private int dyh;

    /**
     * 户编号
     */
    @Column(name="HBH")
    private int hbh;

    /**
     * 状态
     */
    @Column(name="status")
    private int status;

    @Column(name="cfStatus")
    private int cfStatus;


    public int getCfStatus() {
        return cfStatus;
    }

    public void setCfStatus(int cfStatus) {
        this.cfStatus = cfStatus;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBdcdyh() {
        return bdcdyh;
    }

    public void setBdcdyh(String bdcdyh) {
        this.bdcdyh = bdcdyh;
    }

    public String getFwbm() {
        return fwbm;
    }

    public void setFwbm(String fwbm) {
        this.fwbm = fwbm;
    }

    public int getCh() {
        return ch;
    }

    public void setCh(int ch) {
        this.ch = ch;
    }

    public String getZl() {
        return zl;
    }

    public void setZl(String zl) {
        this.zl = zl;
    }

    public int getSjcs() {
        return sjcs;
    }

    public void setSjcs(int sjcs) {
        this.sjcs = sjcs;
    }

    public int getHh() {
        return hh;
    }

    public void setHh(int hh) {
        this.hh = hh;
    }

    public String getShbw() {
        return shbw;
    }

    public void setShbw(String shbw) {
        this.shbw = shbw;
    }

    public String getHx() {
        return hx;
    }

    public void setHx(String hx) {
        this.hx = hx;
    }

    public String getHxjg() {
        return hxjg;
    }

    public void setHxjg(String hxjg) {
        this.hxjg = hxjg;
    }

    public String getFwyt1() {
        return fwyt1;
    }

    public void setFwyt1(String fwyt1) {
        this.fwyt1 = fwyt1;
    }

    public double getScjzmj() {
        return scjzmj;
    }

    public void setScjzmj(double scjzmj) {
        this.scjzmj = scjzmj;
    }

    public String getFwlx() {
        return fwlx;
    }

    public void setFwlx(String fwlx) {
        this.fwlx = fwlx;
    }

    public String getMph() {
        return mph;
    }

    public void setMph(String mph) {
        this.mph = mph;
    }

    public int getDyh() {
        return dyh;
    }

    public void setDyh(int dyh) {
        this.dyh = dyh;
    }

    public int getHbh() {
        return hbh;
    }

    public void setHbh(int hbh) {
        this.hbh = hbh;
    }
}
