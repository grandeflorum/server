package com.grandeflorum.dataExchange.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by 13260 on 2019/12/28.
 */
public interface DataExchangeMapper {

    List<Map<String,Object>> QueryNewHouseTradeByCode(String IdentityCode);

    List<Map<String,Object>> QueryStockHouseTradeByCode(String IdentityCode);

    List<Map<String,Object>> QueryPresaleByName(String lpmc);


    List<Map<String,Object>> QueryHouseResourceByName(String LPMC);


}
