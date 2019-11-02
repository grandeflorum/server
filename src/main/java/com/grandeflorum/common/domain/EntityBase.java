package com.grandeflorum.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Transient;

public class EntityBase {
    @JsonIgnore
    @Transient
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
