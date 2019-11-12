package com.grandeflorum.contract.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.contract.dao.StockTradeHistoryMapper;
import com.grandeflorum.contract.dao.StockTradeMapper;
import com.grandeflorum.contract.domain.StockTrade;
import com.grandeflorum.contract.domain.StockTradeHistory;
import com.grandeflorum.contract.service.StockTradeService;
import com.grandeflorum.project.dao.WFAuditMapper;
import com.grandeflorum.project.domain.AuditParam;
import com.grandeflorum.project.domain.WFAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public ResponseBo getStockTradeHistory(String id){
        List<StockTradeHistory> list = stockTradeHistoryMapper.getHistoryList(id);

        if(list!=null&&list.size()>0){
            list.stream().forEach(x->x.setStockTrade((StockTrade) JSON.parse(x.getHistoryobj())));
        }

        return ResponseBo.ok(list);
    }

    @Override
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

                wfAudit.setProjectid(id);
                wfAudit.setSysDate(new Date());
                wfAudit.setSysUpdDate(new Date());

                wFAuditMapper.insert(wfAudit);

                if(wfAudit.getShjg()==1){
                    stockTrade.setCurrentStatus(stockTrade.getCurrentStatus() + 1);
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
            }else{
                if(stockTrade.getIsPass()==2){
                    stockTrade.setIsPass(1);
                }else{
                    stockTrade.setCurrentStatus(stockTrade.getCurrentStatus() + 1);
                }
            }
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
    public String saveOrUpdateStockTrade(StockTrade stockTrade) {
        if (stockTrade.getId() == null) {
            stockTrade.setId(GuidHelper.getGuid());
            stockTrade.setCurrentStatus(0);
            stockTrade.setSysDate(new Date());
            stockTradeMapper.insert(stockTrade);
        } else {
            stockTrade.setSysUpdDate(new Date());
            stockTradeMapper.updateByPrimaryKey(stockTrade);
        }
        return stockTrade.getId();
    }

    @Override
    public ResponseBo getStockTradeById(String id) {
        StockTrade result = stockTradeMapper.selectByPrimaryKey(id);
        if (result != null) {
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
}
