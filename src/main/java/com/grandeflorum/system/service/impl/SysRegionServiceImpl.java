package com.grandeflorum.system.service.impl;

import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.system.dao.SysRegionMapper;
import com.grandeflorum.system.domain.SysRegion;
import com.grandeflorum.system.service.SysRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 13260 on 2019/11/9.
 */
@Service("SysRegionService")
public class SysRegionServiceImpl extends BaseService<SysRegion> implements SysRegionService{

    @Autowired
    SysRegionMapper sysRegionMapper;

    @Override
    public ResponseBo  getAllRegion() {
        List<SysRegion> data = sysRegionMapper.getAllRegion();
        return ResponseBo.ok(data);
    }

    @Override
    public  String getFilterCode(String code) {

        String result = "";
        int level = sysRegionMapper.getLevelByCode(code);

        if (level == 2) {
            result = code.substring(0,4);
        } else if (level == 3) {
            result = code.substring(0,6);
        } else if (level == 4) {
            result = code.substring(0,7);
        }

        return result;
    }
}
