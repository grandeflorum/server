package com.grandeflorum.contract.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.StockHouse.dao.RelationShipMapper;
import com.grandeflorum.StockHouse.domin.RelationShip;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.common.util.StrUtil;
import com.grandeflorum.contract.dao.StockTradeHistoryMapper;
import com.grandeflorum.contract.dao.StockTradeMapper;
import com.grandeflorum.contract.domain.ContractCancel;
import com.grandeflorum.contract.domain.StockTrade;
import com.grandeflorum.contract.domain.StockTradeHistory;
import com.grandeflorum.contract.service.StockTradeService;
import com.grandeflorum.project.dao.WFAuditMapper;
import com.grandeflorum.project.domain.AuditParam;
import com.grandeflorum.project.domain.WFAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("StockTradeService")
public class StockTradeServiceImpl extends BaseService<StockTrade> implements StockTradeService {
    @Autowired
    StockTradeMapper stockTradeMapper ;

    @Autowired
    StockTradeHistoryMapper stockTradeHistoryMapper ;

    @Autowired
    WFAuditMapper wFAuditMapper;

    @Autowired
    HouseTradeServiceIml houseTradeServiceIml;

    @Autowired
    RelationShipMapper relationShipMapper;

    @Override
    public ResponseBo getStockTradeHistory(String id){
        List<StockTradeHistory> list = stockTradeHistoryMapper.getHistoryList(id);

        if(list!=null&&list.size()>0){
            list.stream().forEach(x->x.setStockTrade((StockTrade) JSON.parse(x.getHistoryobj())));
        }

        return ResponseBo.ok(list);
    }

    @Override
    @Transactional
    public ResponseBo btachAuditStockTrade(AuditParam auditParam) {

        WFAudit wf = auditParam.getWfAudit();

        for (String id : auditParam.getIds()) {

            StockTrade stockTrade = stockTradeMapper.selectByPrimaryKey(id);

            if (wf != null) {
                WFAudit wfAudit = new WFAudit();
                wfAudit.setId(GuidHelper.getGuid());

                wfAudit.setShjg(wf.getShjg());
                wfAudit.setShry(wf.getShry());
                wfAudit.setShrq(wf.getShrq());
                wfAudit.setBz(wf.getBz());
                wfAudit.setZxly(wf.getZxly());
                wfAudit.setProjectid(id);
                wfAudit.setSysDate(new Date());
                wfAudit.setSysUpdDate(new Date());
                wfAudit.setCurrentStatus(stockTrade.getCurrentStatus());
                wFAuditMapper.insert(wfAudit);
                //合同为已备案状态后可修改为已注销
                if(stockTrade.getCurrentStatus()==5){
                    stockTrade.setIsCancel(1);
                }else{
                    if(wfAudit.getShjg()==1){
                        stockTrade.setIsPass(1);
                        stockTrade.setCurrentStatus(stockTrade.getCurrentStatus() + 1);

                        if(stockTrade.getCurrentStatus()==4){
                            stockTrade.setHtbah(this.houseTradeServiceIml.getHTBAH("StockTrade"));
                        }

                    }else if(wfAudit.getShjg()==2){
                        stockTrade.setIsPass(2);
                        StockTradeHistory history = new StockTradeHistory();
                        history.setId(GuidHelper.getGuid());
                        history.setStocktradeid(stockTrade.getId());
                        history.setCurrentstatus(stockTrade.getCurrentStatus().shortValue());
                        history.setSysDate(new Date());
                        history.setHistoryobj(JSON.toJSONString(stockTrade));
                        stockTradeHistoryMapper.insert(history);
                    }
                }

            }else{
                if(stockTrade.getIsPass()!=null&& stockTrade.getIsPass()==2){
                    stockTrade.setIsPass(1);
                }else{
                    stockTrade.setCurrentStatus(stockTrade.getCurrentStatus() + 1);
                }
            }
            stockTrade.setSysUpdDate(new Date());
            stockTradeMapper.updateByPrimaryKey(stockTrade);
        }

        return ResponseBo.ok();
    }

    @Override
    public ResponseBo auditStockTradeById(String id, int type) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("type", type);

        stockTradeMapper.auditStockTradeById(map);
        return ResponseBo.ok();
    }

    @Override
    @Transactional
    public StockTrade saveOrUpdateStockTrade(StockTrade stockTrade) {
        if (stockTrade.getId() == null) {
            stockTrade.setId(GuidHelper.getGuid());
            stockTrade.setCurrentStatus(0);
            stockTrade.setSysDate(new Date());
            stockTrade.setSysUpdDate(new Date());
            stockTradeMapper.insert(stockTrade);
        } else {
            stockTrade.setSysUpdDate(new Date());
            stockTradeMapper.updateByPrimaryKey(stockTrade);
            //如果更新的话先删除原来关系数据
            relationShipMapper.deleteRelationShipByProjectId(stockTrade.getId());
        }

        //添加存量房相关的人员信息
        if (stockTrade.getRelationShips() != null && stockTrade.getRelationShips().size() > 0) {
        for (RelationShip relationShip : stockTrade.getRelationShips()) {
            relationShip.setId(GuidHelper.getGuid());
            relationShip.setProjectId(stockTrade.id);
            relationShip.setSysDate(new Date());
            relationShip.setSysUpdDate(new Date());
            relationShipMapper.insert(relationShip);
        }
    }
        return stockTrade;
    }

    @Override
    public ResponseBo getStockTradeById(String id) {
        StockTrade result = stockTradeMapper.getStockTradeById(id);
        if (result != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("shjg", 1);
            map.put("projectid", id);
            List<WFAudit> list = wFAuditMapper.getWFAuditList(map);
            result.setWfAuditList(list);
            if(!StrUtil.isNullOrEmpty(result.getHouseId())){
                result.setLjzid( stockTradeMapper.getLjzh(result.getHouseId()));
            }
            result.setRelationShips(relationShipMapper.getRelationShipByProjectId(result.getId()));
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public ResponseBo getStockTradeList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<StockTrade> list = stockTradeMapper.getStockTradeList(map);

        PageInfo<StockTrade> pageInfo = new PageInfo<StockTrade>(list);

        PagingEntity<StockTrade> result = new PagingEntity<>(pageInfo);
        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getStockTradeCancelList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<ContractCancel> list = stockTradeMapper.getStockTradeCancelList(map);

        PageInfo<ContractCancel> pageInfo = new PageInfo<ContractCancel>(list);

        PagingEntity<ContractCancel> result = new PagingEntity<>(pageInfo);
        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo linkH(String id,String hid){

        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("hid",hid);

        stockTradeMapper.linkH(map);

        return ResponseBo.ok();
    }

    @Override
    @Transactional
    public ResponseBo deleteStockTradeByIds(List<String> ids){
        for (String id:ids){
            stockTradeMapper.deleteByPrimaryKey(id);

            Example exampleAudit = new Example(WFAudit.class);
            exampleAudit.createCriteria().andEqualTo("projectid", id);
            wFAuditMapper.deleteByExample(exampleAudit);

            Example exampleHistory = new Example(StockTradeHistory.class);
            exampleHistory.createCriteria().andEqualTo("stocktradeid", id);
            stockTradeHistoryMapper.deleteByExample(exampleHistory);

            Example exampleRelationShip=new Example(RelationShip.class);
            exampleRelationShip.createCriteria().andEqualTo("projectId", id);
            relationShipMapper.deleteByExample(exampleRelationShip);
        }
        return ResponseBo.ok();
    }

}
