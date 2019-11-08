package com.grandeflorum.contract.dao;

import com.grandeflorum.contract.domain.ContractTemplateHistory;
import com.grandeflorum.common.config.MyMapper;

import java.util.List;
import java.util.Map;

public interface ContractTemplateHistoryMapper extends MyMapper<ContractTemplateHistory> {
    List<ContractTemplateHistory> getContractTemplateHistoryList(Map<String, Object> map);
}
