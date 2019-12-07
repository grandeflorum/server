package com.grandeflorum.StockHouse.service;

import com.grandeflorum.StockHouse.domin.StockHouse;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.IService;
import com.grandeflorum.project.domain.AuditParam;

public interface StockHouseService  extends IService<StockHouse> {
    // 保持或更新存量房源
    StockHouse saveOrUpdateStockHouse(StockHouse stockHouse );

    // 获取存量房源信息
    ResponseBo getStockHouseById(String id);

    //获取存量房源列表
    ResponseBo getStockHouseList(Page page);

    //审核项目
    ResponseBo auditStockHouses(AuditParam auditParam);

    //提交审核
    ResponseBo auditStockHouseById(String id,int type);
}
