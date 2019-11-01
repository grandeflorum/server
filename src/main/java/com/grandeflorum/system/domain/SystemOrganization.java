package com.grandeflorum.system.domain;

import javax.persistence.*;

@Table(name = "SYSTEM_ORGANIZATION")
public class SystemOrganization {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "PARENT_ID")
    private String parentId;

    @Column(name = "ORG_LEVER")
    private Integer orgLever;

    @Column(name = "ORG_LEVER_ORDER")
    private Integer orgLeverOrder;

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
     * @return CODE
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }

    /**
     * @return NAME
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * @return PARENT_ID
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * @return ORG_LEVER
     */
    public Integer getOrgLever() {
        return orgLever;
    }

    /**
     * @param orgLever
     */
    public void setOrgLever(Integer orgLever) {
        this.orgLever = orgLever;
    }

    /**
     * @return ORG_LEVER_ORDER
     */
    public Integer getOrgLeverOrder() {
        return orgLeverOrder;
    }

    /**
     * @param orgLeverOrder
     */
    public void setOrgLeverOrder(Integer orgLeverOrder) {
        this.orgLeverOrder = orgLeverOrder;
    }
}