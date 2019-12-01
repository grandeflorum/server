package com.grandeflorum.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.common.cache.EHCacheUtils;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.common.util.StrUtil;
import com.grandeflorum.practitioner.domain.Company;
import com.grandeflorum.practitioner.service.CompanyService;
import com.grandeflorum.system.dao.SystemOrganizationMapper;
import com.grandeflorum.system.dao.SystemRoleMapper;
import com.grandeflorum.system.dao.SystemUserMapper;
import com.grandeflorum.system.dao.SystemUserRoleMapper;
import com.grandeflorum.system.domain.SystemUser;
import com.grandeflorum.system.domain.UserCompany;
import com.grandeflorum.system.domain.SystemUserRole;
import com.grandeflorum.system.service.SystemUserRoleService;
import com.grandeflorum.system.service.SystemUserService;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    CompanyService companyService;

    @Autowired
    private CacheManager cacheManager;
    SystemUserRoleMapper systemUserRoleMapper;

    @Autowired
    SystemRoleMapper systemRoleMapper;

    @Override
    public ResponseBo vaildCard(String id,String card){
        Map<String,Object> map = new HashMap<>();

        if(!StrUtil.isNullOrEmpty(id)){
            map.put("id",id);
        }
        if(!StrUtil.isNullOrEmpty(card)){
            map.put("card",card);
        }else{
            return ResponseBo.error("请输入身份证号");
        }


        int res = userMapper.vaildCard(map);

        if(res>0){
            return ResponseBo.error("身份证号重复");
        }

        return ResponseBo.ok();
    }

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
    public ResponseBo insertRoleManage(SystemUser user,int type) {

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

            SystemUserRole sut = new SystemUserRole();
            sut.setId(GuidHelper.getGuid());
            sut.setUserId(user.getId());

            String roleId = systemRoleMapper.getRoleIdByName(type);
            sut.setRoleId(roleId);
            //插入权限
            systemUserRoleMapper.insert(sut);
        }else{

            //判断是否重复
            if(user1!=null&&!user.getId().equals(user1.getId())) {
                return ResponseBo.error("账号重复");
            }
            userMapper.updateUserRoleManage(user);
        }
        return ResponseBo.ok();
    }

    @Override
    @Transactional
    public ResponseBo insertUserCompany(UserCompany userCompany){
        Company company = new Company();

        company.setQymc(userCompany.getQymc());
        company.setShxydm(userCompany.getShxydm());
        company.setYyzz(userCompany.getYyzz());
        company.setCompanyType(Short.parseShort(userCompany.getCompanyType()));

        company.setQyfr(userCompany.getRealname());
        company.setZjh(userCompany.getCard());
        company.setDzyx(userCompany.getEmail());
        company.setPhone(userCompany.getMobile());

        ResponseBo bo = companyService.SaveOrUpdateCompany(company);

        if(bo.get("msg").equals("repeat")){
            return ResponseBo.error("企业名称重复");
        }

        userCompany.setIsVaild(2);

        return insertRoleManage(userCompany,Integer.parseInt(userCompany.getCompanyType()));
    }


    @Override
    public Map<String,Object> getSelectInfo(){
        Map<String,Object> map = new HashMap<>();

        map.put("needFilter",false);

        SystemUser user = EHCacheUtils.getCurrentUser(cacheManager);

        List<String> roles = user.getRoles();

        if(roles.contains("管理员")||roles.contains("录入员")||roles.contains("审核员")){
            map.put("needFilter",false);
        }else if(roles.contains("开发企业")||roles.contains("经济公司")){
            map.put("needFilter",true);
        }

        if(StrUtil.isNullOrEmpty(user.getCard())){
            map.put("companyList",new ArrayList<String>());
        }else{
            List<String> CompanyList = userMapper.getCompanyIdByCard(user.getCard());

            if(CompanyList!=null&&CompanyList.size()>0){
                map.put("companyList",CompanyList);
            }else{
                map.put("companyList",new ArrayList<String>());
            }
        }
        return map;
    }

}
