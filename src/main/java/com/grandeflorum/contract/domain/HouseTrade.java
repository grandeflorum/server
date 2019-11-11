package com.grandeflorum.contract.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "HOUSE_NEW_TRADE")
public class HouseTrade {
    @Id
    @Column(name = "ID")
    public String id;

    @Column(name = "CURRENTSTATUS")
    public Integer currentStatus;

    @Column(name = "ISPASS")
    public Integer isPass;

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
}
