package com.grandeflorum.system.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.system.domain.SysRegion;

import java.util.List;

public interface SysRegionMapper extends MyMapper<SysRegion> {

    List<SysRegion> getAllRegion();

    int getLevelByCode(String code);
}