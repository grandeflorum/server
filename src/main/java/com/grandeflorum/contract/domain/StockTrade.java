package com.grandeflorum.contract.domain;

import com.grandeflorum.StockHouse.domin.RelationShip;
import com.grandeflorum.project.domain.WFAudit;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Table(name = "HOUSE_STOCK_TRADE")
public class StockTrade {
    @Id
    @Column(name = "ID")
    public String id;
    /**
     *合同编号
     */
    @Column(name = "HTBH")
    public String htbh;
    /**
     *甲方
     */
    @Column(name = "JF")
    public String jf;
    /**
     *甲方联系地址
     */
    @Column(name = "JFLXDZ")
    public String jflxdz;
    /**
     *甲方证件类型
     */
    @Column(name = "JFZJLX")
    public Integer jfzjlx;
    /**
     *甲方证件号码
     */
    @Column(name = "JFZJHM")
    public String jfzjhm;
    /**
     *甲方联系电话
     */
    @Column(name = "JFLXDH")
    public String jflxdh;
    /**
     *乙方
     */
    @Column(name = "YF")
    public String yf;
    /**
     *乙方联系地址
     */
    @Column(name = "YFLXDZ")
    public String yflxdz;
    /**
     *乙方证件类型
     */
    @Column(name = "YFZJLX")
    public Integer yfzjlx;
    /**
     *乙方证件号码
     */
    @Column(name = "YFZJHM")
    public String yfzjhm;
    /**
     *乙方联系电话
     */
    @Column(name = "YFLXDH")
    public String yflxdh;
    /**
     *单价
     */
    @Column(name = "DJ")
    public Double dj;
    /**
     *总价
     */
    @Column(name = "ZJ")
    public Double zj;
    /**
     *入网时间
     */
    @Column(name = "RWSJ")
    public Date rwsj;
    /**
     *关联户id
     */
    @Column(name = "HOUSEID")
    public String houseId;
    /**
     *合同模板id
     */
    @Column(name = "CONTRACTID")
    public String contractId;
    /**
     *合同备案号
     */
    @Column(name = "HTBAH")
    public String htbah;

    /**
     * 当前状态
     */
    @Column(name = "CURRENTSTATUS")
    public Integer currentStatus;

    /**
     * 是否通过
     */
    @Column(name = "ISPASS")
    public Integer isPass;

    /**
     * 创建时间
     */
    @Column(name = "SYS_DATE")
    public Date sysDate;

    /**
     * 最后修改时间
     */
    @Column(name = "SYS_UPD_DATE")
    public Date sysUpdDate;

    /**
     * 流程信息
     */
    public List<WFAudit> wfAuditList;

    /**
     * 多个购买人
     */
    public List<RelationShip> relationShips;

    @Transient
    private String ljzid;


    /**
     * 项目名称
     */
    @Transient
    private String xmmc;
    /**
     * 建筑物名称
     */
    @Transient
    private String jzwmc;
    /**
     * 幢号
     */
    @Transient
    private String zh;

    /**
     * 单元号
     */
    @Transient
    private String dyh;
    /**
     * 层号
     */
    @Transient
    private String ch;
    /**
     * 房号
     */
    @Transient
    private String fh;

    /**
     * 是否注销
     */
    @Column(name = "ISCANCEL")
    public Integer isCancel;

    /**
     * 交易方式
     */
    @Column(name = "JYFS")
    public Integer jyfs;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Integer getIsPass() {
        return isPass;
    }

    public void setIsPass(Integer isPass) {
        this.isPass = isPass;
    }

    public Date getSysDate() {
        return sysDate;
    }

    public void setSysDate(Date sysDate) {
        this.sysDate = sysDate;
    }

    public Date getSysUpdDate() {
        return sysUpdDate;
    }

    public void setSysUpdDate(Date sysUpdDate) {
        this.sysUpdDate = sysUpdDate;
    }

    public String getHtbh() {
        return htbh;
    }

    public void setHtbh(String htbh) {
        this.htbh = htbh;
    }

    public String getJf() {
        return jf;
    }

    public void setJf(String jf) {
        this.jf = jf;
    }

    public String getJflxdz() {
        return jflxdz;
    }

    public void setJflxdz(String jflxdz) {
        this.jflxdz = jflxdz;
    }

    public Integer getJfzjlx() {
        return jfzjlx;
    }

    public void setJfzjlx(Integer jfzjlx) {
        this.jfzjlx = jfzjlx;
    }

    public String getJfzjhm() {
        return jfzjhm;
    }

    public void setJfzjhm(String jfzjhm) {
        this.jfzjhm = jfzjhm;
    }

    public String getJflxdh() {
        return jflxdh;
    }

    public void setJflxdh(String jflxdh) {
        this.jflxdh = jflxdh;
    }

    public String getYf() {
        return yf;
    }

    public void setYf(String yf) {
        this.yf = yf;
    }

    public String getYflxdz() {
        return yflxdz;
    }

    public void setYflxdz(String yflxdz) {
        this.yflxdz = yflxdz;
    }

    public Integer getYfzjlx() {
        return yfzjlx;
    }

    public void setYfzjlx(Integer yfzjlx) {
        this.yfzjlx = yfzjlx;
    }

    public String getYfzjhm() {
        return yfzjhm;
    }

    public void setYfzjhm(String yfzjhm) {
        this.yfzjhm = yfzjhm;
    }

    public String getYflxdh() {
        return yflxdh;
    }

    public void setYflxdh(String yflxdh) {
        this.yflxdh = yflxdh;
    }

    public Double getDj() {
        return dj;
    }

    public void setDj(Double dj) {
        this.dj = dj;
    }

    public Double getZj() {
        return zj;
    }

    public void setZj(Double zj) {
        this.zj = zj;
    }

    public Date getRwsj() {
        return rwsj;
    }

    public void setRwsj(Date rwsj) {
        this.rwsj = rwsj;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getHtbah() {
        return htbah;
    }

    public void setHtbah(String htbah) {
        this.htbah = htbah;
    }

    public List<WFAudit> getWfAuditList() {
        return wfAuditList;
    }

    public void setWfAuditList(List<WFAudit> wfAuditList) {
        this.wfAuditList = wfAuditList;
    }

    public String getLjzid() {
        return ljzid;
    }

    public void setLjzid(String ljzid) {
        this.ljzid = ljzid;
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

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public String getDyh() {
        return dyh;
    }

    public void setDyh(String dyh) {
        this.dyh = dyh;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public String getFh() {
        return fh;
    }

    public void setFh(String fh) {
        this.fh = fh;
    }

    public Integer getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(Integer isCancel) {
        this.isCancel = isCancel;
    }

    public Integer getJyfs() {
        return jyfs;
    }

    public void setJyfs(Integer jyfs) {
        this.jyfs = jyfs;
    }

    public List<RelationShip> getRelationShips() {
        return relationShips;
    }

    public void setRelationShips(List<RelationShip> relationShips) {
        this.relationShips = relationShips;
    }
}
