package com.grandeflorum.buildingTable.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Table(name = "FG_ZRZ")
public class ZRZ {

    /**
     * id
     */
    @Id
    @Column(name = "ID")
    private String id;

    /**
     * 不动产单元号
     */
    @Column(name ="BDCDYH")
    private String bdcdyh;

    /**
     * 自然幢号
     */
    @Column(name ="ZRZH")
    private String zrzh;

    /**
     * 要素代码
     */
    @Column(name = "YSDM")
    private String ysdm;

    @Column(name = "BSM")
    private Long bsm;

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

    @Column(name = "JGRQ")
    private Double jgrq;

    @Column(name = "JZWGD")
    private Double jzwgd;

    @Column(name = "ZZDMJ")
    private Double zzdmj;

    @Column(name = "ZYDMJ")
    private Double zydmj;

    @Column(name = "YCJZMJ")
    private Double ycjzmj;

    @Column(name = "SCJZMJ")
    private Double scjzmj;

    @Column(name = "ZCS")
    private Short zcs;

    @Column(name = "DSCS")
    private Short dscs;

    @Column(name = "DXCS")
    private Short dxcs;

    @Column(name = "GHYT")
    private String ghyt;

    @Column(name = "FWJG")
    private String fwjg;

    @Column(name = "ZTS")
    private Integer zts;

    @Column(name = "JZWJBYT")
    private String jzwjbyt;

    @Column(name = "DAH")
    private String dah;

    @Column(name = "BZ")
    private String bz;

    @Column(name = "ZDDM")
    private String zddm;

    /**
     * 项目id
     */
    @Column(name = "XMID")
    private String xmid;

    /**
     * 地下深度
     */
    @Column(name = "DXSD")
    private Double dxsd;

    @Column(name = "ZT")
    private String zt;

    @Column(name = "YZRZH")
    private String yzrzh;

    @Column(name = "YTMC")
    private String ytmc;

    @Column(name = "ZRZID")
    private Double zrzid;

    @Column(name = "YBDCDYH")
    private String ybdcdyh;

    /**
     * 区县代码
     */
    @Column(name = "QXDM")
    private String qxdm;

    /**
     * 更新时间
     */
    @Column(name = "GXSJ")
    private Date gxsj;

    /**
     * 数据来源1:备案系统数据，2：登记系统数据
     */
    @Transient
    private Integer datasource;

    /**
     * 审核状态(只有新建的数据有审核状态，登记系统同步过来的数据没有)
     */
    @Column(name = "AUDIT_TYPE")
    public Integer auditType;


    /**
     * 逻辑幢集合
     */
    private List<LJZ> ljzList;

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

    public String getBdcdyh() {
        return bdcdyh;
    }

    public void setBdcdyh(String bdcdyh) {
        this.bdcdyh = bdcdyh;
    }

    public String getYsdm() {
        return ysdm;
    }

    public void setYsdm(String ysdm) {
        this.ysdm = ysdm;
    }

    public Long getBsm() {
        return bsm;
    }

    public void setBsm(Long bsm) {
        this.bsm = bsm;
    }

    public Double getJgrq() {
        return jgrq;
    }

    public void setJgrq(Double jgrq) {
        this.jgrq = jgrq;
    }

    public Double getJzwgd() {
        return jzwgd;
    }

    public void setJzwgd(Double jzwgd) {
        this.jzwgd = jzwgd;
    }

    public Double getZzdmj() {
        return zzdmj;
    }

    public void setZzdmj(Double zzdmj) {
        this.zzdmj = zzdmj;
    }

    public Double getZydmj() {
        return zydmj;
    }

    public void setZydmj(Double zydmj) {
        this.zydmj = zydmj;
    }

    public Double getYcjzmj() {
        return ycjzmj;
    }

    public void setYcjzmj(Double ycjzmj) {
        this.ycjzmj = ycjzmj;
    }

    public Double getScjzmj() {
        return scjzmj;
    }

    public void setScjzmj(Double scjzmj) {
        this.scjzmj = scjzmj;
    }

    public Short getZcs() {
        return zcs;
    }

    public void setZcs(Short zcs) {
        this.zcs = zcs;
    }

    public Short getDscs() {
        return dscs;
    }

    public void setDscs(Short dscs) {
        this.dscs = dscs;
    }

    public Short getDxcs() {
        return dxcs;
    }

    public void setDxcs(Short dxcs) {
        this.dxcs = dxcs;
    }

    public String getGhyt() {
        return ghyt;
    }

    public void setGhyt(String ghyt) {
        this.ghyt = ghyt;
    }

    public String getFwjg() {
        return fwjg;
    }

    public void setFwjg(String fwjg) {
        this.fwjg = fwjg;
    }

    public Integer getZts() {
        return zts;
    }

    public void setZts(Integer zts) {
        this.zts = zts;
    }

    public String getJzwjbyt() {
        return jzwjbyt;
    }

    public void setJzwjbyt(String jzwjbyt) {
        this.jzwjbyt = jzwjbyt;
    }

    public String getDah() {
        return dah;
    }

    public void setDah(String dah) {
        this.dah = dah;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getZddm() {
        return zddm;
    }

    public void setZddm(String zddm) {
        this.zddm = zddm;
    }

    public String getXmid() {
        return xmid;
    }

    public void setXmid(String xmid) {
        this.xmid = xmid;
    }

    public Double getDxsd() {
        return dxsd;
    }

    public void setDxsd(Double dxsd) {
        this.dxsd = dxsd;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public String getYzrzh() {
        return yzrzh;
    }

    public void setYzrzh(String yzrzh) {
        this.yzrzh = yzrzh;
    }

    public String getYtmc() {
        return ytmc;
    }

    public void setYtmc(String ytmc) {
        this.ytmc = ytmc;
    }

    public Double getZrzid() {
        return zrzid;
    }

    public void setZrzid(Double zrzid) {
        this.zrzid = zrzid;
    }

    public String getYbdcdyh() {
        return ybdcdyh;
    }

    public void setYbdcdyh(String ybdcdyh) {
        this.ybdcdyh = ybdcdyh;
    }

    public String getQxdm() {
        return qxdm;
    }

    public void setQxdm(String qxdm) {
        this.qxdm = qxdm;
    }

    public Date getGxsj() {
        return gxsj;
    }

    public void setGxsj(Date gxsj) {
        this.gxsj = gxsj;
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
