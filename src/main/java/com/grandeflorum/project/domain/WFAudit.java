package com.grandeflorum.project.domain;

import com.grandeflorum.common.domain.EntityBase;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "WF_AUDIT")
public class WFAudit extends EntityBase{
    @Id
    @Column(name = "ID")
    public String id;

    /**
     * 审核结果
     */
    @Column(name = "SHJG")
    public Integer shjg;

    /**
     * 审核人
     */
    @Column(name = "SHR")
    public String shr;

    /**
     * 审核日期
     */
    @Column(name = "SHRQ")
    public Date shrq;

    /**
     * 备注
     */
    @Column(name = "BZ")
    public String bz;

    /**
     * 关联审核项目外键
     */
    @Column(name = "PROJECTID")
    public String projectId;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getShjg() {
        return shjg;
    }

    public void setShjg(Integer shjg) {
        this.shjg = shjg;
    }

    public String getShr() {
        return shr;
    }

    public void setShr(String shr) {
        this.shr = shr;
    }

    public Date getShrq() {
        return shrq;
    }

    public void setShrq(Date shrq) {
        this.shrq = shrq;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
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
}
