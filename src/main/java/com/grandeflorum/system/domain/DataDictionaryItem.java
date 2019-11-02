package com.grandeflorum.system.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "DICTIONARY")
public class DataDictionaryItem {
    @Id
    @Column(name = "ID")
    public String id;

    /**
     * 标识
     */
    @Column(name = "CODE")
    public Integer code;

    /**
     * 名称
     */
    @Column(name = "NAME")
    public String name;

    /**
     * 父标识
     */
    @Column(name = "PARENTID")
    public String parentId;

    /**
     * 排序字段
     */
    @Column(name = "RANK")
    public Integer rank;

    /**
     * 类型
     */
    @Column(name = "TYPE")
    public String type;

    /**
     * 备注
     */
    @Column(name = "REMARK")
    public String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
