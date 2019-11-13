package com.grandeflorum.houseRental.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.houseRental.domain.HouseRental;
import com.grandeflorum.houseRental.domain.HouseRentalExtend;

import java.util.List;
import java.util.Map;

public interface HouseRentalMapper extends MyMapper<HouseRental> {

    List<HouseRentalExtend> getHouseRentalList(Map<String,Object> map);

    void linkH(Map<String,Object> map);

    String getLjzh(String hid);
}