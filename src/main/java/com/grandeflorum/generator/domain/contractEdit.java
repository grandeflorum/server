package domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "CONTRACT_EDIT")
public class contractEdit {
    @Id
    @Column(name = "TRADE_ID")
    private Object tradeId;

    @Column(name = "UPLOAD_DATE")
    private Date uploadDate;

    @Column(name = "CONTENT")
    private String content;

    /**
     * @return TRADE_ID
     */
    public Object getTradeId() {
        return tradeId;
    }

    /**
     * @param tradeId
     */
    public void setTradeId(Object tradeId) {
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