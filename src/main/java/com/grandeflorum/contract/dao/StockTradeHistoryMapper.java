package com.grandeflorum.contract.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.contract.domain.StockTradeHistory;

import java.util.List;

public interface StockTradeHistoryMapper extends MyMapper<StockTradeHistory> {

    List<StockTradeHistory> getHistoryList(String id);
}
