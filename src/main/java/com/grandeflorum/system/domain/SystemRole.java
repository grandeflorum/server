package com.grandeflorum.system.domain;

import javax.persistence.*;
import java.util.List;

@Table(name = "SYSTEM_ROLE")
public class SystemRole {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME")
    private String name;

    private List<SystemMenu> roleMenuList;

    public List<SystemMenu> getRoleMenuList() {
        return roleMenuList;
    }

    public void setRoleMenuList(List<SystemMenu> roleMenuList) {
        this.roleMenuList = roleMenuList;
    }

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
}