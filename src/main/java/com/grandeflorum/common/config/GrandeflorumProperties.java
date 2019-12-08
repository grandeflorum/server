package com.grandeflorum.common.config;

import com.grandeflorum.common.shiro.ShiroProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:liuhao
 * @Date:2019/4/29 09:46
 * @Description:
 **/
@Configuration
@ConfigurationProperties(prefix = "grandeflorum")
public class GrandeflorumProperties {

    public ShiroProperties getShiro() {
        return shiro;
    }

    public void setShiro(ShiroProperties shiro) {
        this.shiro = shiro;
    }

    private ShiroProperties shiro = new ShiroProperties();

    private String timeFormat = "yyyy-MM-dd HH:mm:ss";

    private boolean openAOPLog = true;

    private String uploadFolder;

    private String openoffice;

    private String qrCodePath;

    public String getUploadFolder() {
        return uploadFolder;
    }

    public void setUploadFolder(String uploadFolder) {
        this.uploadFolder = uploadFolder;
    }

    public String getTimeFormat() {
        return timeFormat;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public boolean isOpenAOPLog() {
        return openAOPLog;
    }

    public void setOpenAOPLog(boolean openAOPLog) {
        this.openAOPLog = openAOPLog;
    }

    public String getOpenoffice() {
        return openoffice;
    }

    public void setOpenoffice(String openoffice) {
        this.openoffice = openoffice;
    }

    public String getQrCodePath() {
        return qrCodePath;
    }

    public void setQrCodePath(String qrCodePath) {
        this.qrCodePath = qrCodePath;
    }
}
