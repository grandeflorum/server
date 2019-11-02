package com.grandeflorum.StockHouse.service.impi;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.StockHouse.dao.StockHouseMapper;
import com.grandeflorum.StockHouse.domin.StockHouse;
import com.grandeflorum.StockHouse.service.StockHouseService;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.project.domain.AuditParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("StockHouseService")
public class StockHouseServiceImpl extends BaseService<StockHouse> implements StockHouseService {

    @Autowired
    StockHouseMapper stockHouseMapper;

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
        }
        return stockHouse.getId();
    }

    @Override
    public ResponseBo getStockHouseById(String id) {
        StockHouse result = stockHouseMapper.selectByPrimaryKey(id);
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
        for (String id : param.ids) {
            //更新项目表信息
            StockHouse stockHouse  = stockHouseMapper.selectByPrimaryKey(id);
            stockHouse.setAuditType(2);
            stockHouseMapper.updateByPrimaryKey(stockHouse);

            //添加或更新审核表信息
            if(param.getWfAudit()!=null&&param.getWfAudit().getId()==null){
                param.getWfAudit().setId(GuidHelper.getGuid());
            }
            param.getWfAudit().setProjectId(id);
            stockHouseMapper.addOrUpdateAudit(param.getWfAudit());
        }
        return ResponseBo.ok();
    }

    @Override
    public ResponseBo modifyAuditState(StockHouse project) {
        StockHouse projectinfo = stockHouseMapper.selectByPrimaryKey(project.getId());
        projectinfo.setAuditType(project.getAuditType());
        stockHouseMapper.updateByPrimaryKey(projectinfo);
        return ResponseBo.ok();
    }
}
