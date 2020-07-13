package com.grandeflorum.contract.dao;

import com.grandeflorum.contract.domain.CashSalesTemplate;
import com.grandeflorum.common.config.MyMapper;

public interface CashSalesTemplateMapper extends MyMapper<CashSalesTemplate> {

    String getIdByHousetradeId(String housetradeid);

    CashSalesTemplate getAllByHousetradeId(String housetradeid);
}
