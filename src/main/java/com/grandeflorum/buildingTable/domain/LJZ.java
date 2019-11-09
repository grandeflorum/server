package com.grandeflorum.buildingTable.domain;

import javax.persistence.Column;
import java.util.List;

public class LJZ {

    @Column(name="ID")
    private String id;

    /**
     * 逻辑幢号
     */
    @Column(name="LJZH")
    private String ljzh;

    /**
     * 自然幢号
     */
    @Column(name="ZRZH")
    private String zrzh;

    /**
     * 门牌号
     */
    @Column(name="MPH")
    private String mph;

    /**
     * 实测建筑面积
     */
    @Column(name="SCJZMJ")
    private double scjzmj;

    /**
     * 房屋用途
     */
    @Column(name="FWYT1")
    private int fwyt1;

    /**
     * 总层数
     */
    @Column(name ="ZCS")
    private int zcs;


    /**
     * 单元对象
     */
    private List<DY> dyList;

    /**
     * 层对象
     */
    private List<C> cList;

    private LJZStatistical ljzStatistical;

    public LJZStatistical getLjzStatistical() {
        return ljzStatistical;
    }

    public void setLjzStatistical(LJZStatistical ljzStatistical) {
        this.ljzStatistical = ljzStatistical;
    }

    public List<DY> getDyList() {
        return dyList;
    }

    public void setDyList(List<DY> dyList) {
        this.dyList = dyList;
    }

    public List<C> getcList() {
        return cList;
    }

    public void setcList(List<C> cList) {
        this.cList = cList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLjzh() {
        return ljzh;
    }

    public void setLjzh(String ljzh) {
        this.ljzh = ljzh;
    }

    public String getZrzh() {
        return zrzh;
    }

    public void setZrzh(String zrzh) {
        this.zrzh = zrzh;
    }

    public String getMph() {
        return mph;
    }

    public void setMph(String mph) {
        this.mph = mph;
    }

    public double getScjzmj() {
        return scjzmj;
    }

    public void setScjzmj(double scjzmj) {
        this.scjzmj = scjzmj;
    }

    public int getFwyt1() {
        return fwyt1;
    }

    public void setFwyt1(int fwyt1) {
        this.fwyt1 = fwyt1;
    }

    public int getZcs() {
        return zcs;
    }

    public void setZcs(int zcs) {
        this.zcs = zcs;
    }
}
