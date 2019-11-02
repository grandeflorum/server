package com.grandeflorum.practitioner.domain;

import java.util.Date;
import javax.persistence.*;

public class Employee {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "GENDER")
    private Short gender;

    @Column(name = "MAJOR")
    private String major;

    @Column(name = "EDUCATION")
    private Short education;

    @Column(name = "ZJLB")
    private Short zjlb;

    @Column(name = "ZJH")
    private String zjh;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "REGIONCODE")
    private String regioncode;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "FWJGDM")
    private String fwjgdm;

    @Column(name = "POSTION")
    private String postion;

    @Column(name = "ZSMC")
    private String zsmc;

    @Column(name = "ZSBH")
    private String zsbh;

    @Column(name = "CYNX")
    private Short cynx;

    @Column(name = "FWNX")
    private Short fwnx;

    @Column(name = "BZ")
    private Object bz;

    @Column(name = "SYS_DATE")
    private Date sysDate;

    @Column(name = "SYS_UPD_DATE")
    private Date sysUpdDate;

    @Column(name = "COMPANY_ID")
    private String companyId;

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
     * @return NAME
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return GENDER
     */
    public Short getGender() {
        return gender;
    }

    /**
     * @param gender
     */
    public void setGender(Short gender) {
        this.gender = gender;
    }

    /**
     * @return MAJOR
     */
    public String getMajor() {
        return major;
    }

    /**
     * @param major
     */
    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    /**
     * @return EDUCATION
     */
    public Short getEducation() {
        return education;
    }

    /**
     * @param education
     */
    public void setEducation(Short education) {
        this.education = education;
    }

    /**
     * @return ZJLB
     */
    public Short getZjlb() {
        return zjlb;
    }

    /**
     * @param zjlb
     */
    public void setZjlb(Short zjlb) {
        this.zjlb = zjlb;
    }

    /**
     * @return ZJH
     */
    public String getZjh() {
        return zjh;
    }

    /**
     * @param zjh
     */
    public void setZjh(String zjh) {
        this.zjh = zjh == null ? null : zjh.trim();
    }

    /**
     * @return PHONE
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * @return EMAIL
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * @return REGIONCODE
     */
    public String getRegioncode() {
        return regioncode;
    }

    /**
     * @param regioncode
     */
    public void setRegioncode(String regioncode) {
        this.regioncode = regioncode == null ? null : regioncode.trim();
    }

    /**
     * @return ADDRESS
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * @return FWJGDM
     */
    public String getFwjgdm() {
        return fwjgdm;
    }

    /**
     * @param fwjgdm
     */
    public void setFwjgdm(String fwjgdm) {
        this.fwjgdm = fwjgdm == null ? null : fwjgdm.trim();
    }

    /**
     * @return POSTION
     */
    public String getPostion() {
        return postion;
    }

    /**
     * @param postion
     */
    public void setPostion(String postion) {
        this.postion = postion == null ? null : postion.trim();
    }

    /**
     * @return ZSMC
     */
    public String getZsmc() {
        return zsmc;
    }

    /**
     * @param zsmc
     */
    public void setZsmc(String zsmc) {
        this.zsmc = zsmc == null ? null : zsmc.trim();
    }

    /**
     * @return ZSBH
     */
    public String getZsbh() {
        return zsbh;
    }

    /**
     * @param zsbh
     */
    public void setZsbh(String zsbh) {
        this.zsbh = zsbh == null ? null : zsbh.trim();
    }

    /**
     * @return CYNX
     */
    public Short getCynx() {
        return cynx;
    }

    /**
     * @param cynx
     */
    public void setCynx(Short cynx) {
        this.cynx = cynx;
    }

    /**
     * @return FWNX
     */
    public Short getFwnx() {
        return fwnx;
    }

    /**
     * @param fwnx
     */
    public void setFwnx(Short fwnx) {
        this.fwnx = fwnx;
    }

    /**
     * @return BZ
     */
    public Object getBz() {
        return bz;
    }

    /**
     * @param bz
     */
    public void setBz(Object bz) {
        this.bz = bz;
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
     * @return COMPANY_ID
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * @param companyId
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }
}