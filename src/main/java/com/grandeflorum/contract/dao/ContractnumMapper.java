package com.grandeflorum.contract.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.contract.domain.Contractnum;

import java.util.Map;

public interface ContractnumMapper extends MyMapper<Contractnum> {

    Contractnum getObjByTypeAndTime(Map<String,Object> map);
}