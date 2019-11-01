package com.grandeflorum.system.service.impl;

import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.system.dao.SystemUserRoleMapper;
import com.grandeflorum.system.domain.SystemRole;
import com.grandeflorum.system.domain.SystemUser;
import com.grandeflorum.system.domain.SystemUserRole;
import com.grandeflorum.system.service.SystemUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by 13260 on 2019/11/1.
 */
@Service("SystemUserRoleService")
public class SystemUserRoleServiceImpl extends BaseService<SystemUserRole> implements SystemUserRoleService {

    @Autowired
    SystemUserRoleMapper userRoleMapper;

    @Override
    public void deleteUserRoleByRoleId(String roleId) {
        userRoleMapper.deleteUserRoleByRoleId(roleId);
    }

    @Override
    public void insertUserRoleByRole(SystemUser userWithRole) {
        if ((userWithRole.getRoleList() != null) && (userWithRole.getRoleList().size() > 0)) {
            for (SystemRole role : userWithRole.getRoleList()) {
                SystemUserRole userRole = new SystemUserRole();
                userRole.setId(GuidHelper.getGuid());
                userRole.setUserId(userWithRole.getId());
                userRole.setRoleId(role.getId());
                userRoleMapper.insert(userRole);
            }
        }
    }

    @Override
    public void deleteUserRoleByUserId(String userId) {
        userRoleMapper.deleteUserRoleByUserId(userId);
    }

}
