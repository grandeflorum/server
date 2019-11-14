package com.grandeflorum.StockHouse.service.impi;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.StockHouse.dao.RelationShipMapper;
import com.grandeflorum.StockHouse.dao.StockHouseMapper;
import com.grandeflorum.StockHouse.domin.RelationShip;
import com.grandeflorum.StockHouse.domin.StockHouse;
import com.grandeflorum.StockHouse.service.StockHouseService;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.project.dao.WFAuditMapper;
import com.grandeflorum.project.domain.AuditParam;
import com.grandeflorum.project.domain.WFAudit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("StockHouseService")
public class StockHouseServiceImpl extends BaseService<StockHouse> implements StockHouseService {

    @Autowired
    StockHouseMapper stockHouseMapper;

    @Autowired
    RelationShipMapper relationShipMapper;

    @Autowired
    WFAuditMapper wFAuditMapper;

    @Override
    public String saveOrUpdateStockHouse(StockHouse stockHouse) {
        if (stockHouse.getId() == null) {
            stockHouse.setId(GuidHelper.getGuid());
            stockHouse.setAuditType(0);
            stockHouse.setSysDate(new Date());
            stockHouseMapper.insert(stockHouse);
        } else {
            stockHouse.setSysUpdDate(new Date());
            stockHouseMapper.updateByPrimaryKey(stockHouse);
            //如果更新的话先删除原来关系数据
            relationShipMapper.deleteRelationShipByProjectId(stockHouse.getId());
        }
        //添加存量房相关的人员信息
        if (stockHouse.getRelationShips() != null && stockHouse.getRelationShips().size() > 0) {
            for (RelationShip relationShip : stockHouse.getRelationShips()) {
                relationShip.setId(GuidHelper.getGuid());
                relationShip.setProjectId(stockHouse.id);
                relationShipMapper.insert(relationShip);
            }
        }
        return stockHouse.getId();
    }

    @Override
    public ResponseBo getStockHouseById(String id) {
        StockHouse result = stockHouseMapper.selectByPrimaryKey(id);
        result.setRelationShips(relationShipMapper.getRelationShipByProjectId(result.getId()));
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public ResponseBo getStockHouseList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<StockHouse> list = stockHouseMapper.getStockHouseList(map);

        PageInfo<StockHouse> pageInfo = new PageInfo<StockHouse>(list);

        PagingEntity<StockHouse> result = new PagingEntity<>(pageInfo);
        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo auditStockHouses(AuditParam param) {
        WFAudit wf = param.getWfAudit();
        for (String id : param.ids) {
            //更新项目表信息
            if(param.getWfAudit().getShjg()==1){
                auditStockHouseById(id, 2);
            }else{
                auditStockHouseById(id, 3);
            }

            //添加或更新审核表信息
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
        }
        return ResponseBo.ok();
    }



    @Override
    public ResponseBo auditStockHouseById(String id, int type) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("type", type);

        stockHouseMapper.auditStockHouseById(map);
        return ResponseBo.ok();
    }
}
