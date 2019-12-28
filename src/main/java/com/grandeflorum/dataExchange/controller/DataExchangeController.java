package com.grandeflorum.dataExchange.controller;

import com.grandeflorum.dataExchange.domain.DataExchange;
import com.grandeflorum.dataExchange.service.DataExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by 13260 on 2019/12/28.
 */
@RestController
@Scope("prototype")
@RequestMapping("DataExchange")
public class DataExchangeController {

    @Autowired
    DataExchangeService dataExchangeService;


    /**
     * 商品房合同查询
     * @param map
     * @return
     */
    @PostMapping("/QueryNewHouseTradeByCode")
    public DataExchange QueryNewHouseTradeByCode(@RequestBody Map<String,String> map){

        return dataExchangeService.QueryNewHouseTradeByCode(map);

    }

    @PostMapping("/QueryStockHouseTradeByCode")
    public DataExchange QueryStockHouseTradeByCode(@RequestBody Map<String,String> map){
        return dataExchangeService.QueryStockHouseTradeByCode(map);
    }

}
