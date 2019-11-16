package com.grandeflorum.contract.domain;

import com.grandeflorum.project.domain.WFAudit;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Table(name = "HOUSE_NEW_TRADE")
public class HouseTrade {
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

    public List<WFAudit>  wfAuditList;

    @Transient
    private String ljzid;

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
}
