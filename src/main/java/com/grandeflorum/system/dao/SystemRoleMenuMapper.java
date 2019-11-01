package com.grandeflorum.system.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.system.domain.SystemRoleMenu;

public interface SystemRoleMenuMapper extends MyMapper<SystemRoleMenu> {

    void deleteRoleMenusByRoleId(String roleId);
}