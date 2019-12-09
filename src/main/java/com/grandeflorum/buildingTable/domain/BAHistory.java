package com.grandeflorum.buildingTable.domain;

import java.util.Date;

public class BAHistory {
    public String id;
    /**
     * 合同备案号
     */
    public String htbah;
    /**
     * 购买人
     */
    public String gmr;
    /**
     * 备案时间
     */
    public Date basj;
    /**
     * 1:商品房 2:存量房
     */
    public Integer isnewstock;
    /**
     * 房屋id
     */
    public String houseid;
    /**
     * 房号
     */
    public String fh;

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

    public String getGmr() {
        return gmr;
    }

    public void setGmr(String gmr) {
        this.gmr = gmr;
    }

    public Date getBasj() {
        return basj;
    }

    public void setBasj(Date basj) {
        this.basj = basj;
    }

    public Integer getIsnewstock() {
        return isnewstock;
    }

    public void setIsnewstock(Integer isnewstock) {
        this.isnewstock = isnewstock;
    }

    public String getHouseid() {
        return houseid;
    }

    public void setHouseid(String houseid) {
        this.houseid = houseid;
    }

    public String getFh() {
        return fh;
    }

    public void setFh(String fh) {
        this.fh = fh;
    }
}
