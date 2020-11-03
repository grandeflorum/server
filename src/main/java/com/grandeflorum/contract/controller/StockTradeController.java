package com.grandeflorum.contract.controller;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.contract.domain.StockTrade;
import com.grandeflorum.contract.service.StockTradeService;
import com.grandeflorum.project.domain.AuditParam;
import com.grandeflorum.project.domain.WFAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("StockTrade")
public class StockTradeController {
    @Autowired
    private StockTradeService stockTradeService ;

    @PostMapping("/btachAuditStockTrade")
    public ResponseBo btachAuditStockTrade(@RequestBody AuditParam auditParam){
        return stockTradeService.btachAuditStockTrade(auditParam);
    }

    @GetMapping("/getStockTradeHistory")
    public ResponseBo getStockTradeHistory(String id){
        return stockTradeService.getStockTradeHistory(id);
    }

    @GetMapping("/auditStockTradeById")
    public ResponseBo auditStockTradeById(String id,int type) {
        return stockTradeService.auditStockTradeById(id, type);
    }
    /**
     * 添加或修改商品房合同
     *
     * @param
     * @return
     */
    @PostMapping("/saveOrUpdateStockTrade")
    public ResponseBo saveOrUpdateStockTrade(@RequestBody StockTrade stockTrade   ) {
        StockTrade stockTrade1 = stockTradeService.saveOrUpdateStockTrade(stockTrade);
        return ResponseBo.ok(stockTrade1);

    }

    /**
     * 获取商品房合同信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getStockTradeById")
    public ResponseBo getStockTradeById(String id) {
        return stockTradeService.getStockTradeById(id);
    }

    /**
     * 删除商品房合同信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteStockTradeByIds")
    public ResponseBo deleteStockTradeByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            stockTradeService.deleteStockTradeByIds(ids);
        }
        return ResponseBo.ok();
    }

    /**
     * 查询商品房合同信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getStockTradeList")
    public ResponseBo getStockTradeList(@RequestBody Page page) {

        return stockTradeService.getStockTradeList(page);
    }

    /**
     * 查询存量房注销合同信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getStockTradeCancelList")
    public ResponseBo getStockTradeCancelList(@RequestBody Page page) {

        return stockTradeService.getStockTradeCancelList(page);
    }

    @RequestMapping(value = "/printHt", method = RequestMethod.GET)
    public void printHt(@RequestParam(value = "id", required = false) String id
            , HttpServletResponse response) {
        stockTradeService.printHt(id,response);
    }

    @RequestMapping(value = "/printCeHt", method = RequestMethod.GET)
    public void printCeHt(@RequestParam(value = "id", required = false) String id
            , HttpServletResponse response) {
        stockTradeService.printCeHt(id,response);
    }

    @RequestMapping(value = "/previewHt", method = RequestMethod.GET)
    public void previewHt(@RequestParam(value = "id", required = false) String id,
                          HttpServletResponse response){
        stockTradeService.previewHt(id,response);
    }

    @RequestMapping(value = "/previewCeHt", method = RequestMethod.GET)
    public void previewCeHt(@RequestParam(value = "id", required = false) String id,
                          HttpServletResponse response){
        stockTradeService.previewCeHt(id,response);
    }

    @GetMapping("/linkH")
    public ResponseBo linkH(String id,String hid){
        return stockTradeService.linkH(id,hid);
    }

    @GetMapping("/sh")
    public ResponseBo sh(String id){
        return stockTradeService.sh(id);
    }


    /**
     * 获取户详细信息
     * @param hid
     * @return
     */
    @GetMapping("/getHInfo")
    public ResponseBo getHInfo(String hid) {
        return stockTradeService.getHInfo(hid);
    }

    @PostMapping("/AuditHouseTradeNew")
    public ResponseBo AuditHouseTradeNew(@RequestBody WFAudit wfAudit){
        return stockTradeService.AuditHouseTradeNew(wfAudit);
    }

    @PostMapping("/getWFAuditListByProjectid")
    public ResponseBo getWFAuditListByProjectid(@RequestBody Page page){
        return stockTradeService.getWFAuditListByProjectid(page);
    }

}
