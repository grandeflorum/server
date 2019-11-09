package com.grandeflorum.system.service;

import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.IService;
import com.grandeflorum.system.domain.SysRegion;

import java.util.List;

/**
 * Created by 13260 on 2019/11/9.
 */
public interface SysRegionService extends IService<SysRegion>{

    ResponseBo getAllRegion();

    String getFilterCode(String code);
}
