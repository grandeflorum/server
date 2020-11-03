package com.grandeflorum.practitioner.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.attachment.service.FileInfoService;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.common.util.StrUtil;
import com.grandeflorum.practitioner.dao.AssociatedCompanyMapper;
import com.grandeflorum.practitioner.dao.CompanyMapper;
import com.grandeflorum.practitioner.domain.AssociatedCompany;
import com.grandeflorum.practitioner.domain.Company;
import com.grandeflorum.practitioner.service.CompanyService;
import com.grandeflorum.project.dao.WFAuditMapper;
import com.grandeflorum.project.domain.AuditParam;
import com.grandeflorum.project.domain.WFAudit;
import com.grandeflorum.system.domain.SystemUser;
import com.grandeflorum.system.domain.SystemUserRole;
import com.grandeflorum.system.service.SystemUserRoleService;
import com.grandeflorum.system.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by 13260 on 2019/11/2.
 */
@Service("CompanyService")
public class CompanyServiceImpl extends BaseService<Company> implements CompanyService {

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    WFAuditMapper wFAuditMapper;

    @Autowired
    FileInfoService fileInfoService;

    @Autowired
    AssociatedCompanyMapper associatedCompanyMapper;

    @Autowired
    SystemUserService systemUserService;

    @Autowired
    private SystemUserRoleService userRoleService;


    @Override
    public ResponseBo SaveOrUpdateCompany(Company company,int type){


        company.setSysUpdDate(new Date());

        Map<String,Object> map = new HashMap<>();
        map.put("qymc",company.getQymc());
        map.put("id",company.getId());
        int count = companyMapper.checkCompanyName(map);

        if(count>0){
            return ResponseBo.ok("repeat");
        }

        //新增
        if(StrUtil.isNullOrEmpty(company.getId())){
            company.setId(GuidHelper.getGuid());
            company.setSysDate(new Date());

            company.setAuditType((short)0);
            companyMapper.insert(company);

            fileInfoService.updateFileInfoByIds(company.getFileInfoList(),company.getId());

            if(type==1){
                SystemUser user = systemUserService.findUserByCard(company.getZjh());

                if(user==null){

                    user = new SystemUser();

                    user.setUsername(company.getZjh());
//                    user.setPassword(!StrUtil.isNullOrEmpty(company.getZjh())&&company.getZjh().length()>=6?company.getZjh().substring(company.getZjh().length() - 6):"abc123");
                    user.setPassword("123456");
                    user.setRealname(company.getQyfr());
                    user.setEmail(company.getDzyx());
                    user.setMobile(company.getPhone());
                    user.setIsVaild(1);
                    user.setIsGrant(0);
                    user.setCard(company.getZjh());

                    ResponseBo bo = systemUserService.insertRoleManage(user,(int)company.getCompanyType());
                    if(!bo.get("code").toString().equals("200")){
                        return bo;
                    }
                }
            }

            return ResponseBo.ok(company);

        }else{
            if(type==1){
                Company c = companyMapper.selectByPrimaryKey(company.getId());

                SystemUser user = systemUserService.findUserByCard(c.getZjh());

                if(user!=null){
                    user.setRealname(company.getQyfr());
                    user.setEmail(company.getDzyx());
                    user.setMobile(company.getPhone());
                    user.setCard(company.getZjh());

                    systemUserService.updateUser(user);
                }
            }
        }

        companyMapper.updateByPrimaryKey(company);
        return ResponseBo.ok(company);
    }

    /**
     * 获取企业列表
     * @param page
     * @return
     */
    @Override
    public ResponseBo getCompanyList(Page page){

        Map<String, Object> map = page.getQueryParameter();


        //获取过滤条件
        systemUserService.getSelectInfo(map);



        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<Company> list = companyMapper.getCompanyList(map);

        PageInfo<Company> pageInfo = new PageInfo<Company>(list);

        PagingEntity<Company> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getCompanyById(String id){

        Company company =  companyMapper.selectByPrimaryKey(id);
        return ResponseBo.ok(company);
    }

    @Override
    public ResponseBo deleteCompanyByIds(List<String> ids){

        if ((ids != null) && (ids.size() > 0)) {
            for (String str:ids) {
                companyMapper.deleteByPrimaryKey(str);
            }
        }
        return ResponseBo.ok();
    }

    @Override
    public ResponseBo auditCompanyById(String id,int type){

        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("type",type);

        companyMapper.auditCompanyById(map);

        return ResponseBo.ok();
    }

    @Override
    public ResponseBo btachAuditCompany(AuditParam auditParam) {

        WFAudit wf = auditParam.getWfAudit();

        for (String id : auditParam.getIds()) {

            WFAudit wfAudit = new WFAudit();
            wfAudit.setId(GuidHelper.getGuid());

            wfAudit.setShjg(wf.getShjg());
            wfAudit.setShry(wf.getShry());
            wfAudit.setShrq(wf.getShrq());
            wfAudit.setBz(wf.getBz());

            wfAudit.setProjectid(id);
            wfAudit.setSysDate(new Date());
            wfAudit.setSysUpdDate(new Date());

            wFAuditMapper.insert(wfAudit);

            //更新企业审核状态
            Map<String, Object> map = new HashMap<>();
            map.put("id", id);

            if (auditParam.getType() == 0) {
                if (wf.getShjg() == 1) {
                    map.put("type", 2);
                } else {
                    map.put("type", 3);

                    UpdateRoles(id);
                }
            } else if (auditParam.getType() == 1) {
                if (wf.getShjg() == 1) {
                    map.put("type", 4);
                } else {
                    map.put("type", 3);

                    UpdateRoles(id);
                }
            }
            companyMapper.auditCompanyById(map);
        }

        return ResponseBo.ok();

    }

    private void UpdateRoles(String id){
        Company c= companyMapper.selectByPrimaryKey(id);

        SystemUser user = systemUserService.findUserByCard(c.getZjh());

        if(user!=null){
            userRoleService.deleteUserRoleByUserId(user.getId());

            systemUserService.SaveRoles(user.getId(),(int)c.getCompanyType());
        }
    }


    @Override
    public ResponseBo SaveOrUpdateAssociatedCompany(AssociatedCompany associatedCompany){
        associatedCompany.setSysDate(new Date());

        //新增
        if(StrUtil.isNullOrEmpty(associatedCompany.getId())){
            associatedCompany.setId(GuidHelper.getGuid());

            associatedCompanyMapper.insert(associatedCompany);

            return ResponseBo.ok(associatedCompany);
        }

        associatedCompanyMapper.updateByPrimaryKey(associatedCompany);

        return ResponseBo.ok(associatedCompany);
    }

    @Override
    public ResponseBo GetAssociatedCompany(String id,String module){
        Map<String,String> map = new HashMap<>();

        map.put("associatedId",id);
        map.put("module",module);

        AssociatedCompany result = associatedCompanyMapper.GetAssociatedCompany(map);

        return ResponseBo.ok(result);
    }


    @Override
    public ResponseBo DeleteAssociatedCompany(AssociatedCompany associatedCompany){
        associatedCompanyMapper.delete(associatedCompany);

        return ResponseBo.ok();
    }


}
