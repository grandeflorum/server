package com.grandeflorum.buildingTable.domain;

import javax.persistence.Column;

public class ResultList {

    /**
     * 项目名称
     */
    @Column(name="XMMC")
    private String xmmc;

    /**
     * 建筑物名称
     */
    @Column(name="JZWMC")
    private String jzwmc;

    /**
     * 楼幢桩数
     */
    @Column(name="LZZS")
    private int lzzs;

    /**
     * 总套数
     */
    @Column(name="ZTS")
    private int zts;

    /**
     * 总面积
     */
    @Column(name="ZMJ")
    private double zmj;

    @Column(name="ID")
    private String id;

    /**
     * 数据来源1:备案系统数据，2：登记系统数据
     */
    @Column(name="DATASOURCE")
    private Integer datasource;

    /**
     * 数据来源1:备案系统数据，2：登记系统数据
     */
    @Column(name="AUDIT_TYPE")
    private Integer auditType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getJzwmc() {
        return jzwmc;
    }

    public void setJzwmc(String jzwmc) {
        this.jzwmc = jzwmc;
    }

    public int getLzzs() {
        return lzzs;
    }

    public void setLzzs(int lzzs) {
        this.lzzs = lzzs;
    }

    public int getZts() {
        return zts;
    }

    public void setZts(int zts) {
        this.zts = zts;
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

    public Integer getAuditType() {
        return auditType;
    }

    public void setAuditType(Integer auditType) {
        this.auditType = auditType;
    }
}
