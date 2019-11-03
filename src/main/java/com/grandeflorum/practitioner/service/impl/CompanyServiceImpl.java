package com.grandeflorum.practitioner.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.common.util.StrUtil;
import com.grandeflorum.practitioner.dao.CompanyMapper;
import com.grandeflorum.practitioner.domain.Company;
import com.grandeflorum.practitioner.service.CompanyService;
import com.grandeflorum.project.dao.WFAuditMapper;
import com.grandeflorum.project.domain.AuditParam;
import com.grandeflorum.project.domain.WFAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 13260 on 2019/11/2.
 */
@Service("CompanyService")
public class CompanyServiceImpl extends BaseService<Company> implements CompanyService {

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    WFAuditMapper wFAuditMapper;

    @Override
    public ResponseBo SaveOrUpdateCompany(Company company){


        company.setSysUpdDate(new Date());

        //新增
        if(StrUtil.isNullOrEmpty(company.getId())){
            company.setId(GuidHelper.getGuid());
            company.setSysDate(new Date());

            company.setAuditType((short)0);
            companyMapper.insert(company);
            return ResponseBo.ok(company.getId());

        }

        companyMapper.updateByPrimaryKey(company);
        return ResponseBo.ok(company.getId());
    }

    /**
     * 获取企业列表
     * @param page
     * @return
     */
    @Override
    public ResponseBo getCompanyList(Page page){

        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
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
    public ResponseBo btachAuditCompany(AuditParam auditParam){

        WFAudit wf = auditParam.getWfAudit();

        for (String id:auditParam.getIds() ) {

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
            Map<String,Object> map =new HashMap<>();
            map.put("id",id);
            if(wf.getShjg()==1){
                map.put("type",2);
            }else{
                map.put("type",3);
            }
            companyMapper.auditCompanyById(map);
        }

        return ResponseBo.ok();

    }


}
