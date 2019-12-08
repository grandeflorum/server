package com.grandeflorum.statistic.domain;

import java.util.List;

/**
 * Created by 13260 on 2019/12/8.
 */
public class TransactionSummaryResponse {

    /**
     * 差异套数
     */
    private double differenceCount;

    /**
     * 差异面积
     */
    private double differenceArea;

    /**
     * 销售详情
     */
    private List<StatisticValue> list;

    public double getDifferenceCount() {
        return differenceCount;
    }

    public void setDifferenceCount(double differenceCount) {
        this.differenceCount = differenceCount;
    }

    public double getDifferenceArea() {
        return differenceArea;
    }

    public void setDifferenceArea(double differenceArea) {
        this.differenceArea = differenceArea;
    }

    public List<StatisticValue> getList() {
        return list;
    }

    public void setList(List<StatisticValue> list) {
        this.list = list;
    }
}
