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

    /**
     * 判断审核类型（0：普通审核）
     */
    public int type;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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
