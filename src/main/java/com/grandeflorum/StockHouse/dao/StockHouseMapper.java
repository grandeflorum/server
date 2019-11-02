package com.grandeflorum.StockHouse.dao;

import com.grandeflorum.StockHouse.domin.StockHouse;
import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.project.domain.WFAudit;

import java.util.List;
import java.util.Map;

public interface StockHouseMapper  extends MyMapper<StockHouse> {

    List<StockHouse> getStockHouseList(Map<String, Object> map);

    int addOrUpdateAudit(WFAudit param);
}
