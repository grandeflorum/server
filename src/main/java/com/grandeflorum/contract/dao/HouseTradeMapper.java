package com.grandeflorum.contract.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.contract.domain.ContractCancel;
import com.grandeflorum.contract.domain.HouseTrade;
import com.grandeflorum.practitioner.domain.Company;
import com.grandeflorum.project.domain.Project;

import java.util.List;
import java.util.Map;

public interface HouseTradeMapper extends MyMapper<HouseTrade> {

    List<HouseTrade> getHouseTradeList(Map<String, Object> map);

    List<ContractCancel> getHouseTradeCancelList(Map<String, Object> map);

    void auditHouseTradeById(Map<String, Object> map);

    void linkH(Map<String, Object> map);

    String getLjzh(String hid);

    HouseTrade getHouseTradeById(String id);

    Company getCompanyByAssociatedId(String id);

    Project getProjectByCompanyId(String companyId);

    Map<String,String> queryHinfoByTradeId(String id);

    HouseTrade getHInfo(String hid);

    int checkExistCompletionFile(String tradeId);

    void sh(String id);
}

