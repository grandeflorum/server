package com.grandeflorum.contract.service;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.IService;
import com.grandeflorum.contract.domain.HouseTrade;
import com.grandeflorum.project.domain.AuditParam;

public interface HouseTradeService extends IService<HouseTrade> {


    ResponseBo btachAuditHouseTrade(AuditParam auditParam);

    ResponseBo getHouseTradeHistory(String id);

    // 保持或更新存量房源
    String saveOrUpdateHouseTrade(HouseTrade houseTrade  );

    // 获取存量房源信息
    ResponseBo getHouseTradeById(String id);

    //获取存量房源列表
    ResponseBo getHouseTradeList(Page page);
}
