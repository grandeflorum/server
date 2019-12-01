package com.grandeflorum.system.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.system.domain.SystemUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SystemUserMapper extends MyMapper<SystemUser> {

    SystemUser login(Map<String, String> map);

    SystemUser getUserDetailByUserId(String userId);

    List<String> getPermissionByUserId(@Param("id") String id);

    SystemUser findByName(String username);

    String getUserNameById(String userId);

    SystemUser findUserByUsername(String username);

    List<SystemUser> getUserList(Map<String, Object> map);

    int changePassword(SystemUser user);

    List<String> getRoleByUserId(String id);


    void updateUserRoleManage(SystemUser user);

    int vaildCard(Map<String,Object> map);


    List<String> getCompanyIdByCard(String zjh);

}