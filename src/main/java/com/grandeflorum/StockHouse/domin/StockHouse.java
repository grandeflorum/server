package com.grandeflorum.StockHouse.domin;

import com.grandeflorum.buildingTable.domain.H;
import com.sun.star.util.DateTime;
import org.aspectj.asm.internal.Relationship;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

//存量房源
@Table(name = "FG_H")
public class StockHouse extends H {
//    /**
//     * id
//     */
//    @Id
//    @Column(name = "ID")
//    public String id;

    /**
     * 产权人姓名
     */
    @Column(name = "CQRXM")
    public String cqrxm;

    /**
     * 产权证号
     */
    @Column(name = "CQZH")
    public String cqzh;

    /**
     * 房型
     */
    @Column(name = "FX")
    public Integer fx;

    /**
     * 建筑面积
     */
    @Column(name = "JZMJ")
    public Double jzmj;

    /**
     * 逻辑幢id
     */
    @Transient
    public String ljzid;
//
//    /**
//     * 所属地区行政区划代码
//     */
//    @Column(name = "REGIONCODE")
//    public String regionCode;

    /**
     * 项目详细地址
     */
    @Column(name = "ADDRESS")
    public String address;
//
//    /**
//     * 备注
//     */
//    @Column(name = "BZ")
//    public String bz;

    /**
     * 与产权人关系
     */
    public List<RelationShip> relationShips;
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



//    /**
//     * 不动产单元号
//     */
//    @Column(name = "BDCDYH")
//    public String bdcdyh;
//
//    /**
//     * 房屋编码
//     */
//    @Column(name = "FWBM")
//    public String fwbm;
//
//    /**
//     * 要素代码
//     */
//    @Column(name = "YSDM")
//    public String ysdm;
//
//    /**
//     * 自然幢号
//     */
//    @Column(name = "ZRZH")
//    public String zrzh;
//
//    /**
//     * 逻辑幢号
//     */
//    @Column(name = "LJZH")
//    public String ljzh;
//
//    /**
//     * 层号
//     */
//    @Column(name = "CH")
//    public String ch;
//
//    /**
//     * 坐落
//     */
//    @Column(name = "ZL")
//    public String zl;
//
//    /**
//     * 面积单位
//     */
//    @Column(name = "MJDW")
//    public Integer mjdw;
//
//    /**
//     * 实际层数
//     */
//    @Column(name = "SJCS")
//    public String sjcs;
//
//    /**
//     * 户号
//     */
//    @Column(name = "HH")
//    public String hh;
//
//    /**
//     * 室号部位
//     */
//    @Column(name = "SHBW")
//    public String shbw;
//
//    /**
//     * 户型
//     */
//    @Column(name = "HX")
//    public Integer hx;
//
//    /**
//     * 户型结构
//     */
//    @Column(name = "HXJG")
//    public Integer hxjg;
//
//    /**
//     * 房屋用途1
//     */
//    @Column(name = "FWYT1")
//    public Integer fwyt1;
//
//    /**
//     * 房屋用途2
//     */
//    @Column(name = "FWYT2")
//    public Integer fwyt2;
//
//    /**
//     * 房屋用途3
//     */
//    @Column(name = "FWYT3")
//    public Integer fwyt3;
//
//    /**
//     * 预测建筑面积
//     */
//    @Column(name = "YCJZMJ")
//    public Double ycjzmj;
//
//    /**
//     * 预测套内建筑面积
//     */
//    @Column(name = "YCTNJZMJ")
//    public Double yctnjzmj;
//
//    /**
//     * 预测分摊建筑面积
//     */
//    @Column(name = "YCFTJZMJ")
//    public Double ycftjzmj;
//
//    /**
//     * 预测地下部分建筑面积
//     */
//    @Column(name = "YCDXBFJZMJ")
//    public Double ycdxbfjzmj;
//
//    /**
//     * 预测其它建筑面积
//     */
//    @Column(name = "YCQTJZMJ")
//    public Double ycqtjzmj;
//
//    /**
//     * 预测分摊系数
//     */
//    @Column(name = "YCFTXS")
//    public Double ycftxs;
//
//    /**
//     * 实测建筑面积
//     */
//    @Column(name = "SCJZMJ")
//    public Double scjzmj;
//
//    /**
//     * 实测套内建筑面积
//     */
//    @Column(name = "SCTNJZMJ")
//    public Double sctnjzmj;
//
//    /**
//     * 实测分摊建筑面积
//     */
//    @Column(name = "SCFTJZMJ")
//    public Double scftjzmj;
//
//    /**
//     * 实测地下部分建筑面积
//     */
//    @Column(name = "SCDXBFJZMJ")
//    public Double scdxbfjzmj;
//
//    /**
//     * 实测其它建筑面积
//     */
//    @Column(name = "SCQTJZMJ")
//    public Double scqtjzmj;
//
//    /**
//     * 实测分摊系数
//     */
//    @Column(name = "SCFTXS")
//    public Double scftxs;
//
//    /**
//     * 共有土地面积
//     */
//    @Column(name = "GYTDMJ")
//    public Double gytdmj;
//
//    /**
//     * 分摊土地面积
//     */
//    @Column(name = "FTTDMJ")
//    public Double fttdmj;
//
//    /**
//     * 独用土地面积
//     */
//    @Column(name = "DYTDMJ")
//    public Double dytdmj;
//
//    /**
//     * 房屋类型
//     */
//    @Column(name = "FWLX")
//    public Integer fwlx;
//
//    /**
//     * 房屋性质
//     */
//    @Column(name = "FWXZ")
//    public Integer fwxz;
//
//    /**
//     * 房产分户图
//     */
//    @Column(name = "FCFHT")
//    public String fcfht;


//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }

    public String getCqrxm() {
        return cqrxm;
    }

    public void setCqrxm(String cqrxm) {
        this.cqrxm = cqrxm;
    }

    public String getCqzh() {
        return cqzh;
    }

    public void setCqzh(String cqzh) {
        this.cqzh = cqzh;
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

    //
//    public String getRegionCode() {
//        return regionCode;
//    }
//
//    public void setRegionCode(String regionCode) {
//        this.regionCode = regionCode;
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
//
//    public String getBz() {
//        return bz;
//    }
//
//    public void setBz(String bz) {
//        this.bz = bz;
//    }
//
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



    public List<RelationShip> getRelationShips() {
        return relationShips;
    }

    public void setRelationShips(List<RelationShip> relationShips) {
        this.relationShips = relationShips;
    }

//    public String getBdcdyh() {
//        return bdcdyh;
//    }
//
//    public void setBdcdyh(String bdcdyh) {
//        this.bdcdyh = bdcdyh;
//    }
//
//    public String getFwbm() {
//        return fwbm;
//    }
//
//    public void setFwbm(String fwbm) {
//        this.fwbm = fwbm;
//    }
//
//    public String getYsdm() {
//        return ysdm;
//    }
//
//    public void setYsdm(String ysdm) {
//        this.ysdm = ysdm;
//    }
//
//    public String getZrzh() {
//        return zrzh;
//    }
//
//    public void setZrzh(String zrzh) {
//        this.zrzh = zrzh;
//    }
//
//    public String getLjzh() {
//        return ljzh;
//    }
//
//    public void setLjzh(String ljzh) {
//        this.ljzh = ljzh;
//    }
//
//    public String getCh() {
//        return ch;
//    }
//
//    public void setCh(String ch) {
//        this.ch = ch;
//    }
//
//    public String getZl() {
//        return zl;
//    }
//
//    public void setZl(String zl) {
//        this.zl = zl;
//    }
//
//    public Integer getMjdw() {
//        return mjdw;
//    }
//
//    public void setMjdw(Integer mjdw) {
//        this.mjdw = mjdw;
//    }
//
//    public String getSjcs() {
//        return sjcs;
//    }
//
//    public void setSjcs(String sjcs) {
//        this.sjcs = sjcs;
//    }
//
//    public String getHh() {
//        return hh;
//    }
//
//    public void setHh(String hh) {
//        this.hh = hh;
//    }
//
//    public String getShbw() {
//        return shbw;
//    }
//
//    public void setShbw(String shbw) {
//        this.shbw = shbw;
//    }
//
//    public Integer getHx() {
//        return hx;
//    }
//
//    public void setHx(Integer hx) {
//        this.hx = hx;
//    }
//
//    public Integer getHxjg() {
//        return hxjg;
//    }
//
//    public void setHxjg(Integer hxjg) {
//        this.hxjg = hxjg;
//    }
//
//    public Integer getFwyt1() {
//        return fwyt1;
//    }
//
//    public void setFwyt1(Integer fwyt1) {
//        this.fwyt1 = fwyt1;
//    }
//
//    public Integer getFwyt2() {
//        return fwyt2;
//    }
//
//    public void setFwyt2(Integer fwyt2) {
//        this.fwyt2 = fwyt2;
//    }
//
//    public Integer getFwyt3() {
//        return fwyt3;
//    }
//
//    public void setFwyt3(Integer fwyt3) {
//        this.fwyt3 = fwyt3;
//    }
//
//    public Double getYcjzmj() {
//        return ycjzmj;
//    }
//
//    public void setYcjzmj(Double ycjzmj) {
//        this.ycjzmj = ycjzmj;
//    }
//
//    public Double getYctnjzmj() {
//        return yctnjzmj;
//    }
//
//    public void setYctnjzmj(Double yctnjzmj) {
//        this.yctnjzmj = yctnjzmj;
//    }
//
//    public Double getYcftjzmj() {
//        return ycftjzmj;
//    }
//
//    public void setYcftjzmj(Double ycftjzmj) {
//        this.ycftjzmj = ycftjzmj;
//    }
//
//    public Double getYcdxbfjzmj() {
//        return ycdxbfjzmj;
//    }
//
//    public void setYcdxbfjzmj(Double ycdxbfjzmj) {
//        this.ycdxbfjzmj = ycdxbfjzmj;
//    }
//
//    public Double getYcqtjzmj() {
//        return ycqtjzmj;
//    }
//
//    public void setYcqtjzmj(Double ycqtjzmj) {
//        this.ycqtjzmj = ycqtjzmj;
//    }
//
//    public Double getYcftxs() {
//        return ycftxs;
//    }
//
//    public void setYcftxs(Double ycftxs) {
//        this.ycftxs = ycftxs;
//    }
//
//    public Double getScjzmj() {
//        return scjzmj;
//    }
//
//    public void setScjzmj(Double scjzmj) {
//        this.scjzmj = scjzmj;
//    }
//
//    public Double getSctnjzmj() {
//        return sctnjzmj;
//    }
//
//    public void setSctnjzmj(Double sctnjzmj) {
//        this.sctnjzmj = sctnjzmj;
//    }
//
//    public Double getScftjzmj() {
//        return scftjzmj;
//    }
//
//    public void setScftjzmj(Double scftjzmj) {
//        this.scftjzmj = scftjzmj;
//    }
//
//    public Double getScdxbfjzmj() {
//        return scdxbfjzmj;
//    }
//
//    public void setScdxbfjzmj(Double scdxbfjzmj) {
//        this.scdxbfjzmj = scdxbfjzmj;
//    }
//
//    public Double getScqtjzmj() {
//        return scqtjzmj;
//    }
//
//    public void setScqtjzmj(Double scqtjzmj) {
//        this.scqtjzmj = scqtjzmj;
//    }
//
//    public Double getScftxs() {
//        return scftxs;
//    }
//
//    public void setScftxs(Double scftxs) {
//        this.scftxs = scftxs;
//    }
//
//    public Double getGytdmj() {
//        return gytdmj;
//    }
//
//    public void setGytdmj(Double gytdmj) {
//        this.gytdmj = gytdmj;
//    }
//
//    public Double getFttdmj() {
//        return fttdmj;
//    }
//
//    public void setFttdmj(Double fttdmj) {
//        this.fttdmj = fttdmj;
//    }
//
//    public Double getDytdmj() {
//        return dytdmj;
//    }
//
//    public void setDytdmj(Double dytdmj) {
//        this.dytdmj = dytdmj;
//    }
//
//    public Integer getFwlx() {
//        return fwlx;
//    }
//
//    public void setFwlx(Integer fwlx) {
//        this.fwlx = fwlx;
//    }
//
//    public Integer getFwxz() {
//        return fwxz;
//    }
//
//    public void setFwxz(Integer fwxz) {
//        this.fwxz = fwxz;
//    }
//
//    public String getFcfht() {
//        return fcfht;
//    }
//
//    public void setFcfht(String fcfht) {
//        this.fcfht = fcfht;
//    }

    public String getLjzid() {
        return ljzid;
    }

    public void setLjzid(String ljzid) {
        this.ljzid = ljzid;
    }
}
