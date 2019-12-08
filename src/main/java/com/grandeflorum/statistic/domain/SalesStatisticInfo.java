package com.grandeflorum.statistic.domain;

public class SalesStatisticInfo {

    /**
     * 企业名称
     */
    private String qymc;

    /**
     * 项目名称
     */
    private String xmmc;

    /**
     * 用途
     */
    private String yt;

    /**
     * 均价
     */
    private double jj;

    /**
     * 销售面积
     */
    private double xsmj;

    /**
     * 销售量
     */
    private double xsl;

    /**
     * 总价
     */
    private double zj;


    public String getQymc() {
        return qymc;
    }

    public void setQymc(String qymc) {
        this.qymc = qymc;
    }

    public String getXmmc() {
        return xmmc;
    }

    public void setXmmc(String xmmc) {
        this.xmmc = xmmc;
    }

    public String getYt() {
        return yt;
    }

    public void setYt(String yt) {
        this.yt = yt;
    }

    public double getJj() {
        return jj;
    }

    public void setJj(double jj) {
        this.jj = jj;
    }

    public double getXsmj() {
        return xsmj;
    }

    public void setXsmj(double xsmj) {
        this.xsmj = xsmj;
    }

    public double getXsl() {
        return xsl;
    }

    public void setXsl(double xsl) {
        this.xsl = xsl;
    }

    public double getZj() {
        return zj;
    }

    public void setZj(double zj) {
        this.zj = zj;
    }
}
