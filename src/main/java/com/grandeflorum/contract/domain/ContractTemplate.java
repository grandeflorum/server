package com.grandeflorum.contract.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "CONTRACTTEMPLATE")
public class ContractTemplate {

    @Id
    @Column(name = "ID")
    public String id;

    /**
     * 模板名称
     */
    @Column(name = "NAME")
    public String name;

    /**
     * 模板内容
     */
    @Column(name = "CONTENT")
    public String content;

    /**
     * type:1商品房合同模板，2存量合同模板
     */
    @Column(name = "TYPE")
    public Integer type;

    /**
     * 外键id
     */
    @Column(name = "REF_ID")
    public String refId;

    /**
     * 上传时间
     */
    @Column(name = "UPLOAD_DATE")
    public Date uploadDate;


    public ContractTemplateHistory contractTemplateHistory;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public ContractTemplateHistory getContractTemplateHistory() {
        return contractTemplateHistory;
    }

    public void setContractTemplateHistory(ContractTemplateHistory contractTemplateHistory) {
        this.contractTemplateHistory = contractTemplateHistory;
    }
}
