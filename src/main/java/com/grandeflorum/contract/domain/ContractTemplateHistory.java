package com.grandeflorum.contract.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "CONTRACTTEMPLATEHISTORY")
public class ContractTemplateHistory {

    @Id
    @Column(name = "ID")
    public String id;

    /**
     * 修改原因
     */
    @Column(name = "XGYY")
    public String xgyy;
    /**
     * 修改内容
     */
    @Column(name = "XGNR")
    public String xgnr;
    /**
     * 修改人
     */
    @Column(name = "XGR")
    public String xgr;
    /**
     * 内容
     */
    @Column(name = "CONTENT")
    public String content;
    /**
     * 修改时间
     */
    @Column(name = "UPLOAD_DATE")
    public Date uploadDate;
    /**
     * 合同模板id
     */
    @Column(name = "CONTRACTTEMPLATEID")
    public String contractTemplateId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getXgyy() {
        return xgyy;
    }

    public void setXgyy(String xgyy) {
        this.xgyy = xgyy;
    }

    public String getXgnr() {
        return xgnr;
    }

    public void setXgnr(String xgnr) {
        this.xgnr = xgnr;
    }

    public String getXgr() {
        return xgr;
    }

    public void setXgr(String xgr) {
        this.xgr = xgr;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getContractTemplateId() {
        return contractTemplateId;
    }

    public void setContractTemplateId(String contractTemplateId) {
        this.contractTemplateId = contractTemplateId;
    }
}
