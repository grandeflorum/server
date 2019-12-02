package com.grandeflorum.statistic.service.impl;

import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.statistic.dao.StatisticMapper;
import com.grandeflorum.statistic.domain.StatisticValue;
import com.grandeflorum.statistic.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 13260 on 2019/12/1.
 */
@Service("StatisticService")
public class StatisticServiceImpl implements StatisticService {

    @Autowired
    StatisticMapper statisticMapper;


    @Override
    public ResponseBo getHouseRentalStatistic(Map<String,Object> map) {

        List<StatisticValue> list = statisticMapper.getHouseRentalStatisticDate(map);

        List<StatisticValue> listRegion = statisticMapper.getHouseRentalStatisticRegion(map);

        Map<String, Object> mapResult = new HashMap<>();
        mapResult.put("dateResult",list);
        mapResult.put("regionResult",listRegion);

        return ResponseBo.ok(mapResult);
    }
}
