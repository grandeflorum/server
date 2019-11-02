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


}
