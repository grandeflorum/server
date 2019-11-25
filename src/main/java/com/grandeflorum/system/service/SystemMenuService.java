package com.grandeflorum.system.service;

import com.grandeflorum.common.service.IService;
import com.grandeflorum.system.domain.SystemMenu;

import java.util.List;

/**
 * Created by 13260 on 2019/11/1.
 */
public interface SystemMenuService extends IService<SystemMenu> {

    int addSystemMenu(SystemMenu systemMenu);


    List<SystemMenu> getUserMenu();

}
