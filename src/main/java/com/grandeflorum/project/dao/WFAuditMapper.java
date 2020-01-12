package com.grandeflorum.project.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.project.domain.WFAudit;

import java.util.List;
import java.util.Map;

public interface WFAuditMapper extends MyMapper<WFAudit> {
    List<WFAudit> getWFAuditList(Map<String, Object> map);

    WFAudit getWFAuditInfo(Map<String, Object> map);

    List<WFAudit> getWFAuditOtherInfo(Map<String, Object> map);

    void updateWfActive(Map<String, Object> map);

    int getOtherOrgPassCount(Map<String, Object> map);
}