package com.grandeflorum.contract.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "HOUSE_NEW_TRADE_HISTORY")
public class HouseTradeHistory {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "HOUSETRADEID")
    private String housetradeid;

    @Column(name = "SYS_DATE")
    private Date sysDate;

    @Column(name = "CURRENTSTATUS")
    private Short currentstatus;

    @Column(name = "HISTORYOBJ")
    private String historyobj;

    private HouseTrade houseTrade;


    public HouseTrade getHouseTrade() {
        return houseTrade;
    }

    public void setHouseTrade(HouseTrade houseTrade) {
        this.houseTrade = houseTrade;
    }

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

    public String getHousetradeid() {
        return housetradeid;
    }

    public void setHousetradeid(String housetradeid) {
        this.housetradeid = housetradeid;
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
     * @return CURRENTSTATUS
     */
    public Short getCurrentstatus() {
        return currentstatus;
    }

    /**
     * @param currentstatus
     */
    public void setCurrentstatus(Short currentstatus) {
        this.currentstatus = currentstatus;
    }

    /**
     * @return HISTORYOBJ
     */
    public String getHistoryobj() {
        return historyobj;
    }

    /**
     * @param historyobj
     */
    public void setHistoryobj(String historyobj) {
        this.historyobj = historyobj == null ? null : historyobj.trim();
    }
}