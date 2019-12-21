package com.grandeflorum.buildingTable.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;

@Table(name = "FG_H")
public class H {
    @Id
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

    @Column(name = "YSDM")
    private String ysdm;

    @Column(name = "ZRZH")
    private String zrzh;

    @Column(name = "LJZH")
    private String ljzh;
    /**
     * 层号
     */
    @Column(name="CH")
    private Integer ch;

    /**
     * 坐落
     */
    @Column(name="ZL")
    private String zl;

    @Column(name = "MJDW")
    private Integer mjdw;
    /**
     * 实际层数
     */
    @Column(name="SJCS")
    private Integer sjcs;

    /**
     * 户号(排序用的)
     */
    @Column(name="HH")
    private Integer hh;

    @Column(name="SHBW")
    private String shbw;

    /**
     * 户型
     */
    @Column(name="HX")
    private Integer hx;

    /**
     * 户型结构
     */
    @Column(name="HXJG")
    private Integer hxjg;

    /***
     * 房屋用途
     */
    @Column(name="FWYT1")
    private Integer fwyt1;

    @Column(name = "FWYT2")
    private Integer fwyt2;

    @Column(name = "FWYT3")
    private Integer fwyt3;

    @Column(name = "YCJZMJ")
    private Double ycjzmj;

    @Column(name = "YCTNJZMJ")
    private Double yctnjzmj;

    @Column(name = "YCFTJZMJ")
    private Double ycftjzmj;

    @Column(name = "YCDXBFJZMJ")
    private Double ycdxbfjzmj;

    @Column(name = "YCQTJZMJ")
    private Double ycqtjzmj;

    @Column(name = "YCFTXS")
    private Double ycftxs;
    /**
     * 实测建筑面积
     */
    @Column(name="SCJZMJ")
    private Double scjzmj;

    @Column(name = "SCTNJZMJ")
    private Double sctnjzmj;

    @Column(name = "SCFTJZMJ")
    private Double scftjzmj;

    @Column(name = "SCDXBFJZMJ")
    private Double scdxbfjzmj;

    @Column(name = "SCQTJZMJ")
    private Double scqtjzmj;

    @Column(name = "SCFTXS")
    private Double scftxs;

    @Column(name = "GYTDMJ")
    private Double gytdmj;

    @Column(name = "FTTDMJ")
    private Double fttdmj;

    @Column(name = "DYTDMJ")
    private Double dytdmj;
    /**
     * 房屋类型
     */
    @Column(name="FWLX")
    private Integer fwlx;

    @Column(name = "FWXZ")
    private Integer fwxz;

    @Column(name = "FCFHT")
    private String fcfht;

    @Column(name = "BZ")
    private String bz;
    /**
     * 门牌号
     */
    @Column(name="MPH")
    private String mph;

    @Column(name = "ZT")
    private String zt;

    @Column(name = "FWID")
    private Double fwid;

    @Column(name = "ZID")
    private Double zid;

    @Column(name = "DH")
    private String dh;

    /**
     * 单元号
     */
    @Column(name="DYH")
    private Integer dyh;

    /**
     * 单元名称
     */
    @Column(name = "DYMC")
    private String dymc;

    @Column(name = "ZCS")
    private Double zcs;

    @Column(name = "ZBM")
    private String zbm;

    @Column(name = "ZDDM")
    private String zddm;

    @Column(name = "CHZT")
    private String chzt;

    @Column(name = "FGHBZT")
    private Double fghbzt;

    @Column(name = "FWLY")
    private String fwly;

    @Column(name = "FWSYZ")
    private String fwsyz;

    @Column(name = "FWZJ")
    private Double fwzj;

    /**
     * 户编号(在一层里面的第几户里面)
     */
    @Column(name="HBH")
    private Integer hbh;

    @Column(name = "FWBH")
    private String fwbh;

    @Column(name = "FWSYQR")
    private String fwsyqr;

    @Column(name = "QLRLX")
    private String qlrlx;

    @Column(name = "ZJZL")
    private String zjzl;

    @Column(name = "ZJHM")
    private String zjhm;

    @Column(name = "ZZ")
    private String zz;

    @Column(name = "TEL")
    private String tel;

    @Column(name = "YZBM")
    private String yzbm;

    @Column(name = "CQLY")
    private String cqly;

    @Column(name = "CB")
    private String cb;

    @Column(name = "GYQK")
    private String gyqk;

    @Column(name = "GHYT")
    private String ghyt;

    @Column(name = "ZYJZMJ")
    private Double zyjzmj;

    @Column(name = "FJSM")
    private String fjsm;

    @Column(name = "DCYJ")
    private String dcyj;

    @Column(name = "ZTS")
    private Double zts;

    @Column(name = "JGRQ")
    private Date jgrq;

    @Column(name = "XMMC")
    private String xmmc;

    @Column(name = "DQ")
    private String dq;

    @Column(name = "NQ")
    private String nq;

    @Column(name = "XQ")
    private String xq;

    @Column(name = "BQ")
    private String bq;

    @Column(name = "GGBW")
    private String ggbw;

    @Column(name = "TDYT")
    private String tdyt;

    @Column(name = "QLXZ")
    private String qlxz;

    @Column(name = "TDSYQX")
    private String tdsyqx;

    @Column(name = "TDSYQQSSJ")
    private Date tdsyqqssj;

    @Column(name = "TDSYQJSSJ")
    private Date tdsyqjssj;

    @Column(name = "YBDCDYH")
    private String ybdcdyh;

    /**
     * 用途名称
     */
    @Column(name = "YTMC")
    private String ytmc;

    /**
     * 房屋性质名称
     */
    @Column(name = "FWXZMC")
    private String fwxzmc;

    /**
     * 房屋类型名称
     */
    @Column(name = "FWLXMC")
    private String fwlxmc;

    @Column(name = "YLJZH")
    private String yljzh;

    @Column(name = "HID")
    private Double hid;

    @Column(name = "QXDM")
    private String qxdm;

    /**
     * 抵押状态
     */
    @Transient
    private Integer status;

    /**
     * 查封状态
     */
    @Transient
    private Integer cfStatus;

    /**
     * 备案状态
     */
    @Transient
    private Integer tradeType;

    /**
     * 租赁状态
     */
    @Transient
    private Integer zlStatus;

    /**
     *  1:商品房 2:存量房
     */
    @Column(name="ISNEWSTOCK")
    private Integer isnewstock;

    /**
     * 审核状态
     */
    @Column(name = "AUDIT_TYPE")
    public Integer auditType;

    public Integer getCfStatus() {
        return cfStatus;
    }

    public void setCfStatus(Integer cfStatus) {
        this.cfStatus = cfStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public Integer getCh() {
        return ch;
    }

    public void setCh(Integer ch) {
        this.ch = ch;
    }

    public String getZl() {
        return zl;
    }

    public void setZl(String zl) {
        this.zl = zl;
    }

    public Integer getSjcs() {
        return sjcs;
    }

    public void setSjcs(Integer sjcs) {
        this.sjcs = sjcs;
    }

    public Integer getHh() {
        return hh;
    }

    public void setHh(Integer hh) {
        this.hh = hh;
    }

    public String getShbw() {
        return shbw;
    }

    public void setShbw(String shbw) {
        this.shbw = shbw;
    }

    public Integer getHx() {
        return hx;
    }

    public void setHx(Integer hx) {
        this.hx = hx;
    }

    public Integer getHxjg() {
        return hxjg;
    }

    public void setHxjg(Integer hxjg) {
        this.hxjg = hxjg;
    }

    public Integer getFwyt1() {
        return fwyt1;
    }

    public void setFwyt1(Integer fwyt1) {
        this.fwyt1 = fwyt1;
    }

    public Double getScjzmj() {
        return scjzmj;
    }

    public void setScjzmj(Double scjzmj) {
        this.scjzmj = scjzmj;
    }

    public Integer getFwlx() {
        return fwlx;
    }

    public void setFwlx(Integer fwlx) {
        this.fwlx = fwlx;
    }

    public String getMph() {
        return mph;
    }

    public void setMph(String mph) {
        this.mph = mph;
    }

    public Integer getDyh() {
        return dyh;
    }

    public void setDyh(Integer dyh) {
        this.dyh = dyh;
    }

    public Integer getHbh() {
        return hbh;
    }

    public void setHbh(Integer hbh) {
        this.hbh = hbh;
    }

    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }

    public String getYsdm() {
        return ysdm;
    }

    public void setYsdm(String ysdm) {
        this.ysdm = ysdm;
    }

    public String getZrzh() {
        return zrzh;
    }

    public void setZrzh(String zrzh) {
        this.zrzh = zrzh;
    }

    public String getLjzh() {
        return ljzh;
    }

    public void setLjzh(String ljzh) {
        this.ljzh = ljzh;
    }

    public Integer getMjdw() {
        return mjdw;
    }

    public void setMjdw(Integer mjdw) {
        this.mjdw = mjdw;
    }

    public Integer getFwyt2() {
        return fwyt2;
    }

    public void setFwyt2(Integer fwyt2) {
        this.fwyt2 = fwyt2;
    }

    public Integer getFwyt3() {
        return fwyt3;
    }

    public void setFwyt3(Integer fwyt3) {
        this.fwyt3 = fwyt3;
    }

    public Double getYcjzmj() {
        return ycjzmj;
    }

    public void setYcjzmj(Double ycjzmj) {
        this.ycjzmj = ycjzmj;
    }

    public Double getYctnjzmj() {
        return yctnjzmj;
    }

    public void setYctnjzmj(Double yctnjzmj) {
        this.yctnjzmj = yctnjzmj;
    }

    public Double getYcftjzmj() {
        return ycftjzmj;
    }

    public void setYcftjzmj(Double ycftjzmj) {
        this.ycftjzmj = ycftjzmj;
    }

    public Double getYcdxbfjzmj() {
        return ycdxbfjzmj;
    }

    public void setYcdxbfjzmj(Double ycdxbfjzmj) {
        this.ycdxbfjzmj = ycdxbfjzmj;
    }

    public Double getYcqtjzmj() {
        return ycqtjzmj;
    }

    public void setYcqtjzmj(Double ycqtjzmj) {
        this.ycqtjzmj = ycqtjzmj;
    }

    public Double getYcftxs() {
        return ycftxs;
    }

    public void setYcftxs(Double ycftxs) {
        this.ycftxs = ycftxs;
    }

    public Double getSctnjzmj() {
        return sctnjzmj;
    }

    public void setSctnjzmj(Double sctnjzmj) {
        this.sctnjzmj = sctnjzmj;
    }

    public Double getScftjzmj() {
        return scftjzmj;
    }

    public void setScftjzmj(Double scftjzmj) {
        this.scftjzmj = scftjzmj;
    }

    public Double getScdxbfjzmj() {
        return scdxbfjzmj;
    }

    public void setScdxbfjzmj(Double scdxbfjzmj) {
        this.scdxbfjzmj = scdxbfjzmj;
    }

    public Double getScqtjzmj() {
        return scqtjzmj;
    }

    public void setScqtjzmj(Double scqtjzmj) {
        this.scqtjzmj = scqtjzmj;
    }

    public Double getScftxs() {
        return scftxs;
    }

    public void setScftxs(Double scftxs) {
        this.scftxs = scftxs;
    }

    public Double getGytdmj() {
        return gytdmj;
    }

    public void setGytdmj(Double gytdmj) {
        this.gytdmj = gytdmj;
    }

    public Double getFttdmj() {
        return fttdmj;
    }

    public void setFttdmj(Double fttdmj) {
        this.fttdmj = fttdmj;
    }

    public Double getDytdmj() {
        return dytdmj;
    }

    public void setDytdmj(Double dytdmj) {
        this.dytdmj = dytdmj;
    }

    public Integer getFwxz() {
        return fwxz;
    }

    public void setFwxz(Integer fwxz) {
        this.fwxz = fwxz;
    }

    public String getFcfht() {
        return fcfht;
    }

    public void setFcfht(String fcfht) {
        this.fcfht = fcfht;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public Double getFwid() {
        return fwid;
    }

    public void setFwid(Double fwid) {
        this.fwid = fwid;
    }

    public Double getZid() {
        return zid;
    }

    public void setZid(Double zid) {
        this.zid = zid;
    }

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }

    public String getDymc() {
        return dymc;
    }

    public void setDymc(String dymc) {
        this.dymc = dymc;
    }

    public Double getZcs() {
        return zcs;
    }

    public void setZcs(Double zcs) {
        this.zcs = zcs;
    }

    public String getZbm() {
        return zbm;
    }

    public void setZbm(String zbm) {
        this.zbm = zbm;
    }

    public String getZddm() {
        return zddm;
    }

    public void setZddm(String zddm) {
        this.zddm = zddm;
    }

    public String getChzt() {
        return chzt;
    }

    public void setChzt(String chzt) {
        this.chzt = chzt;
    }

    public Double getFghbzt() {
        return fghbzt;
    }

    public void setFghbzt(Double fghbzt) {
        this.fghbzt = fghbzt;
    }

    public String getFwly() {
        return fwly;
    }

    public void setFwly(String fwly) {
        this.fwly = fwly;
    }

    public String getFwsyz() {
        return fwsyz;
    }

    public void setFwsyz(String fwsyz) {
        this.fwsyz = fwsyz;
    }

    public Double getFwzj() {
        return fwzj;
    }

    public void setFwzj(Double fwzj) {
        this.fwzj = fwzj;
    }

    public String getFwbh() {
        return fwbh;
    }

    public void setFwbh(String fwbh) {
        this.fwbh = fwbh;
    }

    public String getFwsyqr() {
        return fwsyqr;
    }

    public void setFwsyqr(String fwsyqr) {
        this.fwsyqr = fwsyqr;
    }

    public String getQlrlx() {
        return qlrlx;
    }

    public void setQlrlx(String qlrlx) {
        this.qlrlx = qlrlx;
    }

    public String getZjzl() {
        return zjzl;
    }

    public void setZjzl(String zjzl) {
        this.zjzl = zjzl;
    }

    public String getZjhm() {
        return zjhm;
    }

    public void setZjhm(String zjhm) {
        this.zjhm = zjhm;
    }

    public String getZz() {
        return zz;
    }

    public void setZz(String zz) {
        this.zz = zz;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getYzbm() {
        return yzbm;
    }

    public void setYzbm(String yzbm) {
        this.yzbm = yzbm;
    }

    public String getCqly() {
        return cqly;
    }

    public void setCqly(String cqly) {
        this.cqly = cqly;
    }

    public String getCb() {
        return cb;
    }

    public void setCb(String cb) {
        this.cb = cb;
    }

    public String getGyqk() {
        return gyqk;
    }

    public void setGyqk(String gyqk) {
        this.gyqk = gyqk;
    }

    public String getGhyt() {
        return ghyt;
    }

    public void setGhyt(String ghyt) {
        this.ghyt = ghyt;
    }

    public Double getZyjzmj() {
        return zyjzmj;
    }

    public void setZyjzmj(Double zyjzmj) {
        this.zyjzmj = zyjzmj;
    }

    public String getFjsm() {
        return fjsm;
    }

    public void setFjsm(String fjsm) {
        this.fjsm = fjsm;
    }

    public String getDcyj() {
        return dcyj;
    }

    public void setDcyj(String dcyj) {
        this.dcyj = dcyj;
    }

    public Double getZts() {
        return zts;
    }

    public void setZts(Double zts) {
        this.zts = zts;
    }

    public Date getJgrq() {
        return jgrq;
    }

    public void setJgrq(Date jgrq) {
        this.jgrq = jgrq;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getDq() {
        return dq;
    }

    public void setDq(String dq) {
        this.dq = dq;
    }

    public String getNq() {
        return nq;
    }

    public void setNq(String nq) {
        this.nq = nq;
    }

    public String getXq() {
        return xq;
    }

    public void setXq(String xq) {
        this.xq = xq;
    }

    public String getBq() {
        return bq;
    }

    public void setBq(String bq) {
        this.bq = bq;
    }

    public String getGgbw() {
        return ggbw;
    }

    public void setGgbw(String ggbw) {
        this.ggbw = ggbw;
    }

    public String getTdyt() {
        return tdyt;
    }

    public void setTdyt(String tdyt) {
        this.tdyt = tdyt;
    }

    public String getQlxz() {
        return qlxz;
    }

    public void setQlxz(String qlxz) {
        this.qlxz = qlxz;
    }

    public String getTdsyqx() {
        return tdsyqx;
    }

    public void setTdsyqx(String tdsyqx) {
        this.tdsyqx = tdsyqx;
    }

    public Date getTdsyqqssj() {
        return tdsyqqssj;
    }

    public void setTdsyqqssj(Date tdsyqqssj) {
        this.tdsyqqssj = tdsyqqssj;
    }

    public Date getTdsyqjssj() {
        return tdsyqjssj;
    }

    public void setTdsyqjssj(Date tdsyqjssj) {
        this.tdsyqjssj = tdsyqjssj;
    }

    public String getYbdcdyh() {
        return ybdcdyh;
    }

    public void setYbdcdyh(String ybdcdyh) {
        this.ybdcdyh = ybdcdyh;
    }

    public String getYtmc() {
        return ytmc;
    }

    public void setYtmc(String ytmc) {
        this.ytmc = ytmc;
    }

    public String getFwxzmc() {
        return fwxzmc;
    }

    public void setFwxzmc(String fwxzmc) {
        this.fwxzmc = fwxzmc;
    }

    public String getFwlxmc() {
        return fwlxmc;
    }

    public void setFwlxmc(String fwlxmc) {
        this.fwlxmc = fwlxmc;
    }

    public String getYljzh() {
        return yljzh;
    }

    public void setYljzh(String yljzh) {
        this.yljzh = yljzh;
    }

    public Double getHid() {
        return hid;
    }

    public void setHid(Double hid) {
        this.hid = hid;
    }

    public String getQxdm() {
        return qxdm;
    }

    public void setQxdm(String qxdm) {
        this.qxdm = qxdm;
    }

    public Integer getIsnewstock() {
        return isnewstock;
    }

    public void setIsnewstock(Integer isnewstock) {
        this.isnewstock = isnewstock;
    }

    public Integer getAuditType() {
        return auditType;
    }

    public void setAuditType(Integer auditType) {
        this.auditType = auditType;
    }

    public Integer getZlStatus() {
        return zlStatus;
    }

    public void setZlStatus(Integer zlStatus) {
        this.zlStatus = zlStatus;
    }
}
