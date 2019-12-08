package com.grandeflorum.statistic.service;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;

import java.util.Map;

/**
 * Created by 13260 on 2019/12/1.
 */
public interface StatisticService {

    ResponseBo getHouseRentalStatistic(Map<String,Object> map);

    ResponseBo getOverallSalesTrend(Map<String,Object> map);

    ResponseBo getProjectSalesVolumeList(Page page);

    ResponseBo getTimeQueryStatistics(Map<String,Object> map);

    ResponseBo getSummarySalesPurposes(Map<String,Object> map);

    ResponseBo getTransactionSummaryStatistic(Map<String,Object> map);
}
