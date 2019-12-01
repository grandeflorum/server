package com.grandeflorum.practitioner.service;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.IService;
import com.grandeflorum.practitioner.domain.AssociatedCompany;
import com.grandeflorum.practitioner.domain.Company;
import com.grandeflorum.project.domain.AuditParam;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by 13260 on 2019/11/2.
 */
public interface CompanyService extends IService<Company>{

    /**
     * 保存
     * @param company
     * @return
     */
    ResponseBo SaveOrUpdateCompany(Company company);

    /**
     * 获取企业列表
     * @return
     */
    ResponseBo getCompanyList(Page page);

    /**
     * 获取企业详情
     * @param id
     * @return
     */
    ResponseBo getCompanyById(String id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    ResponseBo deleteCompanyByIds(List<String> ids);

    /**
     * 审核
     * @param id
     * @param type
     * @return
     */
    ResponseBo auditCompanyById(String id,int type);

    /**
     * 批量审核
     * @param auditParam
     * @return
     */
    ResponseBo btachAuditCompany(AuditParam auditParam);

    /**
     * 关联企业
     * @param associatedCompany
     * @return
     */
    ResponseBo SaveOrUpdateAssociatedCompany(AssociatedCompany associatedCompany);

    /**
     * 获取关联对象
     * @param id
     * @param module
     * @return
     */
    ResponseBo GetAssociatedCompany(String id,String module);


    /**
     * 删除关联关系
     * @param associatedCompany
     * @return
     */
    ResponseBo DeleteAssociatedCompany( AssociatedCompany associatedCompany);
}
