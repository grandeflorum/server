package com.grandeflorum.dataExchange.service;

import com.grandeflorum.dataExchange.domain.DataExchange;

import java.util.List;
import java.util.Map;

/**
 * Created by 13260 on 2019/12/28.
 */
public interface DataExchangeService {

    DataExchange QueryNewHouseTradeByCode(Map<String,String> map);

    DataExchange QueryStockHouseTradeByCode(Map<String,String> map);
}
