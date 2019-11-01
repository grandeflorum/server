package com.grandeflorum.system.service;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.IService;
import com.grandeflorum.system.domain.SystemUser;

import java.util.List;
import java.util.Map;

/**
 * Created by 13260 on 2019/11/1.
 */
public interface SystemUserService extends IService<SystemUser> {

    int addUser(SystemUser user);

    int modifyUser(SystemUser userWithRole);

    int deleteUserById(String id);

    SystemUser getUserWithRoleByUserId(String id);

    List<String> getAllPermissionByUserId(String id);

    SystemUser login(Map<String, String> map);

    SystemUser findUserByUsername(String username);

    ResponseBo getUserList(Page page);

    int changePassword(SystemUser user);

}
