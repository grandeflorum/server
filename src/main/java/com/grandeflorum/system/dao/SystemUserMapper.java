package com.grandeflorum.system.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.system.domain.SystemUser;

import java.util.List;
import java.util.Map;

public interface SystemUserMapper extends MyMapper<SystemUser> {

    SystemUser login(Map<String, String> map);

    SystemUser getUserDetailByUserId(String userId);

    List<String> getPermissionByUserId(String userId);

    SystemUser findByName(String username);

    String getUserNameById(String userId);

    SystemUser findUserByUsername(String username);

    List<SystemUser> getUserList(Map<String, Object> map);

    int changePassword(SystemUser user);
}