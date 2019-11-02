package com.grandeflorum.StockHouse.controller;

import com.grandeflorum.StockHouse.domin.StockHouse;
import com.grandeflorum.StockHouse.service.StockHouseService;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.project.domain.AuditParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("StockHouse")
public class StockHouseController {

    @Autowired
    private StockHouseService stockHouseService;

    /**
     * 添加或修改存量房源
     *
     * @param
     * @return
     */
    @PostMapping("/saveOrUpdateStockHouse")
    public ResponseBo saveOrUpdateStockHouse(@RequestBody StockHouse stockHouse ) {
        String projectId = stockHouseService.saveOrUpdateStockHouse(stockHouse);
        return ResponseBo.ok(projectId);
    }

    /**
     * 获取存量房源信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getStockHouseById")
    public ResponseBo getStockHouseById(String id) {
        return stockHouseService.getStockHouseById(id);
    }

    /**
     * 删除存量房源信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteStockHouseByIds")
    public ResponseBo deleteStockHouseByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            stockHouseService.batchDelete(ids, "id", StockHouse.class);
        }
        return ResponseBo.ok();
    }

    /**
     * 查询存量房源信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getStockHouseList")
    public ResponseBo getStockHouseList(@RequestBody Page page) {

        return stockHouseService.getStockHouseList(page);
    }


    /**
     * 审核项目
     *
     * @param param
     * @return
     */
    @PostMapping("/auditStockHouses")
    public ResponseBo auditStockHouses(@RequestBody AuditParam param) {
        if ((param.ids != null) && (param.ids.size() > 0)) {
            stockHouseService.auditStockHouses(param);
        }
        return ResponseBo.ok();
    }

    /**
     * 修改审核项目状态
     *
     * @param stockHouse
     * @return
     */
    @PostMapping("/modifyAuditState")
    public ResponseBo modifyAuditState(@RequestBody StockHouse stockHouse ) {
        stockHouseService.modifyAuditState(stockHouse);
        return ResponseBo.ok();
    }
}
