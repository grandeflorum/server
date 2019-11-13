package com.grandeflorum.project.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.project.domain.WFAudit;

import java.util.List;
import java.util.Map;

public interface WFAuditMapper extends MyMapper<WFAudit> {
    List<WFAudit> getWFAuditList(Map<String, Object> map);
}