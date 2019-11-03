package com.grandeflorum.project.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "PROJECT")
public class Project {
    /**
     * id
     */
    @Id
    @Column(name = "ID")
    public String id;

    /**
     * 项目名称
     */
    @Column(name = "XMMC")
    public String xmmc;

    /**
     * 开发企业名称
     */
    @Column(name = "KFQYMC")
    public String kfqymc;

    /**
     * 期数
     */
    @Column(name = "QS")
    public Integer qs;

    /**
     * 批次
     */
    @Column(name = "PC")
    public Integer pc;

    /**
     * 项目负责人
     */
    @Column(name = "XMFZR")
    public String xmfzr;

    /**
     * 项目用途
     */
    @Column(name = "XMYT")
    public Integer xmyt;

    /**
     * 所属地区行政区划代码
     */
    @Column(name = "REGIONCODE")
    public Integer regionCode;

    /**
     * 项目详细地址
     */
    @Column(name = "ADDRESS")
    public String address;

    /**
     * 联系电话
     */
    @Column(name = "PHONE")
    public String phone;

    /**
     * 占地面积
     */
    @Column(name = "ZDMJ")
    public Double zdmj;

    /**
     * 土地投资（万）
     */
    @Column(name = "TDTZ")
    public Double tdtz;

    /**
     * 计划总投资（万）
     */
    @Column(name = "ZTZ")
    public Double ztz;

    /**
     * 总投资中自有资金（万）
     */
    @Column(name = "ZTZZYZJ")
    public Double ztzzyzj;

    /**
     * 总投资中融资金额
     */
    @Column(name = "ZTZRZZJ")
    public Double ztzrzzj;

    /**
     * 开工日期
     */
    @Column(name = "KGRQ")
    public Date kgrq;

    /**
     * 竣工日期
     */
    @Column(name = "JGRQ")
    public Date jgrq;

    /**
     * 当前工程进度
     */
    @Column(name = "GCJD")
    public Integer gcjd;

    /**
     * 项目状态
     */
    @Column(name = "XMZT")
    public Integer xmzt;

    /**
     * 拆迁标志
     */
    @Column(name = "CQBZ")
    public Integer cqbz;

    /**
     * 用地取得方式
     */
    @Column(name = "YDQDFS")
    public Integer ydqdfs;

    /**
     * 地段类别
     */
    @Column(name = "DDLB")
    public Integer ddlb;

    /**
     * 土地使用证号
     */
    @Column(name = "TDSYZH")
    public String tdsyzh;

    /**
     * 土地使用证批准时间
     */
    @Column(name = "PZSJ")
    public Date pzsj;

    /**
     * 总建筑面积
     */
    @Column(name = "ZJZMJ")
    public Double zjzmj;

    /**
     * 住宅面积
     */
    @Column(name = "ZZMJ")
    public Double zzmj;

    /**
     * 非住宅面积
     */
    @Column(name = "FZZMJ")
    public Double fzzmj;

    /**
     * 住宅套数
     */
    @Column(name = "ZZTS")
    public Integer zzts;

    /**
     * 非住宅套数
     */
    @Column(name = "FZZTS")
    public Integer fzzts;

    /**
     * 企业公司外键
     */
    @Column(name = "COMPANY_ID")
    public String companyId;

    /**
     * 重要提示
     */
    @Column(name = "ZYTS")
    public String zyts;


    /**
     * 备注
     */
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

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public Integer getQs() {
        return qs;
    }

    public void setQs(Integer qs) {
        this.qs = qs;
    }

    public Integer getPc() {
        return pc;
    }

    public void setPc(Integer pc) {
        this.pc = pc;
    }

    public String getXmfzr() {
        return xmfzr;
    }

    public void setXmfzr(String xmfzr) {
        this.xmfzr = xmfzr;
    }

    public Integer getXmyt() {
        return xmyt;
    }

    public void setXmyt(Integer xmyt) {
        this.xmyt = xmyt;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getZdmj() {
        return zdmj;
    }

    public void setZdmj(Double zdmj) {
        this.zdmj = zdmj;
    }

    public Double getTdtz() {
        return tdtz;
    }

    public void setTdtz(Double tdtz) {
        this.tdtz = tdtz;
    }

    public Double getZtz() {
        return ztz;
    }

    public void setZtz(Double ztz) {
        this.ztz = ztz;
    }

    public Double getZtzzyzj() {
        return ztzzyzj;
    }

    public void setZtzzyzj(Double ztzzyzj) {
        this.ztzzyzj = ztzzyzj;
    }

    public Double getZtzrzzj() {
        return ztzrzzj;
    }

    public void setZtzrzzj(Double ztzrzzj) {
        this.ztzrzzj = ztzrzzj;
    }

    public Date getKgrq() {
        return kgrq;
    }

    public void setKgrq(Date kgrq) {
        this.kgrq = kgrq;
    }

    public Date getJgrq() {
        return jgrq;
    }

    public void setJgrq(Date jgrq) {
        this.jgrq = jgrq;
    }

    public Integer getGcjd() {
        return gcjd;
    }

    public void setGcjd(Integer gcjd) {
        this.gcjd = gcjd;
    }

    public Integer getXmzt() {
        return xmzt;
    }

    public void setXmzt(Integer xmzt) {
        this.xmzt = xmzt;
    }

    public Integer getCqbz() {
        return cqbz;
    }

    public void setCqbz(Integer cqbz) {
        this.cqbz = cqbz;
    }

    public Integer getYdqdfs() {
        return ydqdfs;
    }

    public void setYdqdfs(Integer ydqdfs) {
        this.ydqdfs = ydqdfs;
    }

    public Integer getDdlb() {
        return ddlb;
    }

    public void setDdlb(Integer ddlb) {
        this.ddlb = ddlb;
    }

    public String getTdsyzh() {
        return tdsyzh;
    }

    public void setTdsyzh(String tdsyzh) {
        this.tdsyzh = tdsyzh;
    }

    public Date getPzsj() {
        return pzsj;
    }

    public void setPzsj(Date pzsj) {
        this.pzsj = pzsj;
    }

    public Double getZjzmj() {
        return zjzmj;
    }

    public void setZjzmj(Double zjzmj) {
        this.zjzmj = zjzmj;
    }

    public Double getZzmj() {
        return zzmj;
    }

    public void setZzmj(Double zzmj) {
        this.zzmj = zzmj;
    }

    public Double getFzzmj() {
        return fzzmj;
    }

    public void setFzzmj(Double fzzmj) {
        this.fzzmj = fzzmj;
    }

    public Integer getZzts() {
        return zzts;
    }

    public void setZzts(Integer zzts) {
        this.zzts = zzts;
    }

    public Integer getFzzts() {
        return fzzts;
    }

    public void setFzzts(Integer fzzts) {
        this.fzzts = fzzts;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getZyts() {
        return zyts;
    }

    public void setZyts(String zyts) {
        this.zyts = zyts;
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

    public String getKfqymc() {
        return kfqymc;
    }

    public void setKfqymc(String kfqymc) {
        this.kfqymc = kfqymc;
    }

    public Integer getAuditType() {
        return auditType;
    }

    public void setAuditType(Integer auditType) {
        this.auditType = auditType;
    }
}
