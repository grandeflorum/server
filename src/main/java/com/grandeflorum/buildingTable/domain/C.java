package com.grandeflorum.buildingTable.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Table(name = "FG_C")

public class C {
    @Id
    @Column(name="ID")
    private String id;

    /**
     * 自然幢号
     */
    @Column(name = "ZRZH")
    private String zrzh;

    /**
     * 要素代码
     */
    @Column(name = "YSDM")
    private String ysdm;
    /**
     * 实际层
     */
    @Column(name="SJC")
    private Integer sjc;
    /**
     * 名义层
     */
    @Column(name = "MYC")
    private String myc;
    /**
     * 层建筑面积平方米
     */
    @Column(name = "CJZMJ")
    private Double cjzmj;

    /**
     * "层套内建筑面积"
     */
    @Column(name = "CTNJZMJ")
    private Double ctnjzmj;

    /**
     * 层阳台面积
     */
    @Column(name = "CYTMJ")
    private Double cytmj;

    /**
     * 层共有建筑面积
     */
    @Column(name = "CGYJZMJ")
    private Double cgyjzmj;

    /**
     * 层分摊建筑面积
     */
    @Column(name = "CFTJZMJ")
    private Double cftjzmj;

    /**
     * 层半墙面积
     */
    @Column(name = "CBQMJ")
    private Double cbqmj;

    /**
     * 层高单位：米
     */
    @Column(name = "CG")
    private Double cg;

    /**
     * 水平投影面积
     */
    @Column(name = "SPTYMJ")
    private Double sptymj;

    /**
     * 层号
     */
    @Column(name="CH")
    private Integer ch;

    /**
     * 逻辑幢号
     */
    @Column(name = "LJZH")
    private String ljzh;

    /**
     * 坐落（根据自然幢号和坐落 和 ljz关联）
     */
    @Column(name = "ZL")
    private String zl;

    @Column(name = "ZT")
    private String zt;

    @Column(name = "YLJZH")
    private String yljzh;

    @Column(name = "CID")
    private Double cid;

    /**
     * 是否区分单元
     */
    @Column(name="SFQFDY")
    private String sfqfdy;

    /**
     * 区县代码
     */
    @Column(name = "QXDM")
    private String qxdm;
    /**
     * 最大数
     */
    private int count;


    /**
     * 户集合
     */
    private List<H> hList;

    public List<H> gethList() {
        return hList;
    }

    public void sethList(List<H> hList) {
        this.hList = hList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSfqfdy() {
        return sfqfdy;
    }

    public void setSfqfdy(String sfqfdy) {
        this.sfqfdy = sfqfdy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getSjc() {
        return sjc;
    }

    public void setSjc(Integer sjc) {
        this.sjc = sjc;
    }

    public Integer getCh() {
        return ch;
    }

    public void setCh(Integer ch) {
        this.ch = ch;
    }

    public String getZrzh() {
        return zrzh;
    }

    public void setZrzh(String zrzh) {
        this.zrzh = zrzh;
    }

    public String getYsdm() {
        return ysdm;
    }

    public void setYsdm(String ysdm) {
        this.ysdm = ysdm;
    }

    public String getMyc() {
        return myc;
    }

    public void setMyc(String myc) {
        this.myc = myc;
    }

    public Double getCjzmj() {
        return cjzmj;
    }

    public void setCjzmj(Double cjzmj) {
        this.cjzmj = cjzmj;
    }

    public Double getCtnjzmj() {
        return ctnjzmj;
    }

    public void setCtnjzmj(Double ctnjzmj) {
        this.ctnjzmj = ctnjzmj;
    }

    public Double getCytmj() {
        return cytmj;
    }

    public void setCytmj(Double cytmj) {
        this.cytmj = cytmj;
    }

    public Double getCgyjzmj() {
        return cgyjzmj;
    }

    public void setCgyjzmj(Double cgyjzmj) {
        this.cgyjzmj = cgyjzmj;
    }

    public Double getCftjzmj() {
        return cftjzmj;
    }

    public void setCftjzmj(Double cftjzmj) {
        this.cftjzmj = cftjzmj;
    }

    public Double getCbqmj() {
        return cbqmj;
    }

    public void setCbqmj(Double cbqmj) {
        this.cbqmj = cbqmj;
    }

    public Double getCg() {
        return cg;
    }

    public void setCg(Double cg) {
        this.cg = cg;
    }

    public Double getSptymj() {
        return sptymj;
    }

    public void setSptymj(Double sptymj) {
        this.sptymj = sptymj;
    }

    public String getLjzh() {
        return ljzh;
    }

    public void setLjzh(String ljzh) {
        this.ljzh = ljzh;
    }

    public String getZl() {
        return zl;
    }

    public void setZl(String zl) {
        this.zl = zl;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getYljzh() {
        return yljzh;
    }

    public void setYljzh(String yljzh) {
        this.yljzh = yljzh;
    }

    public Double getCid() {
        return cid;
    }

    public void setCid(Double cid) {
        this.cid = cid;
    }

    public String getQxdm() {
        return qxdm;
    }

    public void setQxdm(String qxdm) {
        this.qxdm = qxdm;
    }
}
