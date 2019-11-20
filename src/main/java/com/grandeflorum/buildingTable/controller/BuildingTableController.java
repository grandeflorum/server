package com.grandeflorum.buildingTable.controller;


import com.grandeflorum.buildingTable.service.BuildingTableService;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("BuildingTable")
public class BuildingTableController {

    @Autowired
    private BuildingTableService buildingTableService;

    @RequestMapping("getBuildingTableList")
    public ResponseBo getBuildingTableList(@RequestBody Page page){
        return buildingTableService.getBuildingTableList(page);
    }

    @RequestMapping("getZrz")
    public ResponseBo getZrz(String id){
        return buildingTableService.getZrz(id);
    }

    @RequestMapping("getLjz")
    public ResponseBo getLjz(String id){
        return buildingTableService.getLjz(id);
    }

    @GetMapping("getInfoByZh")
    public ResponseBo getInfoByZh(String ZH,String Type){
        return buildingTableService.getInfoByZh(ZH,Type);
    }

}
