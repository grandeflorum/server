package com.grandeflorum.contract.domain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "HOUSE_STOCK_TRADE_HISTORY")
public class StockTradeHistory {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "STOCKTRADEID")
    private String stocktradeid;

    @Column(name = "SYS_DATE")
    private Date sysDate;

    @Column(name = "CURRENTSTATUS")
    private Short currentstatus;

    @Column(name = "HISTORYOBJ")
    private String historyobj;

    private StockTrade stockTrade ;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StockTrade getStockTrade() {
        return stockTrade;
    }

    public void setStockTrade(StockTrade stockTrade) {
        this.stockTrade = stockTrade;
    }

    public String getHistoryobj() {
        return historyobj;
    }

    public void setHistoryobj(String historyobj) {
        this.historyobj = historyobj;
    }

    public String getStocktradeid() {
        return stocktradeid;
    }

    public void setStocktradeid(String stocktradeid) {
        this.stocktradeid = stocktradeid;
    }

    public Date getSysDate() {
        return sysDate;
    }

    public void setSysDate(Date sysDate) {
        this.sysDate = sysDate;
    }

    public Short getCurrentstatus() {
        return currentstatus;
    }

    public void setCurrentstatus(Short currentstatus) {
        this.currentstatus = currentstatus;
    }
}
