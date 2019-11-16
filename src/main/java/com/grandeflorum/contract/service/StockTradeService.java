package com.grandeflorum.contract.service;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.IService;
import com.grandeflorum.contract.domain.StockTrade;
import com.grandeflorum.project.domain.AuditParam;

public interface StockTradeService extends IService<StockTrade> {


    ResponseBo btachAuditStockTrade(AuditParam auditParam);

    ResponseBo getStockTradeHistory(String id);

    ResponseBo auditStockTradeById(String id, int type);

    // 保持或更新存量房源
    String saveOrUpdateStockTrade(StockTrade stockTrade);

    // 获取存量房源信息
    ResponseBo getStockTradeById(String id);

    //获取存量房源列表
    ResponseBo getStockTradeList(Page page);

    ResponseBo linkH(String id,String hid);
}
