package com.grandeflorum.system.service;

import com.grandeflorum.common.service.IService;
import com.grandeflorum.system.domain.DataDictionaryItem;

import java.util.List;
import java.util.Map;

public interface SystemDictionaryService extends IService<DataDictionaryItem> {
    Map<String, List<DataDictionaryItem>> getAllDictionary();

    String getDicName(String type,Integer code);
}
