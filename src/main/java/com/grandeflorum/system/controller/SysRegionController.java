package com.grandeflorum.system.controller;

import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.system.domain.SysRegion;
import com.grandeflorum.system.service.SysRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 13260 on 2019/11/9.
 */
@RestController
@RequestMapping("SysRegion")
public class SysRegionController {

    @Autowired
    SysRegionService sysRegionService;

    @PostMapping("/getAllRegion")
    public ResponseBo getAllRegion() {
       return sysRegionService.getAllRegion();
    }

}
