package com.grandeflorum.statistic.controller;

import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.statistic.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by 13260 on 2019/12/1.
 */
@RestController
@RequestMapping("Statistic")
public class StatisticController {

    @Autowired
    StatisticService statisticService;

    @PostMapping("/getHouseRentalStatistic")
    public ResponseBo getHouseRentalStatistic(@RequestBody Map<String,Object> map){
        return statisticService.getHouseRentalStatistic(map);
    }
}
