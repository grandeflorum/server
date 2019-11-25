package com.grandeflorum.system.service.impl;

import com.grandeflorum.common.cache.EHCacheUtils;
import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.system.dao.SystemMenuMapper;
import com.grandeflorum.system.domain.SystemMenu;
import com.grandeflorum.system.domain.SystemUser;
import com.grandeflorum.system.service.SystemMenuService;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by 13260 on 2019/11/1.
 */
@Service("SystemMenuService")
public class SystemMenuServiceImpl extends BaseService<SystemMenu> implements SystemMenuService {

    @Autowired
    SystemMenuMapper systemMenuMapper;

    @Autowired
    private CacheManager cacheManager;

    @Override
    public int addSystemMenu(SystemMenu systemMenu) {
        systemMenu.setId(GuidHelper.getGuid());
        return systemMenuMapper.insert(systemMenu);
    }


    @Override
    public List<SystemMenu> getUserMenu() {
        SystemUser user = EHCacheUtils.getCurrentUser(cacheManager);

        if (user != null) {
            List<SystemMenu> list = systemMenuMapper.getUserMenu(user.getId());

            if (list != null) {

                List<String> parentId = list.stream().filter(x -> !x.getParentId().equals("0") && !list.stream().anyMatch(y -> y.getId().equals(x.getParentId()))).map(SystemMenu::getParentId).collect(Collectors.toList());

                if (parentId != null && parentId.size() > 0) {
                    List<SystemMenu> PList = systemMenuMapper.getUserMenuByParentId(parentId);

                    if(PList!=null&&PList.size()>0){
                        list.addAll(PList);

                        list.sort((a, b) -> a.getMenuOrder() - b.getMenuOrder());
                    }
                }

                return list;
            }
        }

        return null;
    }


}
