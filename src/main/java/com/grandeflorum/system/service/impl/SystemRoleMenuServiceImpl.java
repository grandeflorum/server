package com.grandeflorum.system.service.impl;

import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.system.dao.SystemRoleMenuMapper;
import com.grandeflorum.system.domain.SystemMenu;
import com.grandeflorum.system.domain.SystemRole;
import com.grandeflorum.system.domain.SystemRoleMenu;
import com.grandeflorum.system.service.SystemRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 13260 on 2019/11/1.
 */
@Service("SystemRoleMenuService")
public class SystemRoleMenuServiceImpl extends BaseService<SystemRoleMenu> implements SystemRoleMenuService {

    @Autowired
    SystemRoleMenuMapper roleMenuMapper;

    @Override
    public void insetRoleMenuByRole(SystemRole role) {
        if ((role.getRoleMenuList() != null) && (role.getRoleMenuList().size() > 0)) {
            for(SystemMenu systemMenu:role.getRoleMenuList()){
                SystemRoleMenu roleMenu=new SystemRoleMenu();
                roleMenu.setId(GuidHelper.getGuid());
                roleMenu.setRoleId(role.getId());
                roleMenu.setMenuId(systemMenu.getId());
                roleMenuMapper.insert(roleMenu);
            }
        }
    }

    @Override
    public void deleteRoleMenusByRoleId(String roleId) {
        roleMenuMapper.deleteRoleMenusByRoleId(roleId);
    }
}
