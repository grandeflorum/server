package com.grandeflorum.houseRental.domain;

import javax.persistence.Column;

/**
 * Created by 13260 on 2019/11/9.
 */
public class HouseRentalExtend extends HouseRental{

    @Column(name = "regionname")
    private String regionName;

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }
}
