package com.grandeflorum.zddy.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.zddy.domain.Zddy;

import java.util.List;
import java.util.Map;

public interface ZddyMapper extends MyMapper<Zddy> {

    List<Zddy> getZddyList(Map<String,Object> map);

    void updateZddyTypeById(Map<String,Object> map);

    String selectZrzhByBdcdyh(String bdcdyh);

    void restrictedProperty(Map<String,Object> map);

    String selectBdcdyhByZh(Map<String,Object> map);

    Zddy getZddyById(String id);
}