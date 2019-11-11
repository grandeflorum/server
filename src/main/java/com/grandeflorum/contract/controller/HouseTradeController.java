package com.grandeflorum.contract.controller;

import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.contract.service.HouseTradeService;
import com.grandeflorum.project.domain.AuditParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("HouseContract")
public class HouseTradeController {

    @Autowired
    private HouseTradeService houseTradeService;

    @PostMapping("/btachAuditHouseTrade")
    public ResponseBo btachAuditHouseTrade(@RequestBody AuditParam auditParam){
        return houseTradeService.btachAuditHouseTrade(auditParam);
    }

    @GetMapping("/getHouseTradeHistory")
    public ResponseBo getHouseTradeHistory(String id){
        return houseTradeService.getHouseTradeHistory(id);
    }

}
