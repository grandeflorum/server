package com.grandeflorum.system.domain;

public class UserCompany extends SystemUser {

    /**
     * 企业类型
     */
    private String companyType;

    /**
     * 企业名称
     */
    private String qymc;

    /**
     * 营业执照
     */
    private String yyzz;

    /**
     * 社会信用代码
     */
    private String shxydm;


    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getQymc() {
        return qymc;
    }

    public void setQymc(String qymc) {
        this.qymc = qymc;
    }

    public String getYyzz() {
        return yyzz;
    }

    public void setYyzz(String yyzz) {
        this.yyzz = yyzz;
    }

    public String getShxydm() {
        return shxydm;
    }

    public void setShxydm(String shxydm) {
        this.shxydm = shxydm;
    }
}
