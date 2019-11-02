package com.grandeflorum.practitioner.controller;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.practitioner.domain.Company;
import com.grandeflorum.practitioner.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/11/2.
 */
@RestController
@RequestMapping("Company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping("/saveOrUpdateCompany")
    public ResponseBo saveOrUpdateCompany(@RequestBody Company company) {
        return companyService.SaveOrUpdateCompany(company);
    }

    @PostMapping("/getCompanyList")
    public ResponseBo getCompanyList(@RequestBody Page page){
        return companyService.getCompanyList(page);
    }

    @GetMapping("/getCompanyById")
    public ResponseBo getCompanyById(String id){
        return companyService.getCompanyById(id);
    }

    @PostMapping("/deleteCompanyByIds")
    public ResponseBo deleteCompanyByIds(@RequestBody List<String> ids) {
        return companyService.deleteCompanyByIds(ids);
    }


    @GetMapping("/auditCompanyById")
    public ResponseBo auditCompanyById(String id,int type) {
        return companyService.auditCompanyById(id, type);
    }
}
