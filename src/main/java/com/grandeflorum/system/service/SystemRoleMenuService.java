package com.grandeflorum.system.service;

import com.grandeflorum.common.service.IService;
import com.grandeflorum.system.domain.SystemRole;
import com.grandeflorum.system.domain.SystemRoleMenu;

/**
 * Created by 13260 on 2019/11/1.
 */
public interface SystemRoleMenuService extends IService<SystemRoleMenu> {

    void insetRoleMenuByRole(SystemRole role);

    void deleteRoleMenusByRoleId(String roleId);
}
