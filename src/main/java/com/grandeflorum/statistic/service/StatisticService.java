package com.grandeflorum.statistic.service;

import com.grandeflorum.common.domain.ResponseBo;

import java.util.Map;

/**
 * Created by 13260 on 2019/12/1.
 */
public interface StatisticService {

    ResponseBo getHouseRentalStatistic(Map<String,Object> map);
}
