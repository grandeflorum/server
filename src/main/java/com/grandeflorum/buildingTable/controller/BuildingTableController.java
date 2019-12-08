package com.grandeflorum.buildingTable.controller;


import com.grandeflorum.buildingTable.domain.C;
import com.grandeflorum.buildingTable.domain.H;
import com.grandeflorum.buildingTable.domain.LJZ;
import com.grandeflorum.buildingTable.domain.ZRZ;
import com.grandeflorum.buildingTable.service.BuildingTableService;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.project.domain.AuditParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

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



    /**
     * 添加或修改自然幢
     *
     * @param
     * @return
     */
    @PostMapping("/saveOrUpdateZRZ")
    public ResponseBo saveOrUpdateZRZ(@RequestBody ZRZ zrz) {
        return buildingTableService.saveOrUpdateZRZ(zrz);
    }

    /**
     * 查看自然幢详情
     *
     * @param
     * @return
     */
    @GetMapping("/getZRZById")
    public ResponseBo getZRZById(String id) {
        return buildingTableService.getZRZById(id);
    }

    /**
     * 删除自然幢详情
     *
     * @param
     * @return
     */
    @GetMapping("/deleteZRZ")
    public ResponseBo deleteZRZ(String id) {
        return buildingTableService.deleteZRZ(id);
    }

    /**
     * 添加或修改自然幢
     *
     * @param
     * @return
     */
    @PostMapping("/saveOrUpdateLJZ")
    public ResponseBo saveOrUpdateLJZ(@RequestBody LJZ ljz) {
        return buildingTableService.saveOrUpdateLJZ(ljz);
    }

    /**
     * 查看逻辑幢详情
     *
     * @param
     * @return
     */
    @GetMapping("/getLJZById")
    public ResponseBo getLJZById(String id) {
        return buildingTableService.getLJZById(id);
    }

    /**
     * 删除逻辑幢
     *
     * @param
     * @return
     */
    @GetMapping("/deleteLJZ")
    public ResponseBo deleteLJZ(String id) {
        return buildingTableService.deleteLJZ(id);
    }

    /**
     * 添加或修改层信息
     *
     * @param
     * @return
     */
    @PostMapping("/saveOrUpdateC")
    public ResponseBo saveOrUpdateC(@RequestBody C c ) {
        return buildingTableService.saveOrUpdateC(c);
    }

    /**
     * 查看层详情
     *
     * @param
     * @return
     */
    @GetMapping("/getCById")
    public ResponseBo getCById(String id) {
        return buildingTableService.getCById(id);
    }

    /**
     * 删除层信息
     *
     * @param
     * @return
     */
    @GetMapping("/deleteC")
    public ResponseBo deleteC( String id) {
        return buildingTableService.deleteC(id);
    }

    /**
     * 添加或修改户信息
     *
     * @param
     * @return
     */
    @PostMapping("/saveOrUpdateH")
    public ResponseBo saveOrUpdateH(@RequestBody H h ) {
        return buildingTableService.saveOrUpdateH(h);
    }

    /**
     * 查看户详情
     *
     * @param
     * @return
     */
    @GetMapping("/getHById")
    public ResponseBo getHById(String id) {
        return buildingTableService.getHById(id);
    }

    /**
     * 删除户信息
     *
     * @param
     * @return
     */
    @GetMapping("/deleteH")
    public ResponseBo deleteH(String id) {
        return buildingTableService.deleteH(id);
    }

    /**
     * 查询自然幢，逻辑幢，层下的户
     * @param page
     * @return
     */
    @RequestMapping("getChildHList")
    public ResponseBo getChildHList(@RequestBody Page page){
        return buildingTableService.getChildHList(page);
    }

    /**
     * 保存楼盘表信息
     *
     * @param
     * @return
     */
    @PostMapping("/saveOrUpdateZRZandLJZ")
    public ResponseBo saveOrUpdateZRZandLJZ(@RequestBody ZRZ zrz) {
        return buildingTableService.saveOrUpdateZRZandLJZ(zrz);
    }

    /**
     * 审核项目
     *
     * @param param
     * @return
     */
    @PostMapping("/auditZRZs")
    public ResponseBo auditProjects(@RequestBody AuditParam param) {
        if ((param.ids != null) && (param.ids.size() > 0)) {
            buildingTableService.auditZRZs(param);
        }
        return ResponseBo.ok();
    }
    /**
     * 提交审核
     *
     * @param
     * @return
     */
    @GetMapping("/auditZRZById")
    public ResponseBo auditZRZById(String id,int type) {
        return buildingTableService.auditZRZById(id, type);
    }


}
