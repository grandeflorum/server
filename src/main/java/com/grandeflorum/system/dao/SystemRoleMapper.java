package com.grandeflorum.system.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.system.domain.SystemRole;
import com.grandeflorum.system.domain.SystemRoleMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SystemRoleMapper extends MyMapper<SystemRole> {

    List<SystemRole> findUserRole(String userName);

    List<SystemRole> findById(Long roleId);

    SystemRole getRoleById(String roleId);

    List<SystemRole> getRoleList(Map<String,Object> map);

    String getRoleIdByName(@Param("type") int type);
}