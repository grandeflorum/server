package com.grandeflorum.contract.dao;

import com.grandeflorum.StockHouse.domin.StockHouse;
import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.contract.domain.ContractCancel;
import com.grandeflorum.contract.domain.StockTrade;

import java.util.List;
import java.util.Map;

public interface StockTradeMapper extends MyMapper<StockTrade> {
    List<StockTrade> getStockTradeList(Map<String, Object> map);

    List<ContractCancel> getStockTradeCancelList(Map<String, Object> map);

    void auditStockTradeById(Map<String, Object> map);

    void linkH(Map<String,Object> map);

    String getLjzh(String hid);

    StockTrade getStockTradeById(String id);

    StockTrade getEwmById(String id);

    Map<String,String> queryHinfoByStockId(String id);

    void sh(String id);

    void xgsh(String id);

    int getShZtById(String id);

    StockTrade getHInfo(String hid);
}
