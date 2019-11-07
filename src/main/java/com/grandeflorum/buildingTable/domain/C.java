package com.grandeflorum.buildingTable.domain;

import javax.persistence.Column;
import java.util.List;

public class C {

    @Column(name="ID")
    private String id;

    /**
     * 实际曾
     */
    @Column(name="SJC")
    private int sjc;

    /**
     * 层号
     */
    @Column(name="CH")
    private int ch;

    /**
     * 是否区分单元
     */
    @Column(name="SFQFDY")
    private String sfqfdy;

    /**
     * 最大数
     */
    private int count;


    /**
     * 户集合
     */
    private List<H> hList;

    public List<H> gethList() {
        return hList;
    }

    public void sethList(List<H> hList) {
        this.hList = hList;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getSfqfdy() {
        return sfqfdy;
    }

    public void setSfqfdy(String sfqfdy) {
        this.sfqfdy = sfqfdy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSjc() {
        return sjc;
    }

    public void setSjc(int sjc) {
        this.sjc = sjc;
    }

    public int getCh() {
        return ch;
    }

    public void setCh(int ch) {
        this.ch = ch;
    }
}
