package com.grandeflorum.contract.controller;

import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.contract.service.AuditNewService;
import com.grandeflorum.project.domain.WFAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by 13260 on 2020/1/12.
 */
@RestController
@RequestMapping("AuditNew")
public class AuditNewController {

    @Autowired
    private AuditNewService auditNewService = null;

    @GetMapping("/getAuditNewInfo")
    public ResponseBo getAuditNewInfo(String id,int type){
        return auditNewService.getAuditNewInfo(id,type);
    }
}
