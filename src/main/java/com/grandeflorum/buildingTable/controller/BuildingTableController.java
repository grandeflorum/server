package com.grandeflorum.buildingTable.controller;


import com.grandeflorum.buildingTable.service.BuildingTableService;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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

    @RequestMapping(value = "/printHt", method = RequestMethod.GET)
    public void printHt(@RequestParam(value = "id", required = false) String id,
                        @RequestParam(value = "type", required = false) int type
            , HttpServletResponse response) {
        buildingTableService.printHt(id,type,response);
    }

}
