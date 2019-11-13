package com.grandeflorum.contract.controller;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.contract.domain.HouseTrade;
import com.grandeflorum.contract.service.HouseTradeService;
import com.grandeflorum.project.domain.AuditParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("HouseTrade")
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

    @GetMapping("/auditHouseTradeById")
    public ResponseBo auditHouseTradeById(String id,int type) {
        return houseTradeService.auditHouseTradeById(id, type);
    }
    /**
     * 添加或修改商品房合同
     *
     * @param
     * @return
     */
    @PostMapping("/saveOrUpdateHouseTrade")
    public ResponseBo saveOrUpdateHouseTrade(@RequestBody HouseTrade houseTrade  ) {
        String projectId = houseTradeService.saveOrUpdateHouseTrade(houseTrade);
        return ResponseBo.ok(projectId);

    }

    /**
     * 获取商品房合同信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getHouseTradeById")
    public ResponseBo getHouseTradeById(String id) {
        return houseTradeService.getHouseTradeById(id);
    }

    /**
     * 删除商品房合同信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteHouseTradeByIds")
    public ResponseBo deleteHouseTradeByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            houseTradeService.batchDelete(ids, "id", HouseTrade.class);
        }
        return ResponseBo.ok();
    }

    /**
     * 查询商品房合同信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getHouseTradeList")
    public ResponseBo getHouseTradeList(@RequestBody Page page) {

        return houseTradeService.getHouseTradeList(page);
    }

    @RequestMapping(value = "/printHt", method = RequestMethod.GET)
    public void printHt(@RequestParam(value = "id", required = false) String id
            , HttpServletResponse response) {
        houseTradeService.printHt(id,response);
    }
}
