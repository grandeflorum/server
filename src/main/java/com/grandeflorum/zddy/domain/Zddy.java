package com.grandeflorum.zddy.domain;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;

@Table(name = "Zddy")
public class Zddy {
    @Id
    @Column(name = "ID")
    private String id;
    //
    @Column(name = "YSDM")
    private String ysdm;

    @Column(name = "BDCDYH")
    private String bdcdyh;

    @Column(name = "YWH")
    private String ywh;

    @Column(name = "DYBDCLX")
    private String dybdclx;

    @Column(name = "DYR")
    private String dyr;

    @Column(name = "DYFS")
    private String dyfs;

    @Column(name = "DJLX")
    private String djlx;

    @Column(name = "DJYY")
    private String djyy;

    @Column(name = "ZJJZWZL")
    private String zjjzwzl;

    @Column(name = "ZJJZWDYFW")
    private String zjjzwdyfw;

    @Column(name = "BDBZZQSE")
    private BigDecimal bdbzzqse;

    @Column(name = "ZWLXQSSJ")
    private Date zwlxqssj;

    @Column(name = "ZWLXJSSJ")
    private Date zwlxjssj;

    @Column(name = "ZGZQQDSS")
    private String zgzqqdss;

    @Column(name = "ZGZQSE")
    private BigDecimal zgzqse;

    @Column(name = "ZXDYYWH")
    private String zxdyywh;

    @Column(name = "ZXDYYY")
    private String zxdyyy;

    @Column(name = "ZXSJ")
    private Date zxsj;

    @Column(name = "BDCDJZMH")
    private String bdcdjzmh;

    @Column(name = "QXDM")
    private String qxdm;

    @Column(name = "DJJG")
    private String djjg;

    @Column(name = "DBR")
    private String dbr;

    @Column(name = "DJSJ")
    private Date djsj;

    @Column(name = "FJ")
    private String fj;

    @Column(name = "QSZT")
    private String qszt;

    @Column(name = "YBDCQZH")
    private String ybdcqzh;

    @Column(name = "QLLX")
    private String qllx;

    @Column(name = "ZL")
    private String zl;

    @Column(name = "ISLOAD")
    private Short isload;

    @Column(name = "LSYWH")
    private String lsywh;

    @Column(name = "SYS_DATE")
    private Date sysDate;

    @Column(name = "SYS_UPD_DATE")
    private Date sysUpdDate;

    @Column(name = "DY_TYPE")
    private Short dyType;

    @Column(name = "ZRZH")
    private String zrzh;
    /**
     *
     */
    @Column(name = "LJZH")
    private String ljzh;


    /**
     * 登记人
     */
    @Column(name="DJR")
    public String djr;


    public String getDjr() {
        return djr;
    }

    public void setDjr(String djr) {
        this.djr = djr;
    }

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return YSDM
     */
    public String getYsdm() {
        return ysdm;
    }

    /**
     * @param ysdm
     */
    public void setYsdm(String ysdm) {
        this.ysdm = ysdm == null ? null : ysdm.trim();
    }

    /**
     * @return BDCDYH
     */
    public String getBdcdyh() {
        return bdcdyh;
    }

    /**
     * @param bdcdyh
     */
    public void setBdcdyh(String bdcdyh) {
        this.bdcdyh = bdcdyh == null ? null : bdcdyh.trim();
    }

    /**
     * @return YWH
     */
    public String getYwh() {
        return ywh;
    }

    /**
     * @param ywh
     */
    public void setYwh(String ywh) {
        this.ywh = ywh == null ? null : ywh.trim();
    }

    /**
     * @return DYBDCLX
     */
    public String getDybdclx() {
        return dybdclx;
    }

    /**
     * @param dybdclx
     */
    public void setDybdclx(String dybdclx) {
        this.dybdclx = dybdclx == null ? null : dybdclx.trim();
    }

    /**
     * @return DYR
     */
    public String getDyr() {
        return dyr;
    }

    /**
     * @param dyr
     */
    public void setDyr(String dyr) {
        this.dyr = dyr == null ? null : dyr.trim();
    }

    /**
     * @return DYFS
     */
    public String getDyfs() {
        return dyfs;
    }

    /**
     * @param dyfs
     */
    public void setDyfs(String dyfs) {
        this.dyfs = dyfs == null ? null : dyfs.trim();
    }

    /**
     * @return DJLX
     */
    public String getDjlx() {
        return djlx;
    }

    /**
     * @param djlx
     */
    public void setDjlx(String djlx) {
        this.djlx = djlx == null ? null : djlx.trim();
    }

    /**
     * @return DJYY
     */
    public String getDjyy() {
        return djyy;
    }

    /**
     * @param djyy
     */
    public void setDjyy(String djyy) {
        this.djyy = djyy == null ? null : djyy.trim();
    }

    /**
     * @return ZJJZWZL
     */
    public String getZjjzwzl() {
        return zjjzwzl;
    }

    /**
     * @param zjjzwzl
     */
    public void setZjjzwzl(String zjjzwzl) {
        this.zjjzwzl = zjjzwzl == null ? null : zjjzwzl.trim();
    }

    /**
     * @return ZJJZWDYFW
     */
    public String getZjjzwdyfw() {
        return zjjzwdyfw;
    }

    /**
     * @param zjjzwdyfw
     */
    public void setZjjzwdyfw(String zjjzwdyfw) {
        this.zjjzwdyfw = zjjzwdyfw == null ? null : zjjzwdyfw.trim();
    }

    /**
     * @return BDBZZQSE
     */
    public BigDecimal getBdbzzqse() {
        return bdbzzqse;
    }

    /**
     * @param bdbzzqse
     */
    public void setBdbzzqse(BigDecimal bdbzzqse) {
        this.bdbzzqse = bdbzzqse;
    }

    /**
     * @return ZWLXQSSJ
     */
    public Date getZwlxqssj() {
        return zwlxqssj;
    }

    /**
     * @param zwlxqssj
     */
    public void setZwlxqssj(Date zwlxqssj) {
        this.zwlxqssj = zwlxqssj;
    }

    /**
     * @return ZWLXJSSJ
     */
    public Date getZwlxjssj() {
        return zwlxjssj;
    }

    /**
     * @param zwlxjssj
     */
    public void setZwlxjssj(Date zwlxjssj) {
        this.zwlxjssj = zwlxjssj;
    }

    /**
     * @return ZGZQQDSS
     */
    public String getZgzqqdss() {
        return zgzqqdss;
    }

    /**
     * @param zgzqqdss
     */
    public void setZgzqqdss(String zgzqqdss) {
        this.zgzqqdss = zgzqqdss == null ? null : zgzqqdss.trim();
    }

    /**
     * @return ZGZQSE
     */
    public BigDecimal getZgzqse() {
        return zgzqse;
    }

    /**
     * @param zgzqse
     */
    public void setZgzqse(BigDecimal zgzqse) {
        this.zgzqse = zgzqse;
    }

    /**
     * @return ZXDYYWH
     */
    public String getZxdyywh() {
        return zxdyywh;
    }

    /**
     * @param zxdyywh
     */
    public void setZxdyywh(String zxdyywh) {
        this.zxdyywh = zxdyywh == null ? null : zxdyywh.trim();
    }

    /**
     * @return ZXDYYY
     */
    public String getZxdyyy() {
        return zxdyyy;
    }

    /**
     * @param zxdyyy
     */
    public void setZxdyyy(String zxdyyy) {
        this.zxdyyy = zxdyyy == null ? null : zxdyyy.trim();
    }

    /**
     * @return ZXSJ
     */
    public Date getZxsj() {
        return zxsj;
    }

    /**
     * @param zxsj
     */
    public void setZxsj(Date zxsj) {
        this.zxsj = zxsj;
    }

    /**
     * @return BDCDJZMH
     */
    public String getBdcdjzmh() {
        return bdcdjzmh;
    }

    /**
     * @param bdcdjzmh
     */
    public void setBdcdjzmh(String bdcdjzmh) {
        this.bdcdjzmh = bdcdjzmh == null ? null : bdcdjzmh.trim();
    }

    /**
     * @return QXDM
     */
    public String getQxdm() {
        return qxdm;
    }

    /**
     * @param qxdm
     */
    public void setQxdm(String qxdm) {
        this.qxdm = qxdm == null ? null : qxdm.trim();
    }

    /**
     * @return DJJG
     */
    public String getDjjg() {
        return djjg;
    }

    /**
     * @param djjg
     */
    public void setDjjg(String djjg) {
        this.djjg = djjg == null ? null : djjg.trim();
    }

    /**
     * @return DBR
     */
    public String getDbr() {
        return dbr;
    }

    /**
     * @param dbr
     */
    public void setDbr(String dbr) {
        this.dbr = dbr == null ? null : dbr.trim();
    }

    /**
     * @return DJSJ
     */
    public Date getDjsj() {
        return djsj;
    }

    /**
     * @param djsj
     */
    public void setDjsj(Date djsj) {
        this.djsj = djsj;
    }

    /**
     * @return FJ
     */
    public String getFj() {
        return fj;
    }

    /**
     * @param fj
     */
    public void setFj(String fj) {
        this.fj = fj == null ? null : fj.trim();
    }

    /**
     * @return QSZT
     */
    public String getQszt() {
        return qszt;
    }

    /**
     * @param qszt
     */
    public void setQszt(String qszt) {
        this.qszt = qszt == null ? null : qszt.trim();
    }

    /**
     * @return YBDCQZH
     */
    public String getYbdcqzh() {
        return ybdcqzh;
    }

    /**
     * @param ybdcqzh
     */
    public void setYbdcqzh(String ybdcqzh) {
        this.ybdcqzh = ybdcqzh == null ? null : ybdcqzh.trim();
    }

    /**
     * @return QLLX
     */
    public String getQllx() {
        return qllx;
    }

    /**
     * @param qllx
     */
    public void setQllx(String qllx) {
        this.qllx = qllx == null ? null : qllx.trim();
    }

    /**
     * @return ZL
     */
    public String getZl() {
        return zl;
    }

    /**
     * @param zl
     */
    public void setZl(String zl) {
        this.zl = zl == null ? null : zl.trim();
    }

    /**
     * @return ISLOAD
     */
    public Short getIsload() {
        return isload;
    }

    /**
     * @param isload
     */
    public void setIsload(Short isload) {
        this.isload = isload;
    }

    /**
     * @return LSYWH
     */
    public String getLsywh() {
        return lsywh;
    }

    /**
     * @param lsywh
     */
    public void setLsywh(String lsywh) {
        this.lsywh = lsywh == null ? null : lsywh.trim();
    }

    /**
     * @return SYS_DATE
     */
    public Date getSysDate() {
        return sysDate;
    }

    /**
     * @param sysDate
     */
    public void setSysDate(Date sysDate) {
        this.sysDate = sysDate;
    }

    /**
     * @return SYS_UPD_DATE
     */
    public Date getSysUpdDate() {
        return sysUpdDate;
    }

    /**
     * @param sysUpdDate
     */
    public void setSysUpdDate(Date sysUpdDate) {
        this.sysUpdDate = sysUpdDate;
    }

    /**
     * @return DY_TYPE
     */
    public Short getDyType() {
        return dyType;
    }

    /**
     * @param dyType
     */
    public void setDyType(Short dyType) {
        this.dyType = dyType;
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
}