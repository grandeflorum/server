package com.grandeflorum.project.domain;

import java.beans.Transient;
import java.util.List;

public class AuditParam {

    /**
     * 项目projectid
     */
    public List<String> ids;

    /**
     * 审核信息
     */
    public WFAudit wfAudit;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public WFAudit getWfAudit() {
        return wfAudit;
    }

    public void setWfAudit(WFAudit wfAudit) {
        this.wfAudit = wfAudit;
    }
}
