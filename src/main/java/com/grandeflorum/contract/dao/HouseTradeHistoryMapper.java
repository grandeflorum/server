package com.grandeflorum.contract.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.contract.domain.HouseTradeHistory;

import java.util.List;

public interface HouseTradeHistoryMapper extends MyMapper<HouseTradeHistory> {

    List<HouseTradeHistory> getHistoryList(String id);
}