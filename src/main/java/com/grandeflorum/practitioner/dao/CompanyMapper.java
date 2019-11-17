package com.grandeflorum.practitioner.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.practitioner.domain.Company;

import java.util.List;
import java.util.Map;

public interface CompanyMapper extends MyMapper<Company> {

    List<Company> getCompanyList(Map<String,Object> map);

    void auditCompanyById(Map<String,Object> map);

    int checkCompanyName(Map<String,Object> map);
}