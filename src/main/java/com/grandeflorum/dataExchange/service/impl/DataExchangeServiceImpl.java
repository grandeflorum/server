package com.grandeflorum.dataExchange.service.impl;

import com.grandeflorum.common.config.GrandeflorumProperties;
import com.grandeflorum.dataExchange.dao.DataExchangeMapper;
import com.grandeflorum.dataExchange.domain.DataExchange;
import com.grandeflorum.dataExchange.service.DataExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 13260 on 2019/12/28.
 */
@Service("DataExchangeService")
public class DataExchangeServiceImpl implements DataExchangeService {

    @Autowired
    private GrandeflorumProperties grandeflorumProperties;

    @Autowired
    private DataExchangeMapper dataExchangeMapper;

    @Override
    public DataExchange QueryNewHouseTradeByCode(Map<String,String> map){
        return commonDataExchange(map,"QueryNewHouseTradeByCode");
    }

    @Override
    public DataExchange QueryStockHouseTradeByCode(Map<String,String> map){
        return commonDataExchange(map,"QueryStockHouseTradeByCode");
    }

    @Override
    public DataExchange QueryPresaleByName(Map<String,String> map){
        return commonDataExchange(map,"QueryPresaleByName");
    }

    public DataExchange commonDataExchange(Map<String,String> map,String type){
        DataExchange dataExchange = new DataExchange();

        try {

            if(!tokenCheck(map)){
                dataExchange.setFlag(false);
                dataExchange.setMessage("赣互通平台令牌错误");
                return dataExchange;
            }

            List<Map<String,Object>> data = new ArrayList<>();

            switch (type){
                case "QueryNewHouseTradeByCode":
                    String IdentityCode = map.get("IdentityCode");
                     data = dataExchangeMapper.QueryNewHouseTradeByCode(IdentityCode);
                    break;
                case "QueryStockHouseTradeByCode":
                    String IdentityCode1 = map.get("IdentityCode");
                    data = dataExchangeMapper.QueryStockHouseTradeByCode(IdentityCode1);
                    break;
                case "QueryPresaleByName":
                    String lpmc = map.get("LPMC");
                    data = dataExchangeMapper.QueryPresaleByName(lpmc);
                    break;
            }

            dataExchange.setFlag(true);
            mapNullToString(data);
            dataExchange.setData(data);

        }catch (Exception e){
            dataExchange.setFlag(false);

            e.printStackTrace();
            dataExchange.setMessage("系统错误，请联系管理员");
            return dataExchange;
        }

        return dataExchange;
    }

    @Override
    public DataExchange QueryHouseResourceByName(Map<String, String> map) {
        DataExchange dataExchange = new DataExchange();

        try {

            if(!tokenCheck(map)){
                dataExchange.setFlag(false);
                dataExchange.setMessage("赣互通平台令牌错误");
                return dataExchange;
            }

            String LPMC = map.get("LPMC");
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("xmmc", LPMC);
            paramMap.put("jzwmc", LPMC);
            List<Map<String,Object>> data = dataExchangeMapper.QueryHouseResourceByName(paramMap);

            dataExchange.setFlag(true);
            mapNullToString(data);
            dataExchange.setData(data);

        }catch (Exception e){
            dataExchange.setFlag(false);

            e.printStackTrace();
            dataExchange.setMessage("系统错误，请联系管理员");
            return dataExchange;
        }

        return dataExchange;
    }


    public boolean tokenCheck(Map<String,String> map){
        boolean result = true;

        String token = map.get("Token");

        if(!grandeflorumProperties.getToken().equals(token)){
            return false;
        }

        return result;
    }

    public void mapNullToString(List<Map<String,Object>> data){

        if(data==null||data.size()==0){
            return;
        }

        for (Map<String,Object> map:data ) {
            for(String s:map.keySet()){
                if(map.get(s)==null){
                    map.put(s,"");
                }
            }
        }
    }
}
