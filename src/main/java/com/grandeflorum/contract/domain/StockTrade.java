package com.grandeflorum.contract.domain;

import com.grandeflorum.StockHouse.domin.RelationShip;
import com.grandeflorum.project.domain.WFAudit;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Table(name = "HOUSE_STOCK_TRADE")
public class StockTrade implements Cloneable{
    @Id
    @Column(name = "ID")
    public String id;
    /**
     *合同编号
     */
    @Column(name = "HTBH")
    public String htbh;
    /**
     *甲方
     */
    @Column(name = "JF")
    public String jf;
    /**
     *甲方联系地址
     */
    @Column(name = "JFLXDZ")
    public String jflxdz;
    /**
     *甲方证件类型
     */
    @Column(name = "JFZJLX")
    public String jfzjlx;
    /**
     *甲方证件号码
     */
    @Column(name = "JFZJHM")
    public String jfzjhm;
    /**
     *甲方联系电话
     */
    @Column(name = "JFLXDH")
    public String jflxdh;
    /**
     *乙方
     */
    @Column(name = "YF")
    public String yf;
    /**
     *乙方联系地址
     */
    @Column(name = "YFLXDZ")
    public String yflxdz;
    /**
     *乙方证件类型
     */
    @Column(name = "YFZJLX")
    public String yfzjlx;
    /**
     *乙方证件号码
     */
    @Column(name = "YFZJHM")
    public String yfzjhm;
    /**
     *乙方联系电话
     */
    @Column(name = "YFLXDH")
    public String yflxdh;
    /**
     *单价
     */
    @Column(name = "DJ")
    public Double dj;
    /**
     *总价
     */
    @Column(name = "ZJ")
    public Double zj;
    /**
     *入网时间
     */
    @Column(name = "RWSJ")
    public Date rwsj;
    /**
     *关联户id
     */
    @Column(name = "HOUSEID")
    public String houseId;
    /**
     *合同模板id
     */
    @Column(name = "CONTRACTID")
    public String contractId;
    /**
     *合同备案号
     */
    @Column(name = "HTBAH")
    public String htbah;

    /**
     * 当前状态
     */
    @Column(name = "CURRENTSTATUS")
    public Integer currentStatus;



    /**
     * 是否通过
     */
    @Column(name = "ISPASS")
    public Integer isPass;

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
     * 登记人
     */
    @Column(name="DJR")
    public String djr;

    /**
     * 流程信息
     */
    public List<WFAudit> wfAuditList;

    /**
     * 多个购买人
     */
    public List<RelationShip> relationShips;

    @Transient
    private String ljzid;


    /**
     * 项目名称
     */
    @Transient
    private String xmmc;
    /**
     * 建筑物名称
     */
    @Transient
    private String jzwmc;
    /**
     * 幢号
     */
    @Transient
    private String zh;

    /**
     * 单元号
     */
    @Transient
    private String dyh;
    /**
     * 层号
     */
    @Transient
    private String ch;
    /**
     * 房号
     */
    @Transient
    private String fh;

    /**
     * 委托合同id
     */
    @Transient
    private String contractEntrustmentId;

    /**
     * 是否注销
     */
    @Column(name = "ISCANCEL")
    public Integer isCancel;

    /**
     * 交易方式
     */
    @Column(name = "JYFS")
    public Integer jyfs;

    /**
     * 不动产权证号
     */
    @Column(name = "BDCQZH")
    public String bdcqzh;

    /**
     * 登记时间
     * 改为 权证时间
     */
    @Column(name = "DJSJ")
    public Date djsj;

    /**
     * 备案时间
     */
    @Column(name="BASJ")
    public Date basj;

    /**
     * 共有情况
     */
    @Column(name = "GYQK")
    public Integer gyqk;

    /**
     * 共有比例
     */
    @Column(name = "GYBL")
    public String gybl;

    /**
     * 甲方共有方式
     */
    @Column(name = "JFGYFS")
    public String jfgyfs;

    /**
     * 甲方共有比例
     */
    @Column(name = "JFGYBL")
    public String jfgybl;

    /**
     * 已方共有方式
     */
    @Column(name = "YFGYFS")
    public String yfgyfs;

    /**
     * 已方共有比例
     */
    @Column(name = "YFGYBL")
    public String yfgybl;

    @Transient
    private String bg;

    @Transient
    private String bgly;

    /**
     * 坐落
     */
    @Transient
    private String zl;

    /**
     * 甲方邮政编码
     */
    @Column(name = "JFYZBM")
    private String jfyzbm;

    /**
     * 乙方邮政编码
     */
    @Column(name = "YFYZBM")
    private String yfyzbm;

    /**
     * 办证时间
     */
    @Column(name = "BZSJ")
    private Date bzsj;

    /**
     * 乙方首付款时间
     */
    @Column(name = "YFSFKSJ")
    private Date yfsfksj;

    /**
     * 乙方首付款金额
     */
    @Column(name = "YFSFKJE")
    private Double yfsfkje;

    /**
     * 乙方剩余房款
     */
    @Column(name = "YFSYFK")
    private Double yfsyfk;

    /**
     *乙方付款方式
     */
    @Column(name = "YFFKFS")
    private String yffkfs;

    /**
     * 甲乙两方缴纳税费约定
     */
    @Column(name = "JYLFJNSFYD")
    private String jylfjnsfyd;

    /**
     * 合同签订日起多少日内
     */
    @Column(name = "HTQJRDSRN")
    private Double htqjrdsrn;

    /**
     * 房屋约定交付时间
     */
    @Column(name = "FWYDJFSJ")
    private Date fwydjfsj;

    /**
     * 甲方房屋欠费交清时间
     */
    @Column(name = "JFFWQFJQSJ")
    private Date jffwqfjqsj;

    /**
     * 甲方违约金
     */
    @Column(name = "JFWYJ")
    private String jfwyj;

    /**
     * 甲方违约天数
     */
    @Column(name = "JFWYTS")
    private String jfwyts;

    /**
     * 乙方违约金
     */
    @Column(name = "YFWYJ")
    private String yfwyj;

    /**
     * 乙方违约天数
     */
    @Column(name = "YFWYTS")
    private String yfwyts;

    /**
     * 违约处理方式
     */
    @Column(name = "WYCLFS")
    private String wyclfs;

    /**
     *违约方违约金
     */
    @Column(name = "WYFWYJ")
    private String wyfwyj;

    /**
     *房屋用途
     */
    @Column(name = "HTFWYT")
    private Integer htfwyt;

    /**
     *户型
     */
    @Column(name = "HTHX")
    private Integer hthx;

    /**
     *房屋结构
     */
    @Column(name = "FWJG")
    private Integer fwjg;

    /**
     *建筑面积
     */
    @Transient
    private String jzmj;

    public  String getJzmj(){ return jzmj; }

    public void setJzmj(String jzmj) {
        this.jzmj = jzmj;
    }

    /**
     *房屋用途
     */
    @Transient
    private String fwyt;

    public String getContractEntrustmentId() {
        return contractEntrustmentId;
    }

    public void setContractEntrustmentId(String contractEntrustmentId) {
        this.contractEntrustmentId = contractEntrustmentId;
    }

    public  String getFwyt(){ return fwyt; }

    public void setFwyt(String fwyt) {
        this.fwyt = fwyt;
    }

    public ContractEntrustment contractEntrustment;

    public ContractEntrustment getContractEntrustment() {
        return contractEntrustment;
    }

    public void setContractEntrustment(ContractEntrustment contractEntrustment) {
        this.contractEntrustment = contractEntrustment;
    }

    public StockTradeTemplate stockTradeTemplate;

    public StockTradeTemplate getStockTradeTemplate() {
        return stockTradeTemplate;
    }

    public void setStockTradeTemplate(StockTradeTemplate stockTradeTemplate) {
        this.stockTradeTemplate = stockTradeTemplate;
    }

    public Date getBasj() {
        return basj;
    }

    public void setBasj(Date basj) {
        this.basj = basj;
    }

    public String getDjr() {
        return djr;
    }

    public void setDjr(String djr) {
        this.djr = djr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(Integer currentStatus) {
        this.currentStatus = currentStatus;
    }

    public Integer getIsPass() {
        return isPass;
    }

    public void setIsPass(Integer isPass) {
        this.isPass = isPass;
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

    public String getHtbh() {
        return htbh;
    }

    public void setHtbh(String htbh) {
        this.htbh = htbh;
    }

    public String getJf() {
        return jf;
    }

    public void setJf(String jf) {
        this.jf = jf;
    }

    public String getJflxdz() {
        return jflxdz;
    }

    public void setJflxdz(String jflxdz) {
        this.jflxdz = jflxdz;
    }


    public String getJfzjhm() {
        return jfzjhm;
    }

    public void setJfzjhm(String jfzjhm) {
        this.jfzjhm = jfzjhm;
    }

    public String getJflxdh() {
        return jflxdh;
    }

    public void setJflxdh(String jflxdh) {
        this.jflxdh = jflxdh;
    }

    public String getYf() {
        return yf;
    }

    public void setYf(String yf) {
        this.yf = yf;
    }

    public String getYflxdz() {
        return yflxdz;
    }

    public void setYflxdz(String yflxdz) {
        this.yflxdz = yflxdz;
    }

    public String getJfzjlx() {
        return jfzjlx;
    }

    public void setJfzjlx(String jfzjlx) {
        this.jfzjlx = jfzjlx;
    }

    public String getYfzjlx() {
        return yfzjlx;
    }

    public void setYfzjlx(String yfzjlx) {
        this.yfzjlx = yfzjlx;
    }

    public String getYfzjhm() {
        return yfzjhm;
    }

    public void setYfzjhm(String yfzjhm) {
        this.yfzjhm = yfzjhm;
    }

    public String getYflxdh() {
        return yflxdh;
    }

    public void setYflxdh(String yflxdh) {
        this.yflxdh = yflxdh;
    }

    public Double getDj() {
        return dj;
    }

    public void setDj(Double dj) {
        this.dj = dj;
    }

    public Double getZj() {
        return zj;
    }

    public void setZj(Double zj) {
        this.zj = zj;
    }

    public Date getRwsj() {
        return rwsj;
    }

    public void setRwsj(Date rwsj) {
        this.rwsj = rwsj;
    }

    public String getHouseId() {
        return houseId;
    }

    public void setHouseId(String houseId) {
        this.houseId = houseId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getHtbah() {
        return htbah;
    }

    public void setHtbah(String htbah) {
        this.htbah = htbah;
    }

    public List<WFAudit> getWfAuditList() {
        return wfAuditList;
    }

    public void setWfAuditList(List<WFAudit> wfAuditList) {
        this.wfAuditList = wfAuditList;
    }

    public String getLjzid() {
        return ljzid;
    }

    public void setLjzid(String ljzid) {
        this.ljzid = ljzid;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getJzwmc() {
        return jzwmc;
    }

    public void setJzwmc(String jzwmc) {
        this.jzwmc = jzwmc;
    }

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public String getDyh() {
        return dyh;
    }

    public void setDyh(String dyh) {
        this.dyh = dyh;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public String getFh() {
        return fh;
    }

    public void setFh(String fh) {
        this.fh = fh;
    }

    public Integer getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(Integer isCancel) {
        this.isCancel = isCancel;
    }

    public Integer getJyfs() {
        return jyfs;
    }

    public void setJyfs(Integer jyfs) {
        this.jyfs = jyfs;
    }

    public List<RelationShip> getRelationShips() {
        return relationShips;
    }

    public void setRelationShips(List<RelationShip> relationShips) {
        this.relationShips = relationShips;
    }

    public String getBdcqzh() {
        return bdcqzh;
    }

    public void setBdcqzh(String bdcqzh) {
        this.bdcqzh = bdcqzh;
    }

    public Date getDjsj() {
        return djsj;
    }

    public void setDjsj(Date djsj) {
        this.djsj = djsj;
    }

    public String getBg() {
        return bg;
    }

    public void setBg(String bg) {
        this.bg = bg;
    }

    public String getBgly() {
        return bgly;
    }

    public void setBgly(String bgly) {
        this.bgly = bgly;
    }

    public String getZl() {
        return zl;
    }

    public void setZl(String zl) {
        this.zl = zl;
    }

    public Integer getGyqk() {
        return gyqk;
    }

    public void setGyqk(Integer gyqk) {
        this.gyqk = gyqk;
    }

    public String getGybl() {
        return gybl;
    }

    public void setGybl(String gybl) {
        this.gybl = gybl;
    }

    public String getJfgyfs() {
        return jfgyfs;
    }

    public void setJfgyfs(String jfgyfs) {
        this.jfgyfs = jfgyfs;
    }

    public String getJfgybl() {
        return jfgybl;
    }

    public void setJfgybl(String jfgybl) {
        this.jfgybl = jfgybl;
    }

    public String getYfgyfs() {
        return yfgyfs;
    }

    public void setYfgyfs(String yfgyfs) {
        this.yfgyfs = yfgyfs;
    }

    public String getYfgybl() {
        return yfgybl;
    }

    public void setYfgybl(String yfgybl) {
        this.yfgybl = yfgybl;
    }

    @Override
    public Object clone() {

        Object object = null;
        try {
            object = super.clone();
        } catch (Exception e) {

        }

        return object;
    }

    public String getJfyzbm() {
        return jfyzbm;
    }

    public void setJfyzbm(String jfyzbm) {
        this.jfyzbm = jfyzbm;
    }

    public String getYfyzbm() {
        return yfyzbm;
    }

    public void setYfyzbm(String yfyzbm) {
        this.yfyzbm = yfyzbm;
    }

    public Date getBzsj() {
        return bzsj;
    }

    public void setBzsj(Date bzsj) {
        this.bzsj = bzsj;
    }

    public Date getYfsfksj() {
        return yfsfksj;
    }

    public void setYfsfksj(Date yfsfksj) {
        this.yfsfksj = yfsfksj;
    }

    public Double getYfsfkje() {
        return yfsfkje;
    }

    public void setYfsfkje(Double yfsfkje) {
        this.yfsfkje = yfsfkje;
    }

    public Double getYfsyfk() {
        return yfsyfk;
    }

    public void setYfsyfk(Double yfsyfk) {
        this.yfsyfk = yfsyfk;
    }

    public String getYffkfs() {
        return yffkfs;
    }

    public void setYffkfs(String yffkfs) {
        this.yffkfs = yffkfs;
    }

    public String getJylfjnsfyd() {
        return jylfjnsfyd;
    }

    public void setJylfjnsfyd(String jylfjnsfyd) {
        this.jylfjnsfyd = jylfjnsfyd;
    }

    public Double getHtqjrdsrn() {
        return htqjrdsrn;
    }

    public void setHtqjrdsrn(Double htqjrdsrn) {
        this.htqjrdsrn = htqjrdsrn;
    }

    public Date getFwydjfsj() {
        return fwydjfsj;
    }

    public void setFwydjfsj(Date fwydjfsj) {
        this.fwydjfsj = fwydjfsj;
    }

    public Date getJffwqfjqsj() {
        return jffwqfjqsj;
    }

    public void setJffwqfjqsj(Date jffwqfjqsj) {
        this.jffwqfjqsj = jffwqfjqsj;
    }

    public String getJfwyj() {
        return jfwyj;
    }

    public void setJfwyj(String jfwyj) {
        this.jfwyj = jfwyj;
    }

    public String getJfwyts() {
        return jfwyts;
    }

    public void setJfwyts(String jfwyts) {
        this.jfwyts = jfwyts;
    }

    public String getYfwyj() {
        return yfwyj;
    }

    public void setYfwyj(String yfwyj) {
        this.yfwyj = yfwyj;
    }

    public String getYfwyts() {
        return yfwyts;
    }

    public void setYfwyts(String yfwyts) {
        this.yfwyts = yfwyts;
    }

    public String getWyclfs() {
        return wyclfs;
    }

    public void setWyclfs(String wyclfs) {
        this.wyclfs = wyclfs;
    }

    public String getWyfwyj() {
        return wyfwyj;
    }

    public void setWyfwyj(String wyfwyj) {
        this.wyfwyj = wyfwyj;
    }

    public Integer getHtfwyt() { return htfwyt; }

    public void setHtfwyt(Integer htfwyt) { this.htfwyt = htfwyt; }

    public Integer getHthx() { return hthx; }

    public void setHthx(Integer hthx) { this.hthx = hthx; }

    public Integer getFwjg() { return fwjg; }

    public void setFwjg(Integer fwjg) { this.fwjg = fwjg; }

}
