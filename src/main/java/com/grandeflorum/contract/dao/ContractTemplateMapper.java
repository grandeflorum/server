package com.grandeflorum.contract.dao;

import com.grandeflorum.contract.domain.ContractTemplate;
import com.grandeflorum.common.config.MyMapper;

public interface ContractTemplateMapper extends MyMapper<ContractTemplate> {
    ContractTemplate getContractTemplateByType(Integer type);
}
