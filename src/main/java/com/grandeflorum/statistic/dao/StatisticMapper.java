package com.grandeflorum.statistic.dao;

import com.grandeflorum.statistic.domain.SalesStatisticInfo;
import com.grandeflorum.statistic.domain.StatisticValue;
import com.grandeflorum.statistic.domain.TimeTatolValue;
import com.grandeflorum.statistic.domain.TransactionSummaryInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by 13260 on 2019/12/1.
 */
public interface StatisticMapper {

    List<StatisticValue> getHouseRentalStatisticDate(Map<String,Object> map);

    List<StatisticValue> getHouseRentalStatisticRegion(Map<String,Object> map);

    List<StatisticValue> getOverallSalesTrend(Map<String,Object> map);

    List<SalesStatisticInfo> getProjectSalesVolumeList(Map<String,Object> map);

    List<SalesStatisticInfo> getSalesVolumeTotalList(Map<String,Object> map);

    List<SalesStatisticInfo> getHouseTradeTotalList(Map<String,Object> map);

    List<SalesStatisticInfo> getStockTradeTotalList(Map<String,Object> map);

    List<TimeTatolValue>  getTimeTotalValueList(Map<String,Object> map);

    List<StatisticValue> getTimeQueryStatistics(Map<String,Object> map);

    List<StatisticValue> getSummarySalesPurposes(Map<String,Object> map);

    TransactionSummaryInfo getTransactionSummaryInfo(Map<String,Object> map);
}
