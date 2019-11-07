package com.grandeflorum.buildingTable.domain;

import javax.persistence.Column;
import java.util.List;

public class ZRZ {

    /**
     * id
     */
    @Column(name = "ID")
    private String id;

//    /**
//     * 不动产单元号
//     */
//    @Column(name ="BDCDYH")
//    private String bdcdyh;

    /**
     * 自然幢号
     */
    @Column(name ="ZRZH")
    private String zrzh;

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
     * 逻辑幢集合
     */
    private List<LJZ> ljzList;

//    /**
//     * 实测建筑面积
//     */
//    @Column(name="SCJZMJ")
//    private double scjzmj;
//
//    /**
//     * 总层数
//     */
//    @Column(name ="ZCS")
//    private int zcs;


    public List<LJZ> getLjzList() {
        return ljzList;
    }

    public void setLjzList(List<LJZ> ljzList) {
        this.ljzList = ljzList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getZrzh() {
        return zrzh;
    }

    public void setZrzh(String zrzh) {
        this.zrzh = zrzh;
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
}
