package com.grandeflorum.StockHouse.domin;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "STOCKHOUSE")
public class StockHouse {
    /**
     * id
     */
    @Id
    @Column(name = "ID")
    public String id;

    /**
     * 产权人姓名
     */
    @Id
    @Column(name = "CQRXM")
    public String cqrxm;

    /**
     * 房型
     */
    @Id
    @Column(name = "FX")
    public Integer fx;

    /**
     * 建筑面积
     */
    @Id
    @Column(name = "JZMJ")
    public Double jzmj;

    /**
     * 所属地区行政区划代码
     */
    @Id
    @Column(name = "REGIONCODE")
    public Integer regionCode;

    /**
     * 项目详细地址
     */
    @Id
    @Column(name = "ADDRESS")
    public String address;

    /**
     * 备注
     */
    @Id
    @Column(name = "BZ")
    public String bz;

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
     * 审核状态
     */
    @Column(name = "AUDIT_TYPE")
    public Integer auditType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCqrxm() {
        return cqrxm;
    }

    public void setCqrxm(String cqrxm) {
        this.cqrxm = cqrxm;
    }

    public Integer getFx() {
        return fx;
    }

    public void setFx(Integer fx) {
        this.fx = fx;
    }

    public Double getJzmj() {
        return jzmj;
    }

    public void setJzmj(Double jzmj) {
        this.jzmj = jzmj;
    }

    public Integer getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(Integer regionCode) {
        this.regionCode = regionCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
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

    public Integer getAuditType() {
        return auditType;
    }

    public void setAuditType(Integer auditType) {
        this.auditType = auditType;
    }
}
