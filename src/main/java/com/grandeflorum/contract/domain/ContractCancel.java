package com.grandeflorum.contract.domain;

import java.util.Date;

/**
 * 合同注销列表
 */
public class ContractCancel {
    /**
     * id
     */
    public String id;
    /**
     * 合同备案号
     */
    public String htbah;
    /**
     * 产权人
     */
    public String cqr;
    /**
     * 购买人
     */
    public String gmr;
    /**
     * 注销日期
     */
    public Date zxrq;
    /**
     * 注销理由
     */
    public String zxly;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHtbah() {
        return htbah;
    }

    public void setHtbah(String htbah) {
        this.htbah = htbah;
    }

    public String getCqr() {
        return cqr;
    }

    public void setCqr(String cqr) {
        this.cqr = cqr;
    }

    public String getGmr() {
        return gmr;
    }

    public void setGmr(String gmr) {
        this.gmr = gmr;
    }

    public Date getZxrq() {
        return zxrq;
    }

    public void setZxrq(Date zxrq) {
        this.zxrq = zxrq;
    }

    public String getZxly() {
        return zxly;
    }

    public void setZxly(String zxly) {
        this.zxly = zxly;
    }
}