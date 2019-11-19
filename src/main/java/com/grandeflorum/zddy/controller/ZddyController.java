package com.grandeflorum.zddy.controller;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.zddy.domain.Zddy;
import com.grandeflorum.zddy.service.ZddyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/11/19.
 */
@RestController
@RequestMapping("Zddy")
public class ZddyController {

    @Autowired
    ZddyService zddyService;

    @PostMapping("/saveOrUpdateZddy")
    public ResponseBo saveOrUpdateZddy(@RequestBody Zddy zddy) {
        return zddyService.SaveOrUpdateZddy(zddy);
    }

    @PostMapping("/getZddyList")
    public ResponseBo getZddyList(@RequestBody Page page){
        return zddyService.getZddyList(page);
    }

    @GetMapping("/getZddyById")
    public ResponseBo getZddyById(String id){
        return zddyService.getZddyById(id);
    }

    @PostMapping("/deleteZddyByIds")
    public ResponseBo deleteZddyByIds(@RequestBody List<String> ids) {
        return zddyService.deleteZddyByIds(ids);
    }

    @GetMapping("/updateZddyTypeById")
    ResponseBo updateZddyTypeById(String id,int type){
        return zddyService.updateZddyTypeById(id,type);
    }
}
