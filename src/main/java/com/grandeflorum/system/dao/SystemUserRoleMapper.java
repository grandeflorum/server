package com.grandeflorum.system.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.system.domain.SystemUserRole;

public interface SystemUserRoleMapper extends MyMapper<SystemUserRole> {

    int deleteUserRoleByRoleId(String roleId);

    int deleteUserRoleByUserId(String userId);
}