package com.grandeflorum.practitioner.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.practitioner.domain.AssociatedCompany;

import java.util.Map;

public interface AssociatedCompanyMapper extends MyMapper<AssociatedCompany> {

    AssociatedCompany GetAssociatedCompany(Map<String,String> map);

}