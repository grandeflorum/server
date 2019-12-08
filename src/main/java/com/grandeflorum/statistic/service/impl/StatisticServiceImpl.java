package com.grandeflorum.statistic.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.statistic.dao.StatisticMapper;
import com.grandeflorum.statistic.domain.SalesStatisticInfo;
import com.grandeflorum.statistic.domain.StatisticValue;
import com.grandeflorum.statistic.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.util.ArrayList;
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

    @Override
    public ResponseBo getOverallSalesTrend(Map<String,Object> map) {

        Map<String, Object> result = new HashMap<>();

        List<StatisticValue> list = statisticMapper.getOverallSalesTrend(map);

        List<String> H = new ArrayList<>();
        List<Double> L = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            String h = i > 9 ? (i + "月") : ("0" + i + "月");
            double v = 0;

            if (list != null) {
                int yf = i;
                StatisticValue s = list.stream().filter(x -> Integer.parseInt(x.getDateValue()) == yf).findFirst().get();

                if (s != null) {
                    v = s.getTs();
                }
            }
            H.add(h);
            L.add(v);
        }

        result.put("X", H);
        result.put("Y", L);

        return ResponseBo.ok(result);
    }


    @Override
    public ResponseBo getProjectSalesVolumeList(Page page){

        PageHelper.startPage(page.getPageNo(), page.getPageSize());

        Map<String, Object> map = page.getQueryParameter();

        List<SalesStatisticInfo> list = statisticMapper.getProjectSalesVolumeList(map);

        PageInfo<SalesStatisticInfo> pageInfo = new PageInfo<>(list);

        PagingEntity<SalesStatisticInfo> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getTimeQueryStatistics(Map<String,Object> map){
        List<StatisticValue> list = statisticMapper.getTimeQueryStatistics(map);

        return ResponseBo.ok(list);
    }

    @Override
    public ResponseBo getSummarySalesPurposes(Map<String,Object> map){

        List<StatisticValue> list = statisticMapper.getSummarySalesPurposes(map);

        return  ResponseBo.ok(list);
    }

}
