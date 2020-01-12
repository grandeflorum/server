package com.grandeflorum.contract.domain;

import javax.persistence.*;
import java.util.Date;

@Table(name = "CONTRACT_EDIT")
public class ContractEdit {

    @Id
    @Column(name = "TRADE_ID")
    private String tradeId;

    @Column(name = "UPLOAD_DATE")
    private Date uploadDate;

    @Column(name = "CONTENT")
    private String content;



    /**
     * @return TRADE_ID
     */
    public String getTradeId() {
        return tradeId;
    }

    /**
     * @param tradeId
     */
    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    /**
     * @return UPLOAD_DATE
     */
    public Date getUploadDate() {
        return uploadDate;
    }

    /**
     * @param uploadDate
     */
    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    /**
     * @return CONTENT
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}