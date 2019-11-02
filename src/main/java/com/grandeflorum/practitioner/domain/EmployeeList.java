package com.grandeflorum.practitioner.domain;

import javax.persistence.Column;

public class EmployeeList extends Employee {

    @Column(name = "AUDIT_TYPE")
    private int auditType;

    @Column(name = "genderName")
    private String genderName;

    @Column(name = "educationName")
    private String educationName;

    @Column(name="fwjgName")
    private String fwjgName;


    public String getFwjgName() {
        return fwjgName;
    }

    public void setFwjgName(String fwjgName) {
        this.fwjgName = fwjgName;
    }

    public int getAuditType() {
        return auditType;
    }

    public void setAuditType(int auditType) {
        this.auditType = auditType;
    }

    public String getGenderName() {
        return genderName;
    }

    public void setGenderName(String genderName) {
        this.genderName = genderName;
    }

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }
}
