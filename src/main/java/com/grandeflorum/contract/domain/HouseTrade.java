package com.grandeflorum.contract.domain;

import com.grandeflorum.StockHouse.domin.RelationShip;
import com.grandeflorum.project.domain.WFAudit;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Table(name = "HOUSE_NEW_TRADE")
public class HouseTrade implements Cloneable {
    @Id
    @Column(name = "ID")
    public String id;
    /**
     * 单价
     */
    @Column(name = "DJ")
    public Double dj;
    /**
     * 总价
     */
    @Column(name = "ZJ")
    public Double zj;
    /**
     * 入网时间
     */
    @Column(name = "RWSJ")
    public Date rwsj;
    /**
     * 合同备案号
     */
    @Column(name = "HTBAH")
    public String htbah;
    /**
     * 备案时间
     */
    @Column(name = "BASJ")
    public Date basj;
    /**
     * 买受人
     */
    @Column(name = "BUYER")
    public String buyer;
    /**
     * 身份证号
     */
    @Column(name = "SFZH")
    public String sfzh;
    /**
     * 联系地址
     */
    @Column(name = "LXDZ")
    public String lxdz;
    /**
     * 预售许可证
     */
    @Column(name = "YSXKZ")
    public String ysxkz;

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
     * 关联户id
     */
    @Column(name = "HOUSEID")
    public String houseId;
    /**
     * 合同模板id
     */
    @Column(name = "CONTRACTID")
    public String contractId;

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
     * 是否注销
     */
    @Column(name = "ISCANCEL")
    public Integer isCancel;

    /**
     * 交易方式
     */
    @Column(name = "JYFS")
    public Integer jyfs;

    /**
     * 登记人
     */
    @Column(name = "DJR")
    public String djr;

    /**
     * 1 现售 2预售
     */
    @Column(name = "HOUSE_TYPE")
    public Integer houseType;

    /**
     * 共有情况
     */
    @Column(name = "GYQK")
    public Integer gyqk;

    /**
     * 共有比例
     */
    @Column(name = "GYBL")
    public String gybl;

    /**
     * 合同户型
     */
    @Column(name = "HTHX")
    public Integer hthx;



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


    @Transient
    private String bg;

    @Transient
    private String bgly;

    /**
     * 坐落
     */
    @Transient
    private String zl;



    /**
     * 预售
     */
    public AdvanceSalesTemplate advanceSalesTemplate;

    /**
     * 现售
     */
    public CashSalesTemplate cashSalesTemplate;


    public CashSalesTemplate getCashSalesTemplate() {
        return cashSalesTemplate;
    }

    public void setCashSalesTemplate(CashSalesTemplate cashSalesTemplate) {
        this.cashSalesTemplate = cashSalesTemplate;
    }

    public AdvanceSalesTemplate getAdvanceSalesTemplate() {
        return advanceSalesTemplate;
    }

    public void setAdvanceSalesTemplate(AdvanceSalesTemplate advanceSalesTemplate) {
        this.advanceSalesTemplate = advanceSalesTemplate;
    }

    public Integer getHthx() {
        return hthx;
    }

    public void setHthx(Integer hthx) {
        this.hthx = hthx;
    }

    public String getDjr() {
        return djr;
    }

    public void setDjr(String djr) {
        this.djr = djr;
    }

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

    public String getHtbah() {
        return htbah;
    }

    public void setHtbah(String htbah) {
        this.htbah = htbah;
    }

    public Date getBasj() {
        return basj;
    }

    public void setBasj(Date basj) {
        this.basj = basj;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getLxdz() {
        return lxdz;
    }

    public void setLxdz(String lxdz) {
        this.lxdz = lxdz;
    }

    public String getYsxkz() {
        return ysxkz;
    }

    public void setYsxkz(String ysxkz) {
        this.ysxkz = ysxkz;
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

    public Integer getHouseType() {
        return houseType;
    }

    public void setHouseType(Integer houseType) {
        this.houseType = houseType;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public String getBgly() {
        return bgly;
    }

    public void setBgly(String bgly) {
        this.bgly = bgly;
    }

    public String getZl() {
        return zl;
    }

    public void setZl(String zl) {
        this.zl = zl;
    }

    public Integer getGyqk() {
        return gyqk;
    }

    public void setGyqk(Integer gyqk) {
        this.gyqk = gyqk;
    }

    public String getGybl() {
        return gybl;
    }

    public void setGybl(String gybl) {
        this.gybl = gybl;
    }

    @Override
    public Object clone() {

        Object object = null;
        try {
            object = super.clone();
        } catch (Exception e) {

        }

        return object;
    }
}
