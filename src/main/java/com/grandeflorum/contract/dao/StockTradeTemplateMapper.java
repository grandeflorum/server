package com.grandeflorum.contract.dao;

import com.grandeflorum.contract.domain.StockTradeTemplate;
import com.grandeflorum.common.config.MyMapper;

import java.util.List;
import java.util.Map;

public interface StockTradeTemplateMapper extends MyMapper<StockTradeTemplate> {
    String getIdByStocktradeId(String stocktradeid);

    StockTradeTemplate getAllByStocktradeId(String stocktradeid);

}
