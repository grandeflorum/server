package com.grandeflorum.system.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.system.domain.DataDictionaryItem;

import java.util.Map;

public interface SystemDictionaryMapper extends MyMapper<DataDictionaryItem> {

    String getDicName( Map<String,Object> map);
}
