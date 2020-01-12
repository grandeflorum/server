package com.grandeflorum.contract.service;

import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.project.domain.WFAudit;

/**
 * Created by 13260 on 2020/1/12.
 */
public interface AuditNewService {

    ResponseBo getAuditNewInfo(String id, int type);
}
