package com.grandeflorum.contract.service.impl;

import com.alibaba.fastjson.JSON;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.contract.dao.HouseTradeHistoryMapper;
import com.grandeflorum.contract.dao.HouseTradeMapper;
import com.grandeflorum.contract.domain.HouseTrade;
import com.grandeflorum.contract.domain.HouseTradeHistory;
import com.grandeflorum.contract.service.HouseTradeService;
import com.grandeflorum.project.dao.WFAuditMapper;
import com.grandeflorum.project.domain.AuditParam;
import com.grandeflorum.project.domain.WFAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("houseTradeService")
public class HouseTradeServiceIml extends BaseService<HouseTrade> implements HouseTradeService {

    @Autowired
    HouseTradeMapper houseTradeMapper;

    @Autowired
    HouseTradeHistoryMapper houseTradeHistoryMapper;

    @Autowired
    WFAuditMapper wFAuditMapper;

    @Override
    public ResponseBo getHouseTradeHistory(String id){
        List<HouseTradeHistory> list = houseTradeHistoryMapper.getHistoryList(id);

        if(list!=null&&list.size()>0){
            list.stream().forEach(x->x.setHouseTrade((HouseTrade) JSON.parse(x.getHistoryobj())));
        }

        return ResponseBo.ok(list);
    }

    @Override
    public ResponseBo btachAuditHouseTrade(AuditParam auditParam) {

        WFAudit wf = auditParam.getWfAudit();

        for (String id : auditParam.getIds()) {

            HouseTrade houseTrade = houseTradeMapper.selectByPrimaryKey(id);

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
                    houseTrade.setCurrentStatus(houseTrade.getCurrentStatus() + 1);
                }else if(wfAudit.getShjg()==2){
                    houseTrade.setIsPass(2);

                    HouseTradeHistory history = new HouseTradeHistory();

                    history.setId(GuidHelper.getGuid());
                    history.setHousetradeid(houseTrade.getId());
                    history.setCurrentstatus(houseTrade.getCurrentStatus().shortValue());
                    history.setSysDate(new Date());
                    history.setHistoryobj(JSON.toJSONString(houseTrade));

                    houseTradeHistoryMapper.insert(history);
                }
            }else{
                if(houseTrade.getIsPass()==2){
                    houseTrade.setIsPass(1);
                }else{
                    houseTrade.setCurrentStatus(houseTrade.getCurrentStatus() + 1);
                }
            }
            houseTradeMapper.updateByPrimaryKey(houseTrade);
        }

        return ResponseBo.ok();
    }
}
