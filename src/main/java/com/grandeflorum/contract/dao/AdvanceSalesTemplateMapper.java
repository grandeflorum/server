package com.grandeflorum.contract.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.contract.domain.AdvanceSalesTemplate;

public interface AdvanceSalesTemplateMapper extends MyMapper<AdvanceSalesTemplate> {

    String getIdByHousetradeId(String housetradeid);

    AdvanceSalesTemplate getAllByHousetradeId(String housetradeid);
}
