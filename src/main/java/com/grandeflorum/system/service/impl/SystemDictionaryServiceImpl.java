package com.grandeflorum.system.service.impl;

import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.system.dao.SystemDictionaryMapper;
import com.grandeflorum.system.domain.DataDictionaryItem;
import com.grandeflorum.system.service.SystemDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("SystemCommonService")
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
        return dictionariesMap;
    }

}
