package com.grandeflorum.contract.service;

import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.IService;
import com.grandeflorum.contract.domain.HouseTrade;
import com.grandeflorum.project.domain.AuditParam;

public interface HouseTradeService extends IService<HouseTrade> {


    ResponseBo btachAuditHouseTrade(AuditParam auditParam);

    ResponseBo getHouseTradeHistory(String id);
}
