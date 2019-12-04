package com.grandeflorum.system.service.impl;

import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.system.dao.SystemDictionaryMapper;
import com.grandeflorum.system.domain.DataDictionaryItem;
import com.grandeflorum.system.service.SystemDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("SystemDictionaryService")
public class SystemDictionaryServiceImpl extends BaseService<DataDictionaryItem> implements SystemDictionaryService {

    @Autowired
    SystemDictionaryMapper systemDictionaryMapper;

    @Override
    public Map<String, List<DataDictionaryItem>> getAllDictionary() {
        Map<String, List<DataDictionaryItem>> dictionariesMap = new HashMap<String, List<DataDictionaryItem>>();
        List<DataDictionaryItem> dictionaryItems = systemDictionaryMapper.selectAll();

        for (DataDictionaryItem dataDictionaryItem : dictionaryItems) {
            if (dictionariesMap.containsKey(dataDictionaryItem.getType())) {
                List<DataDictionaryItem> list = (List<DataDictionaryItem>) dictionariesMap.get(dataDictionaryItem.getType());
                list.add(dataDictionaryItem);
            } else {
                List<DataDictionaryItem> list = new ArrayList<DataDictionaryItem>();
                list.add(dataDictionaryItem);
                dictionariesMap.put(dataDictionaryItem.getType(), list);
            }
        }

        for (List<DataDictionaryItem> list : dictionariesMap.values()) {
            Collections.sort(list, new Comparator<DataDictionaryItem>() {
                @Override
                public int compare(DataDictionaryItem o1, DataDictionaryItem o2) {
                    int diff = o1.getRank() - o2.getRank();
                    if(diff>0){
                        return 1;
                    }else if(diff<0){
                        return -1;
                    }else{
                        return 0;
                    }
                }
            });
        }
        return dictionariesMap;
    }

    @Override
    public String getDicName(String type,Integer code){

        if(code==null){
            return "";
        }
        Map<String,Object> map = new HashMap<>();
        map.put("type",type);
        map.put("code",code);
        return systemDictionaryMapper.getDicName(map);
    }
}
