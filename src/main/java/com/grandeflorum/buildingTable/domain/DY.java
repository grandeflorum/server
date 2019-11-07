package com.grandeflorum.buildingTable.domain;

public class DY {

    /**
     * 单元名称
     */
    private String name;


    /**
     * 合并行
     */
    private int rowSpan;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRowSpan() {
        return rowSpan;
    }

    public void setRowSpan(int rowSpan) {
        this.rowSpan = rowSpan;
    }
}
