package com.grandeflorum.buildingTable.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Table(name = "FG_LJZ")
public class LJZ {
    @Id
    @Column(name="ID")
    private String id;

    /**
     * 逻辑幢号
     */
    @Column(name="LJZH")
    private String ljzh;

    /**
     * 自然幢号
     */
    @Column(name="ZRZH")
    private String zrzh;

    /**
     * 要素代码
     */
    @Column(name = "YSDM")
    private String ysdm;

    /**
     * 门牌号
     */
    @Column(name="MPH")
    private String mph;

    /**
     * 预测建筑面积
     */
    @Column(name = "YCJZMJ")
    private Double ycjzmj;

    /**
     * 预测地下面积
     */
    @Column(name = "YCDXMJ")
    private Double ycdxmj;

    /**
     * 预测其它面积
     */
    @Column(name = "YCQTMJ")
    private Double ycqtmj;
    /**
     * 实测建筑面积
     */
    @Column(name="SCJZMJ")
    private Double scjzmj;
    /**
     * 实测地下面积
     */
    @Column(name = "SCDXMJ")
    private Double scdxmj;
    /**
     * 实测其它面积
     */
    @Column(name = "SCQTMJ")
    private Double scqtmj;

    /**
     * 竣工日期
     */
    @Column(name = "JGRQ")
    private Long jgrq;

    /**
     * 房屋结构1
     */
    @Column(name = "FWJG1")
    private String fwjg1;

    /**
     * 房屋结构1
     */
    @Column(name = "FWJG2")
    private String fwjg2;

    /**
     * 房屋结构1
     */
    @Column(name = "FWJG3")
    private String fwjg3;

    /**
     * 建筑物状态
     */
    @Column(name = "JZWZT")
    private String jzwzt;
    /**
     * 房屋用途
     */
    @Column(name="FWYT1")
    private String fwyt1;
    /**
     * 房屋用途2
     */
    @Column(name = "FWYT2")
    private String fwyt2;
    /**
     * 房屋用途3
     */
    @Column(name = "FWYT3")
    private String fwyt3;
    /**
     * 总层数
     */
    @Column(name ="ZCS")
    private Integer zcs;
    /**
     * 地上层数
     */
    @Column(name = "DSCS")
    private Short dscs;
    /**
     * 地下层数
     */
    @Column(name = "DXCS")
    private Short dxcs;
    /**
     * 备注
     */
    @Column(name = "BZ")
    private String bz;
    /**
     * 坐落
     */
    @Column(name = "ZL")
    private String zl;

    @Column(name = "ZT")
    private String zt;

    @Column(name = "ZID")
    private Long zid;

    @Column(name = "TLJZMJ")
    private Double tljzmj;

    @Column(name = "FTJZMJ")
    private Double ftjzmj;

    @Column(name = "DH")
    private String dh;

    @Column(name = "ZBM")
    private String zbm;

    @Column(name = "CHZT")
    private String chzt;

    @Column(name = "JLX")
    private String jlx;

    @Column(name = "XQMC")
    private String xqmc;

    @Column(name = "DYZS")
    private Long dyzs;

    @Column(name = "ZZMJ")
    private Double zzmj;

    @Column(name = "BGMJ")
    private Double bgmj;

    @Column(name = "SFMJ")
    private Double sfmj;

    @Column(name = "ZZTS")
    private Long zzts;

    @Column(name = "BGTS")
    private Long bgts;

    @Column(name = "SFTS")
    private Long sfts;

    @Column(name = "QTTS")
    private Long qtts;

    @Column(name = "ZLY")
    private String zly;

    @Column(name = "YZBM")
    private String yzbm;

    @Column(name = "DZZH")
    private String dzzh;

    @Column(name = "DZBDCDYH")
    private String dzbdcdyh;

    @Column(name = "YLJZH")
    private String yljzh;

    @Column(name = "YTMC")
    private String ytmc;

    @Column(name = "LJZID")
    private Double ljzid;

    @Column(name = "GHYT")
    private String ghyt;

    @Column(name = "ZZDMJ")
    private Double zzdmj;

    @Column(name = "QXDM")
    private String qxdm;
    /**
     * 单元对象
     */
    private List<DY> dyList;

    /**
     * 层对象
     */
    private List<C> cList;

    private LJZStatistical ljzStatistical;

    public LJZStatistical getLjzStatistical() {
        return ljzStatistical;
    }

    public void setLjzStatistical(LJZStatistical ljzStatistical) {
        this.ljzStatistical = ljzStatistical;
    }

    public List<DY> getDyList() {
        return dyList;
    }

    public void setDyList(List<DY> dyList) {
        this.dyList = dyList;
    }

    public List<C> getcList() {
        return cList;
    }

    public void setcList(List<C> cList) {
        this.cList = cList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLjzh() {
        return ljzh;
    }

    public void setLjzh(String ljzh) {
        this.ljzh = ljzh;
    }

    public String getZrzh() {
        return zrzh;
    }

    public void setZrzh(String zrzh) {
        this.zrzh = zrzh;
    }

    public String getMph() {
        return mph;
    }

    public void setMph(String mph) {
        this.mph = mph;
    }

    public Double getScjzmj() {
        return scjzmj;
    }

    public void setScjzmj(Double scjzmj) {
        this.scjzmj = scjzmj;
    }

    public String getFwyt1() {
        return fwyt1;
    }

    public void setFwyt1(String fwyt1) {
        this.fwyt1 = fwyt1;
    }

    public Integer getZcs() {
        return zcs;
    }

    public void setZcs(Integer zcs) {
        this.zcs = zcs;
    }

    public String getYsdm() {
        return ysdm;
    }

    public void setYsdm(String ysdm) {
        this.ysdm = ysdm;
    }

    public Double getYcjzmj() {
        return ycjzmj;
    }

    public void setYcjzmj(Double ycjzmj) {
        this.ycjzmj = ycjzmj;
    }

    public Double getYcdxmj() {
        return ycdxmj;
    }

    public void setYcdxmj(Double ycdxmj) {
        this.ycdxmj = ycdxmj;
    }

    public Double getYcqtmj() {
        return ycqtmj;
    }

    public void setYcqtmj(Double ycqtmj) {
        this.ycqtmj = ycqtmj;
    }

    public Double getScdxmj() {
        return scdxmj;
    }

    public void setScdxmj(Double scdxmj) {
        this.scdxmj = scdxmj;
    }

    public Double getScqtmj() {
        return scqtmj;
    }

    public void setScqtmj(Double scqtmj) {
        this.scqtmj = scqtmj;
    }

    public Long getJgrq() {
        return jgrq;
    }

    public void setJgrq(Long jgrq) {
        this.jgrq = jgrq;
    }

    public String getFwjg1() {
        return fwjg1;
    }

    public void setFwjg1(String fwjg1) {
        this.fwjg1 = fwjg1;
    }

    public String getFwjg2() {
        return fwjg2;
    }

    public void setFwjg2(String fwjg2) {
        this.fwjg2 = fwjg2;
    }

    public String getFwjg3() {
        return fwjg3;
    }

    public void setFwjg3(String fwjg3) {
        this.fwjg3 = fwjg3;
    }

    public String getJzwzt() {
        return jzwzt;
    }

    public void setJzwzt(String jzwzt) {
        this.jzwzt = jzwzt;
    }

    public String getFwyt2() {
        return fwyt2;
    }

    public void setFwyt2(String fwyt2) {
        this.fwyt2 = fwyt2;
    }

    public String getFwyt3() {
        return fwyt3;
    }

    public void setFwyt3(String fwyt3) {
        this.fwyt3 = fwyt3;
    }

    public Short getDscs() {
        return dscs;
    }

    public void setDscs(Short dscs) {
        this.dscs = dscs;
    }

    public Short getDxcs() {
        return dxcs;
    }

    public void setDxcs(Short dxcs) {
        this.dxcs = dxcs;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getZl() {
        return zl;
    }

    public void setZl(String zl) {
        this.zl = zl;
    }

    public String getZt() {
        return zt;
    }

    public void setZt(String zt) {
        this.zt = zt;
    }

    public Long getZid() {
        return zid;
    }

    public void setZid(Long zid) {
        this.zid = zid;
    }

    public Double getTljzmj() {
        return tljzmj;
    }

    public void setTljzmj(Double tljzmj) {
        this.tljzmj = tljzmj;
    }

    public Double getFtjzmj() {
        return ftjzmj;
    }

    public void setFtjzmj(Double ftjzmj) {
        this.ftjzmj = ftjzmj;
    }

    public String getDh() {
        return dh;
    }

    public void setDh(String dh) {
        this.dh = dh;
    }

    public String getZbm() {
        return zbm;
    }

    public void setZbm(String zbm) {
        this.zbm = zbm;
    }

    public String getChzt() {
        return chzt;
    }

    public void setChzt(String chzt) {
        this.chzt = chzt;
    }

    public String getJlx() {
        return jlx;
    }

    public void setJlx(String jlx) {
        this.jlx = jlx;
    }

    public String getXqmc() {
        return xqmc;
    }

    public void setXqmc(String xqmc) {
        this.xqmc = xqmc;
    }

    public Long getDyzs() {
        return dyzs;
    }

    public void setDyzs(Long dyzs) {
        this.dyzs = dyzs;
    }

    public Double getZzmj() {
        return zzmj;
    }

    public void setZzmj(Double zzmj) {
        this.zzmj = zzmj;
    }

    public Double getBgmj() {
        return bgmj;
    }

    public void setBgmj(Double bgmj) {
        this.bgmj = bgmj;
    }

    public Double getSfmj() {
        return sfmj;
    }

    public void setSfmj(Double sfmj) {
        this.sfmj = sfmj;
    }

    public Long getZzts() {
        return zzts;
    }

    public void setZzts(Long zzts) {
        this.zzts = zzts;
    }

    public Long getBgts() {
        return bgts;
    }

    public void setBgts(Long bgts) {
        this.bgts = bgts;
    }

    public Long getSfts() {
        return sfts;
    }

    public void setSfts(Long sfts) {
        this.sfts = sfts;
    }

    public Long getQtts() {
        return qtts;
    }

    public void setQtts(Long qtts) {
        this.qtts = qtts;
    }

    public String getZly() {
        return zly;
    }

    public void setZly(String zly) {
        this.zly = zly;
    }

    public String getYzbm() {
        return yzbm;
    }

    public void setYzbm(String yzbm) {
        this.yzbm = yzbm;
    }

    public String getDzzh() {
        return dzzh;
    }

    public void setDzzh(String dzzh) {
        this.dzzh = dzzh;
    }

    public String getDzbdcdyh() {
        return dzbdcdyh;
    }

    public void setDzbdcdyh(String dzbdcdyh) {
        this.dzbdcdyh = dzbdcdyh;
    }

    public String getYljzh() {
        return yljzh;
    }

    public void setYljzh(String yljzh) {
        this.yljzh = yljzh;
    }

    public String getYtmc() {
        return ytmc;
    }

    public void setYtmc(String ytmc) {
        this.ytmc = ytmc;
    }

    public Double getLjzid() {
        return ljzid;
    }

    public void setLjzid(Double ljzid) {
        this.ljzid = ljzid;
    }

    public String getGhyt() {
        return ghyt;
    }

    public void setGhyt(String ghyt) {
        this.ghyt = ghyt;
    }

    public Double getZzdmj() {
        return zzdmj;
    }

    public void setZzdmj(Double zzdmj) {
        this.zzdmj = zzdmj;
    }

    public String getQxdm() {
        return qxdm;
    }

    public void setQxdm(String qxdm) {
        this.qxdm = qxdm;
    }
}
