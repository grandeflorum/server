package com.grandeflorum.system.service;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.IService;
import com.grandeflorum.system.domain.SystemRole;

/**
 * Created by 13260 on 2019/11/1.
 */
public interface SystemRoleService extends IService<SystemRole> {

    int addRole(SystemRole roleWithMenu);

    int modifyRole(SystemRole roleWithMenu);

    int deleteRoleById(String id);

    SystemRole getRoleById(String id);

    ResponseBo getRoleList(Page page);

}
