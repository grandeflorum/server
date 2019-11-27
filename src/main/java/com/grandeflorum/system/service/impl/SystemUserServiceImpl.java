package com.grandeflorum.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.common.util.StrUtil;
import com.grandeflorum.system.dao.SystemOrganizationMapper;
import com.grandeflorum.system.dao.SystemUserMapper;
import com.grandeflorum.system.domain.SystemUser;
import com.grandeflorum.system.service.SystemUserRoleService;
import com.grandeflorum.system.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 13260 on 2019/11/1.
 */
@Service("SystemUserService")
public class SystemUserServiceImpl extends BaseService<SystemUser> implements SystemUserService {

    @Autowired
    private SystemUserMapper userMapper;

    @Autowired
    private SystemUserRoleService userRoleService;

    @Autowired
    SystemOrganizationMapper systemOrganizationMapper;

    @Override
    public int addUser(SystemUser user) {
        user.setId(GuidHelper.getGuid());
        try {
            user.setIsVaild(1);
            userMapper.insert(user);
            userRoleService.insertUserRoleByRole(user);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int modifyUser(SystemUser userWithRole) {
        try {
            userMapper.updateByPrimaryKey(userWithRole);
            userRoleService.deleteUserRoleByUserId(userWithRole.getId());
            userRoleService.insertUserRoleByRole(userWithRole);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public int deleteUserById(String id) {
        try {
            userMapper.deleteByPrimaryKey(id);
            userRoleService.deleteUserRoleByUserId(id);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    public SystemUser getUserWithRoleByUserId(String userId) {
        return userMapper.getUserDetailByUserId(userId);
    }

    @Override
    public List<String> getAllPermissionByUserId(String id) {
        List<String> result = userMapper.getPermissionByUserId(id);
        return result;
    }

    @Override
    public List<String> getRoleByUserId(String id){
        List<String> result = userMapper.getRoleByUserId(id);
        return result;
    }

    @Override
    public ResponseBo getUserList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<SystemUser> list = userMapper.getUserList(map);

        PageInfo<SystemUser> pageInfo = new PageInfo<SystemUser>(list);

        PagingEntity<SystemUser> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public SystemUser login(Map<String, String> map) {

        return userMapper.login(map);

    }

    @Override
    public SystemUser findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public int changePassword(SystemUser user) {
        return userMapper.changePassword(user);
    }

    @Override
    public ResponseBo insertRoleManage(SystemUser user) {

        SystemUser user1 = findUserByUsername(user.getUsername());
        if(StrUtil.isNullOrEmpty(user.getId())){

            //判断是否重复
            if(user1!=null){
                return ResponseBo.error("账号重复");
            }

            user.setId(GuidHelper.getGuid());
            String orgId =  systemOrganizationMapper.getTopOrganization();
            user.setOrgId(orgId);
            userMapper.insert(user);
        }else{

            //判断是否重复
            if(user1!=null&&!user.getId().equals(user1.getId())) {
                return ResponseBo.error("账号重复");
            }
            userMapper.updateUserRoleManage(user);
        }


        return ResponseBo.ok();
    }
}
