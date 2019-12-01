package com.grandeflorum.practitioner.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ASSOCIATED_COMPANY")
public class AssociatedCompany {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "COMPANYID")
    private String companyid;

    @Column(name = "ASSOCIATEDID")
    private String associatedid;

    @Column(name = "MODULENAME")
    private String modulename;

    @Column(name = "SYS_DATE")
    private Date sysDate;

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
     * @return COMPANYID
     */
    public String getCompanyid() {
        return companyid;
    }

    /**
     * @param companyid
     */
    public void setCompanyid(String companyid) {
        this.companyid = companyid == null ? null : companyid.trim();
    }

    /**
     * @return ASSOCIATEDID
     */
    public String getAssociatedid() {
        return associatedid;
    }

    /**
     * @param associatedid
     */
    public void setAssociatedid(String associatedid) {
        this.associatedid = associatedid == null ? null : associatedid.trim();
    }

    /**
     * @return MODULENAME
     */
    public String getModulename() {
        return modulename;
    }

    /**
     * @param modulename
     */
    public void setModulename(String modulename) {
        this.modulename = modulename == null ? null : modulename.trim();
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
}