package com.grandeflorum.StockHouse.domin;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

//与产权人关系
@Table(name = "RELATIONSHIP")
public class RelationShip {
    /**
     * id
     */
    @Id
    @Column(name = "ID")
    public String id;

    /**
     * 姓名
     */
    @Column(name = "name")
    public String name;
    /**
     * 性别
     */
    @Column(name = "GENDER")
    public Integer gender;
    /**
     * 身份证号
     */
    @Column(name = "SFZH")
    public String sfzh;
    /**
     * 联系电话
     */
    @Column(name = "LXDH")
    public String lxdh;
    /**
     * 与产权人关系
     */
    @Column(name = "CQRGX")
    public Integer cqrgx;

    /**
     * 共有方式
     */
    @Column(name="GYFS")
    public Integer gyfs;

    /**
     * 共有比例
     */
    @Column(name="GYBL")
    public String gybl;

    /**
     * 证件类别
     */
    @Column(name="ZJLB")
    public Integer zjlb;

    /**
     * 项目id
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


    public Integer getGyfs() {
        return gyfs;
    }

    public void setGyfs(Integer gyfs) {
        this.gyfs = gyfs;
    }

    public String getGybl() {
        return gybl;
    }

    public void setGybl(String gybl) {
        this.gybl = gybl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public Integer getCqrgx() {
        return cqrgx;
    }

    public void setCqrgx(Integer cqrgx) {
        this.cqrgx = cqrgx;
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

    public Integer getZjlb() {
        return zjlb;
    }

    public void setZjlb(Integer zjlb) {
        this.zjlb = zjlb;
    }
}
