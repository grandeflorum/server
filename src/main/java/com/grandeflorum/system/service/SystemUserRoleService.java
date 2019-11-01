package com.grandeflorum.system.service;

import com.grandeflorum.common.service.IService;
import com.grandeflorum.system.domain.SystemUser;
import com.grandeflorum.system.domain.SystemUserRole;

/**
 * Created by 13260 on 2019/11/1.
 */
public interface SystemUserRoleService extends IService<SystemUserRole>{

    //根据角色名称删除用户角色关联信息
    void deleteUserRoleByRoleId(String roleId);

    //根据用户的信息插入用户的角色关联关系
    void insertUserRoleByRole(SystemUser userWithRole);


    void deleteUserRoleByUserId(String userId);
}
