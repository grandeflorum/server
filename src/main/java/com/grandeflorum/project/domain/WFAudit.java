package com.grandeflorum.project.domain;

import com.grandeflorum.attachment.domain.FileInfo;
import com.grandeflorum.common.domain.EntityBase;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "WF_AUDIT")
public class WFAudit extends EntityBase {

    @Id
    @Column(name = "ID")
    private String id;
    /**
     * 审核结果1：通过2：不通过
     */
    @Column(name = "SHJG")
    private Short shjg;
    /**
     * 审核人员（注销人员）
     */
    @Column(name = "SHRY")
    private String shry;
    /**
     * 审核日期（注销日期）
     */
    @Column(name = "SHRQ")
    private Date shrq;
    /**
     * 备注
     */
    @Column(name = "BZ")
    private String bz;

    /**
     * 外键
     */
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
     * 注销理由
     */
    @Column(name = "ZXLY")
    private String zxly;

    private List<FileInfo> fileInfoList;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "USER_TYPE")
    private Integer userType;

    @Column(name = "IS_ACTIVE")
    private Integer isActive;

    private List<WFAudit> otherWf;

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

    public String getZxly() {
        return zxly;
    }

    public void setZxly(String zxly) {
        this.zxly = zxly;
    }

    public List<FileInfo> getFileInfoList() {
        return fileInfoList;
    }

    public void setFileInfoList(List<FileInfo> fileInfoList) {
        this.fileInfoList = fileInfoList;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public List<WFAudit> getOtherWf() {
        return otherWf;
    }

    public void setOtherWf(List<WFAudit> otherWf) {
        this.otherWf = otherWf;
    }

}