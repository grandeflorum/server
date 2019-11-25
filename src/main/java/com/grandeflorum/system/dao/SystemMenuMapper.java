package com.grandeflorum.system.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.system.domain.SystemMenu;

import java.util.List;

public interface SystemMenuMapper extends MyMapper<SystemMenu> {

    List<SystemMenu> getUserMenu(String userId);

    List<SystemMenu> getUserMenuByParentId(List<String> list);

}