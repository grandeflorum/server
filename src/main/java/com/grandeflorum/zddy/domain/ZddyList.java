package com.grandeflorum.zddy.domain;

import javax.persistence.Column;
import javax.persistence.Transient;

/**
 * Created by 13260 on 2019/11/19.
 */
public class ZddyList {

    @Column(name="id")
    private String id;

    @Column(name="bdcdyh")
    private String bdcdyh;

    @Column(name="dybdclxName")
    private String dybdclxName;

    @Column(name="dyfsName")
    private String dyfsName;

    @Column(name="zjjzwzl")
    private String zjjzwzl;

    @Column(name="dyType")
    private int dyType;

    @Column(name="zmj")
    private double zmj;

    //20200208新增
    /**
     * 数据来源1:备案系统数据，2：登记系统数据
     */
    @Transient
    private Integer datasource;

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

    public String getDybdclxName() {
        return dybdclxName;
    }

    public void setDybdclxName(String dybdclxName) {
        this.dybdclxName = dybdclxName;
    }

    public String getDyfsName() {
        return dyfsName;
    }

    public void setDyfsName(String dyfsName) {
        this.dyfsName = dyfsName;
    }

    public String getZjjzwzl() {
        return zjjzwzl;
    }

    public void setZjjzwzl(String zjjzwzl) {
        this.zjjzwzl = zjjzwzl;
    }

    public int getDyType() {
        return dyType;
    }

    public void setDyType(int dyType) {
        this.dyType = dyType;
    }

    public double getZmj() {
        return zmj;
    }

    public void setZmj(double zmj) {
        this.zmj = zmj;
    }

    public Integer getDatasource() {
        return datasource;
    }

    public void setDatasource(Integer datasource) {
        this.datasource = datasource;
    }
}
