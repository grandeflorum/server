package com.grandeflorum.practitioner.domain;

import com.grandeflorum.attachment.domain.FileInfo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

@Table(name = "COMPANY")
public class Company {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "QYMC")
    private String qymc;

    @Column(name = "YYZZ")
    private String yyzz;

    @Column(name = "SHXYDM")
    private String shxydm;

    @Column(name = "QYLX")
    private Short qylx;

    @Column(name = "ZCZJ")
    private BigDecimal zczj;

    @Column(name = "REGIONCODE")
    private String regioncode;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "QYFR")
    private String qyfr;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "YZBM")
    private String yzbm;

    @Column(name = "ZJLX")
    private Short zjlx;

    @Column(name = "ZJH")
    private String zjh;

    @Column(name = "QYTEL")
    private String qytel;

    @Column(name = "CLRQ")
    private Date clrq;

    @Column(name = "YYQX")
    private String yyqx;

    @Column(name = "ZGBM")
    private String zgbm;

    @Column(name = "QYWZ")
    private String qywz;

    @Column(name = "DZYX")
    private String dzyx;

    @Column(name = "DMZH")
    private String dmzh;

    @Column(name = "ZZZSH")
    private String zzzsh;

    @Column(name = "ZZDJ")
    private Short zzdj;

    @Column(name = "PZSJ")
    private Date pzsj;

    @Column(name = "YXQX")
    private String yxqx;

    @Column(name = "PZBM")
    private String pzbm;

    @Column(name = "BZ")
    private Object bz;

    @Column(name = "SYS_DATE")
    private Date sysDate;

    @Column(name = "SYS_UPD_DATE")
    private Date sysUpdDate;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "COMPANY_TYPE")
    private Short companyType;

    @Column(name = "AUDIT_TYPE")
    private Short auditType;

    private List<FileInfo> fileInfoList;

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
     * @return QYMC
     */
    public String getQymc() {
        return qymc;
    }

    /**
     * @param qymc
     */
    public void setQymc(String qymc) {
        this.qymc = qymc == null ? null : qymc.trim();
    }

    /**
     * @return YYZZ
     */
    public String getYyzz() {
        return yyzz;
    }

    /**
     * @param yyzz
     */
    public void setYyzz(String yyzz) {
        this.yyzz = yyzz == null ? null : yyzz.trim();
    }

    /**
     * @return SHXYDM
     */
    public String getShxydm() {
        return shxydm;
    }

    /**
     * @param shxydm
     */
    public void setShxydm(String shxydm) {
        this.shxydm = shxydm == null ? null : shxydm.trim();
    }

    /**
     * @return QYLX
     */
    public Short getQylx() {
        return qylx;
    }

    /**
     * @param qylx
     */
    public void setQylx(Short qylx) {
        this.qylx = qylx;
    }

    /**
     * @return ZCZJ
     */
    public BigDecimal getZczj() {
        return zczj;
    }

    /**
     * @param zczj
     */
    public void setZczj(BigDecimal zczj) {
        this.zczj = zczj;
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
        this.regioncode = regioncode;
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
     * @return QYFR
     */
    public String getQyfr() {
        return qyfr;
    }

    /**
     * @param qyfr
     */
    public void setQyfr(String qyfr) {
        this.qyfr = qyfr == null ? null : qyfr.trim();
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
     * @return YZBM
     */
    public String getYzbm() {
        return yzbm;
    }

    /**
     * @param yzbm
     */
    public void setYzbm(String yzbm) {
        this.yzbm = yzbm == null ? null : yzbm.trim();
    }

    /**
     * @return ZJLX
     */
    public Short getZjlx() {
        return zjlx;
    }

    /**
     * @param zjlx
     */
    public void setZjlx(Short zjlx) {
        this.zjlx = zjlx;
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
     * @return QYTEL
     */
    public String getQytel() {
        return qytel;
    }

    /**
     * @param qytel
     */
    public void setQytel(String qytel) {
        this.qytel = qytel == null ? null : qytel.trim();
    }

    /**
     * @return CLRQ
     */
    public Date getClrq() {
        return clrq;
    }

    /**
     * @param clrq
     */
    public void setClrq(Date clrq) {
        this.clrq = clrq;
    }

    /**
     * @return YYQX
     */
    public String getYyqx() {
        return yyqx;
    }

    /**
     * @param yyqx
     */
    public void setYyqx(String yyqx) {
        this.yyqx = yyqx == null ? null : yyqx.trim();
    }

    /**
     * @return ZGBM
     */
    public String getZgbm() {
        return zgbm;
    }

    /**
     * @param zgbm
     */
    public void setZgbm(String zgbm) {
        this.zgbm = zgbm == null ? null : zgbm.trim();
    }

    /**
     * @return QYWZ
     */
    public String getQywz() {
        return qywz;
    }

    /**
     * @param qywz
     */
    public void setQywz(String qywz) {
        this.qywz = qywz == null ? null : qywz.trim();
    }

    /**
     * @return DZYX
     */
    public String getDzyx() {
        return dzyx;
    }

    /**
     * @param dzyx
     */
    public void setDzyx(String dzyx) {
        this.dzyx = dzyx == null ? null : dzyx.trim();
    }

    /**
     * @return DMZH
     */
    public String getDmzh() {
        return dmzh;
    }

    /**
     * @param dmzh
     */
    public void setDmzh(String dmzh) {
        this.dmzh = dmzh == null ? null : dmzh.trim();
    }

    /**
     * @return ZZZSH
     */
    public String getZzzsh() {
        return zzzsh;
    }

    /**
     * @param zzzsh
     */
    public void setZzzsh(String zzzsh) {
        this.zzzsh = zzzsh == null ? null : zzzsh.trim();
    }

    /**
     * @return ZZDJ
     */
    public Short getZzdj() {
        return zzdj;
    }

    /**
     * @param zzdj
     */
    public void setZzdj(Short zzdj) {
        this.zzdj = zzdj;
    }

    /**
     * @return PZSJ
     */
    public Date getPzsj() {
        return pzsj;
    }

    /**
     * @param pzsj
     */
    public void setPzsj(Date pzsj) {
        this.pzsj = pzsj;
    }

    /**
     * @return YXQX
     */
    public String getYxqx() {
        return yxqx;
    }

    /**
     * @param yxqx
     */
    public void setYxqx(String yxqx) {
        this.yxqx = yxqx == null ? null : yxqx.trim();
    }

    /**
     * @return PZBM
     */
    public String getPzbm() {
        return pzbm;
    }

    /**
     * @param pzbm
     */
    public void setPzbm(String pzbm) {
        this.pzbm = pzbm == null ? null : pzbm.trim();
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
     * @return USER_ID
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }


    public Short getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Short companyType) {
        this.companyType = companyType;
    }

    public Short getAuditType() {
        return auditType;
    }

    public void setAuditType(Short auditType) {
        this.auditType = auditType;
    }

    public List<FileInfo> getFileInfoList() {
        return fileInfoList;
    }

    public void setFileInfoList(List<FileInfo> fileInfoList) {
        this.fileInfoList = fileInfoList;
    }
}