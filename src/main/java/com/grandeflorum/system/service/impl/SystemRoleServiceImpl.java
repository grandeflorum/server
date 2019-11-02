package com.grandeflorum.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.system.dao.SystemRoleMapper;
import com.grandeflorum.system.domain.SystemRole;
import com.grandeflorum.system.service.SystemRoleMenuService;
import com.grandeflorum.system.service.SystemRoleService;
import com.grandeflorum.system.service.SystemUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * Created by 13260 on 2019/11/1.
 */
@Service("SystemRoleService")
public class SystemRoleServiceImpl extends BaseService<SystemRole> implements SystemRoleService {

    @Autowired
    SystemRoleMapper roleMapper;

    @Autowired
    SystemRoleMenuService roleMenuServie;

    @Autowired
    SystemUserRoleService systemUserRoleService;

    @Override
    @Transactional
    public int addRole(SystemRole roleWithMenu) {
        String roleId = GuidHelper.getGuid();
        roleWithMenu.setId(roleId);
        try {
            roleMapper.insert(roleWithMenu);
            roleMenuServie.insetRoleMenuByRole(roleWithMenu);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int modifyRole(SystemRole roleWithMenu) {
        try {
            roleMapper.updateByPrimaryKey(roleWithMenu);
            roleMenuServie.deleteRoleMenusByRoleId(roleWithMenu.getId());
            roleMenuServie.insetRoleMenuByRole(roleWithMenu);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public ResponseBo getRoleList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<SystemRole> list = roleMapper.getRoleList(map);

        PageInfo<SystemRole> pageInfo = new PageInfo<SystemRole>(list);

        PagingEntity<SystemRole> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public int deleteRoleById(String id) {
        try {
            roleMapper.deleteByPrimaryKey(id);
            roleMenuServie.deleteRoleMenusByRoleId(id);
            systemUserRoleService.deleteUserRoleByRoleId(id);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public SystemRole getRoleById(String id) {
        SystemRole result = roleMapper.getRoleById(id);
        return result;
    }
}
