package com.grandeflorum.contract.service;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.IService;
import com.grandeflorum.contract.domain.HouseTrade;
import com.grandeflorum.project.domain.AuditParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface HouseTradeService extends IService<HouseTrade> {


    ResponseBo btachAuditHouseTrade(AuditParam auditParam);

    ResponseBo getHouseTradeHistory(String id);

     ResponseBo auditHouseTradeById(String id, int type) ;

    // 保持或更新存量房源
    HouseTrade saveOrUpdateHouseTrade(HouseTrade houseTrade  );

    // 获取存量房源信息
    ResponseBo getHouseTradeById(String id);

    //获取存量房源列表
    ResponseBo getHouseTradeList(Page page);

    void printHt(String id , String type,HttpServletResponse response);

    ResponseBo linkH(String id,String hid);

    ResponseBo deleteHouseTradeByIds(List<String> ids);
}
