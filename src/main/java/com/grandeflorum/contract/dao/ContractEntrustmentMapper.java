package com.grandeflorum.contract.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.contract.domain.ContractEntrustment;

public interface ContractEntrustmentMapper extends MyMapper<ContractEntrustment> {

    String getIdByStocktradeId(String stocktradeid);

    ContractEntrustment getAllByStocktradeId(String stocktradeid);

    ContractEntrustment getContractEntrustmentByStocktradeId(String id);
}
