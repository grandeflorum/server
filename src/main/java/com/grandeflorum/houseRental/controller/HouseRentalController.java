package com.grandeflorum.houseRental.controller;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.houseRental.domain.HouseRental;
import com.grandeflorum.houseRental.service.HouseRentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/11/9.
 */
@RestController
@RequestMapping("HouseRental")
public class HouseRentalController {

    @Autowired
    HouseRentalService houseRentalService;

    @PostMapping("/SaveOrUpdateHouseRental")
    public ResponseBo SaveOrUpdateHouseRental(@RequestBody HouseRental company) {
        return houseRentalService.SaveOrUpdateHouseRental(company);
    }

    @PostMapping("/getHouseRentalList")
    public ResponseBo getHouseRentalList(@RequestBody Page page){
        return houseRentalService.getHouseRentalList(page);
    }

    @GetMapping("/getHouseRentalById")
    public ResponseBo getHouseRentalById(String id){
        return houseRentalService.getHouseRentalById(id);
    }

    @PostMapping("/deleteHouseRentalByIds")
    public ResponseBo deleteHouseRentalByIds(@RequestBody List<String> ids) {
        return houseRentalService.deleteHouseRentalByIds(ids);
    }

    @GetMapping("/linkH")
    public ResponseBo linkH(String id,String hid){
        return houseRentalService.linkH(id,hid);
    }
}
