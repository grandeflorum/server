package com.grandeflorum.system.service.impl;

import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.system.dao.SystemMenuMapper;
import com.grandeflorum.system.domain.SystemMenu;
import com.grandeflorum.system.service.SystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by 13260 on 2019/11/1.
 */
@Service("SystemMenuService")
public class SystemMenuServiceImpl extends BaseService<SystemMenu> implements SystemMenuService {

    @Autowired
    SystemMenuMapper systemMenuMapper;

    @Override
    public int addSystemMenu(SystemMenu systemMenu) {
        systemMenu.setId(GuidHelper.getGuid());
        return systemMenuMapper.insert(systemMenu);
    }
}
