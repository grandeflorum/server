package com.grandeflorum.attachment.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "FILE_INFO")
public class FileInfo {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "REF_ID")
    private String refId;

    @Column(name = "FILE_TYPE")
    private String fileType;

    @Column(name = "SERVER_FILE_NAME")
    private String serverFileName;

    @Column(name = "CLIENT_FILE_NAME")
    private String clientFileName;

    @Column(name = "SERVER_PATH")
    private String serverPath;

    @Column(name = "UPLOAD_USER_ID")
    private String uploadUserId;

    @Column(name = "UPLOAD_DATE")
    private Date uploadDate;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * @return REF_ID
     */
    public String getRefId() {
        return refId;
    }

    /**
     * @param refId
     */
    public void setRefId(String refId) {
        this.refId = refId == null ? null : refId.trim();
    }

    /**
     * @return FILE_TYPE
     */
    public String getFileType() {
        return fileType;
    }

    /**
     * @param fileType
     */
    public void setFileType(String fileType) {
        this.fileType = fileType == null ? null : fileType.trim();
    }

    /**
     * @return SERVER_FILE_NAME
     */
    public String getServerFileName() {
        return serverFileName;
    }

    /**
     * @param serverFileName
     */
    public void setServerFileName(String serverFileName) {
        this.serverFileName = serverFileName == null ? null : serverFileName.trim();
    }

    /**
     * @return CLIENT_FILE_NAME
     */
    public String getClientFileName() {
        return clientFileName;
    }

    /**
     * @param clientFileName
     */
    public void setClientFileName(String clientFileName) {
        this.clientFileName = clientFileName == null ? null : clientFileName.trim();
    }

    /**
     * @return SERVER_PATH
     */
    public String getServerPath() {
        return serverPath;
    }

    /**
     * @param serverPath
     */
    public void setServerPath(String serverPath) {
        this.serverPath = serverPath == null ? null : serverPath.trim();
    }

    /**
     * @return UPLOAD_USER_ID
     */
    public String getUploadUserId() {
        return uploadUserId;
    }

    /**
     * @param uploadUserId
     */
    public void setUploadUserId(String uploadUserId) {
        this.uploadUserId = uploadUserId == null ? null : uploadUserId.trim();
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
}