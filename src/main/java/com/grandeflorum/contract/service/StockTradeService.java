package com.grandeflorum.contract.service;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.IService;
import com.grandeflorum.contract.domain.StockTrade;
import com.grandeflorum.project.domain.AuditParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface StockTradeService extends IService<StockTrade> {


    ResponseBo btachAuditStockTrade(AuditParam auditParam);

    ResponseBo getStockTradeHistory(String id);

    ResponseBo auditStockTradeById(String id, int type);

    // 保存或更新存量合同
    StockTrade saveOrUpdateStockTrade(StockTrade stockTrade);

    // 获取存量合同
    ResponseBo getStockTradeById(String id);

    //获取存量合同列表
    ResponseBo getStockTradeList(Page page);

    //获取存量合同注销列表
    ResponseBo getStockTradeCancelList(Page page);

    ResponseBo linkH(String id,String hid);

    ResponseBo deleteStockTradeByIds(List<String> ids);

    void printHt(String id ,HttpServletResponse response);

    //预览合同
    void previewHt(String id,HttpServletResponse response);

    void getParams(Map<String, Object> params, String id);

    ResponseBo sh(String id);

    ResponseBo getHInfo(String hid);
}
