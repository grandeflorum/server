package com.grandeflorum.contract.controller;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.contract.domain.StockTrade;
import com.grandeflorum.contract.service.StockTradeService;
import com.grandeflorum.project.domain.AuditParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        String projectId = stockTradeService.saveOrUpdateStockTrade(stockTrade);
        return ResponseBo.ok(projectId);

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
            stockTradeService.batchDelete(ids, "id", StockTrade.class);
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
}
