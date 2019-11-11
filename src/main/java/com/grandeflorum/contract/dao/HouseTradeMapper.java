package com.grandeflorum.contract.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.contract.domain.HouseTrade;

import java.util.List;
import java.util.Map;

public interface HouseTradeMapper extends MyMapper<HouseTrade> {

    List<HouseTrade> getHouseTradeList(Map<String, Object> map);
}
