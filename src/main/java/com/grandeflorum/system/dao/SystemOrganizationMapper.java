package com.grandeflorum.system.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.system.domain.SystemOrganization;

import java.util.List;
import java.util.Map;


public interface SystemOrganizationMapper extends MyMapper<SystemOrganization> {

    List<SystemOrganization> getOrganizationList(Map<String, Object> map);
}