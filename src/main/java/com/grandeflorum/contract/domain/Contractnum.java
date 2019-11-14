package com.grandeflorum.contract.domain;

import javax.persistence.*;

public class Contractnum {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "TIME")
    private String time;

    @Column(name = "MAXNUM")
    private Short maxnum;

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
        this.id = id;
    }

    /**
     * @return TYPE
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return TIME
     */
    public String getTime() {
        return time;
    }

    /**
     * @param time
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @return MAXNUM
     */
    public Short getMaxnum() {
        return maxnum;
    }

    /**
     * @param maxnum
     */
    public void setMaxnum(Short maxnum) {
        this.maxnum = maxnum;
    }
}