package com.grandeflorum.statistic.domain;

/**
 * Created by 13260 on 2019/12/8.
 */
public class TransactionSummaryInfo {

    private double AlreadySoldCount;

    private double UnsoldCount;

    private double AlreadySoldArea;

    private double UnsoldArea;

    public double getAlreadySoldCount() {
        return AlreadySoldCount;
    }

    public void setAlreadySoldCount(double alreadySoldCount) {
        AlreadySoldCount = alreadySoldCount;
    }

    public double getUnsoldCount() {
        return UnsoldCount;
    }

    public void setUnsoldCount(double unsoldCount) {
        UnsoldCount = unsoldCount;
    }

    public double getAlreadySoldArea() {
        return AlreadySoldArea;
    }

    public void setAlreadySoldArea(double alreadySoldArea) {
        AlreadySoldArea = alreadySoldArea;
    }

    public double getUnsoldArea() {
        return UnsoldArea;
    }

    public void setUnsoldArea(double unsoldArea) {
        UnsoldArea = unsoldArea;
    }
}
