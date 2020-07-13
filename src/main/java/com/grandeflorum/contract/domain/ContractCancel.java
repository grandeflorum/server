package com.grandeflorum.contract.domain;

import java.util.Date;

/**
 * 合同历史列表
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
     * 坐落
     */
    public String zl;



    /**
     * 日期
     */
    public Date zxrq;
    /**
     * 理由
     */
    public String zxly;

    /**
     * 状态 1 注销 2变更
     */
    public int status;


    public String getZl() {
        return zl;
    }

    public void setZl(String zl) {
        this.zl = zl;
    }

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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
