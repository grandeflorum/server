package com.grandeflorum.project.domain;

import com.grandeflorum.common.domain.EntityBase;

import java.util.Date;
import javax.persistence.*;

@Table(name = "WF_AUDIT")
public class WFAudit extends EntityBase {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "SHJG")
    private Short shjg;

    @Column(name = "SHRY")
    private String shry;

    @Column(name = "SHRQ")
    private Date shrq;

    @Column(name = "BZ")
    private String bz;

    @Column(name = "PROJECTID")
    private String projectid;

    /**
     * 当前状态
     */
    @Column(name = "CURRENTSTATUS")
    public Integer currentStatus;

    @Column(name = "SYS_DATE")
    private Date sysDate;

    @Column(name = "SYS_UPD_DATE")
    private Date sysUpdDate;

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
     * @return SHJG
     */
    public Short getShjg() {
        return shjg;
    }

    /**
     * @param shjg
     */
    public void setShjg(Short shjg) {
        this.shjg = shjg;
    }

    /**
     * @return SHR
     */
    public String getShry() {
        return shry;
    }

    /**
     * @param shry
     */
    public void setShry(String shry) {
        this.shry = shry == null ? null : shry.trim();
    }

    /**
     * @return SHRQ
     */
    public Date getShrq() {
        return shrq;
    }

    /**
     * @param shrq
     */
    public void setShrq(Date shrq) {
        this.shrq = shrq;
    }

    /**
     * @return BZ
     */
    public String getBz() {
        return bz;
    }

    /**
     * @param bz
     */
    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    /**
     * @return PROJECTID
     */
    public String getProjectid() {
        return projectid;
    }

    /**
     * @param projectid
     */
    public void setProjectid(String projectid) {
        this.projectid = projectid == null ? null : projectid.trim();
    }

    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
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
}