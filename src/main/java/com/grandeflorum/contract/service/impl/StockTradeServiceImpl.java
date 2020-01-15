package com.grandeflorum.contract.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.StockHouse.dao.RelationShipMapper;
import com.grandeflorum.StockHouse.domin.RelationShip;
import com.grandeflorum.attachment.service.FileInfoService;
import com.grandeflorum.common.cache.EHCacheUtils;
import com.grandeflorum.common.config.GrandeflorumProperties;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.*;
import com.grandeflorum.contract.dao.StockTradeHistoryMapper;
import com.grandeflorum.contract.dao.StockTradeMapper;
import com.grandeflorum.contract.domain.*;
import com.grandeflorum.contract.service.StockTradeService;
import com.grandeflorum.project.dao.WFAuditMapper;
import com.grandeflorum.project.domain.AuditParam;
import com.grandeflorum.project.domain.WFAudit;
import com.grandeflorum.system.domain.SystemUser;
import com.grandeflorum.system.service.SystemDictionaryService;
import com.grandeflorum.system.service.SystemUserService;
import net.sf.ehcache.CacheManager;
import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.grandeflorum.common.util.StrUtil.DoubleToString;
import static com.grandeflorum.common.util.StrUtil.NoNullString;

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

    @Autowired
    FileInfoService fileInfoService;

    @Autowired
    GrandeflorumProperties grandeflorumProperties;

    @Autowired
    SystemDictionaryService systemDictionaryService;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    SystemUserService systemUserService;

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
                if(wf.getFileInfoList()!=null&&wf.getFileInfoList().size()>0){
                    fileInfoService.updateFileInfoByIds(wf.getFileInfoList(),wfAudit.getId());
                }

                if(stockTrade.getCurrentStatus()==4){
                    stockTrade.setBasj(new Date());
                }
                //合同为已备案状态后可修改为已注销
                if(stockTrade.getCurrentStatus()==5){
                    stockTrade.setIsCancel(1);
                    if(auditParam.getStatus()==2){
                        stockTrade.setIsCancel(2);
                    }
                }else{
                    if(wfAudit.getShjg()==1){
                        stockTrade.setIsPass(1);
                        stockTrade.setCurrentStatus(stockTrade.getCurrentStatus() + 1);

                        if(stockTrade.getCurrentStatus()==4){
                            stockTrade.setHtbah(this.houseTradeServiceIml.getHTBAH("StockTrade"));
                        }

                        if(stockTrade.getCurrentStatus()==5){
                            stockTrade.setBasj(new Date());
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
    @Transactional
    public  ResponseBo AuditHouseTradeNew(WFAudit wfAudit){
        StockTrade stockTrade = stockTradeMapper.selectByPrimaryKey(wfAudit.getProjectid());


        if (StrUtil.isNullOrEmpty(wfAudit.getId())) {
            wfAudit.setId(GuidHelper.getGuid());
        }
        wfAudit.setSysDate(new Date());
        wfAudit.setSysUpdDate(new Date());
        wfAudit.setCurrentStatus(stockTrade.getCurrentStatus());
        wfAudit.setIsActive(1);
        wFAuditMapper.insert(wfAudit);

        //备案记录备案时间
        if (stockTrade.getCurrentStatus() == 4) {
            stockTrade.setBasj(new Date());
        }

        Map<String, Object> mapUpdate = new HashMap<>();
        mapUpdate.put("projectId", wfAudit.getProjectid());
        mapUpdate.put("currentStatus", wfAudit.getCurrentStatus());

        if (wfAudit.getShjg() == 1) {

            if (1 == wfAudit.getUserType()) {
                mapUpdate.put("userType",2);
            }else{
                mapUpdate.put("userType",1);
            }

            int count = wFAuditMapper.getOtherOrgPassCount(mapUpdate);

            if(count>0){
                stockTrade.setIsPass(1);
                stockTrade.setCurrentStatus(stockTrade.getCurrentStatus() + 1);

                if (stockTrade.getCurrentStatus() == 4) {
                    stockTrade.setHtbah(this.houseTradeServiceIml.getHTBAH("StockTrade"));
                }

                if (stockTrade.getCurrentStatus() == 5) {
                    stockTrade.setBasj(new Date());
                }
            }



        } else if (wfAudit.getShjg() == 2) {

            //设置以前的审核位失效
            wFAuditMapper.updateWfActive(mapUpdate);


            //设置历史信息
            stockTrade.setIsPass(2);
            StockTradeHistory history = new StockTradeHistory();
            history.setId(GuidHelper.getGuid());
            history.setStocktradeid(stockTrade.getId());
            history.setCurrentstatus(stockTrade.getCurrentStatus().shortValue());
            history.setSysDate(new Date());
            history.setHistoryobj(JSON.toJSONString(stockTrade));
            stockTradeHistoryMapper.insert(history);
        }

        stockTrade.setSysUpdDate(new Date());
        stockTradeMapper.updateByPrimaryKey(stockTrade);


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

        SystemUser user = EHCacheUtils.getCurrentUser(cacheManager);

        if (stockTrade.getId() == null) {
            stockTrade.setId(GuidHelper.getGuid());
            stockTrade.setCurrentStatus(0);
            stockTrade.setSysDate(new Date());
            stockTrade.setSysUpdDate(new Date());

            if(user!=null){
                stockTrade.setDjr(user.getId());
            }

            stockTradeMapper.insert(stockTrade);
        } else {
            stockTrade.setSysUpdDate(new Date());

            //变更
            if(!StrUtil.isNullOrEmpty(stockTrade.getBg())){

                StockTrade old = stockTradeMapper.selectByPrimaryKey(stockTrade.getId());
                StockTrade stockTradeBg = (StockTrade)old.clone();
                stockTradeBg.setIsCancel(2);
                stockTradeBg.setSysDate(new Date());
                String id = GuidHelper.getGuid();
                stockTradeBg.setId(id);
                stockTradeMapper.insert(stockTradeBg);

                StockTradeHistory history = new StockTradeHistory();
                history.setId(GuidHelper.getGuid());
                history.setStocktradeid(stockTradeBg.getId());
                history.setCurrentstatus(stockTradeBg.getCurrentStatus().shortValue());
                history.setSysDate(new Date());
                history.setHistoryobj(JSON.toJSONString(old));
                stockTradeHistoryMapper.insert(history);

                WFAudit wfAudit = new WFAudit();
                wfAudit.setId(GuidHelper.getGuid());
                wfAudit.setZxly(stockTrade.getBgly());

                wfAudit.setProjectid(id);
                wfAudit.setSysDate(new Date());
                wfAudit.setSysUpdDate(new Date());
                wfAudit.setCurrentStatus(stockTrade.getCurrentStatus());
                wFAuditMapper.insert(wfAudit);
            }
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

        Map<String, Object> map = page.getQueryParameter();

        //获取过滤条件
        systemUserService.getSelectInfo(map);
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<StockTrade> list = stockTradeMapper.getStockTradeList(map);

        PageInfo<StockTrade> pageInfo = new PageInfo<StockTrade>(list);

        PagingEntity<StockTrade> result = new PagingEntity<>(pageInfo);
        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getStockTradeCancelList(Page page) {
        Map<String, Object> map = page.getQueryParameter();

        //获取过滤条件
        systemUserService.getSelectInfo(map);

        PageHelper.startPage(page.getPageNo(), page.getPageSize());
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

    //打印
    @Override
    public void printHt(String id, HttpServletResponse response){

        try{

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-disposition", "Attachment;filename=" + URLEncoder.encode("万年县存量房买卖合同.docx", "utf-8"));
            OutputStream os = response.getOutputStream();

            creatWord(id,os);
            os.flush();
            os.close();

        }catch (Exception e) {
            e.printStackTrace();
        }


    }

    //预览
    @Override
    public void previewHt(String id, HttpServletResponse response){


        try {

            String sourcePath = grandeflorumProperties.getUploadFolder()+"ht";
            String filePath = sourcePath+"/"+id+".pdf";
            File file1 = new File(filePath);



            OutputStream os = response.getOutputStream();
//            if(file1.exists()){
//                os.write(FileUtils.readFileToByteArray(file1));
//            }else{
                creatWord(id,null);

                File file2 = new File(filePath);
                os.write(FileUtils.readFileToByteArray(file2));
//            }

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-disposition", "Attachment;filename=" + URLEncoder.encode(id+".pdf", "utf-8"));
            os.flush();
            os.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public File creatWord(String id,OutputStream os){

        String sourcePath = grandeflorumProperties.getUploadFolder()+"ht";
        File file = new File(sourcePath);

        if (!file.exists()) {
            file.mkdirs();
        }
        String fileSavePath = sourcePath+"/"+id+".docx";
        File file1 = new File(fileSavePath);

        try{
//            if(file1.exists()){
//                if(os!=null){
//                    os.write(FileUtils.readFileToByteArray(file1));
//                }
//                office2PDF.office2PDF(sourcePath+"/"+id+".docx",sourcePath+"/"+id+".pdf",grandeflorumProperties.getOpenoffice());
//
//            }else{

                StockTrade stockTrade = stockTradeMapper.selectByPrimaryKey(id);
                //读入流中
                String path = this.getClass().getResource("/").getPath()+ "templates/万年县存量房买卖合同.docx";
                //新建一个word文档
                XWPFDocument doc = new XWPFDocument(new FileInputStream(path));
                XWPFDocument docTemp = new XWPFDocument(new FileInputStream(path));

                Map<String, Object> params = new HashMap<String, Object>();

                //生成二维码
                QrCodeUtil.createQrCode(grandeflorumProperties.getQrCodePath()+"?id="+id+"&type=2",sourcePath+"/",id+".png");

                getParams(params,id);

                XwpfTUtil xwpfTUtil = new XwpfTUtil();
                xwpfTUtil.createFooter(doc,id,sourcePath+"/"+id+".png");
                xwpfTUtil.replaceInPara(doc, params,id,sourcePath+"/"+id+".png");
                xwpfTUtil.setStyle(docTemp,doc,params);

                doc.write(new FileOutputStream(fileSavePath));
                if(os!=null){
                    doc.write(os);
                }

                office2PDF.office2PDF(sourcePath+"/"+id+".docx",sourcePath+"/"+id+".pdf",grandeflorumProperties.getOpenoffice());
//            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return file1;
    }

    @Override
    public void getParams(Map<String, Object> params,String id){

        StockTrade stockTrade = stockTradeMapper.getStockTradeById(id);

        params.put("htbh", NoNullString(stockTrade.getHtbah()));
        params.put("cmr", NoNullString(stockTrade.getJf()));
        params.put("msr", NoNullString(stockTrade.getYf()));

        //20200115新增
        params.put("jfyzbm",NoNullString(stockTrade.getJfyzbm()));
        params.put("yfyzbm",NoNullString(stockTrade.getYfyzbm()));
        params.put("bzsj", DateUtils.DateToString(stockTrade.getBzsj()));


        params.put("yfsfksj", DateUtils.DateToString(stockTrade.getYfsfksj()));

        params.put("yfsfkje",stockTrade.getYfsfkje()!=null? DoubleToString(stockTrade.getYfsfkje()):"  ");
        if(stockTrade.getYfsfkje()!=null&&stockTrade.getYfsfkje()>0){
            params.put("yfsfkjedx",NumberToCNUtils.convert(BigDecimal.valueOf(stockTrade.getYfsfkje())));
        }else{
            params.put("yfsfkjedx","  ");
        }

        params.put("yfsyfk",stockTrade.getYfsyfk()!=null? DoubleToString(stockTrade.getYfsyfk()):"  ");
        if(stockTrade.getYfsyfk()!=null&&stockTrade.getYfsyfk()>0){
            params.put("yfsyfkdx",NumberToCNUtils.convert(BigDecimal.valueOf(stockTrade.getYfsyfk())));
        }else{
            params.put("yfsyfkdx","  ");
        }

        params.put("yffkfs",NoNullString(stockTrade.getYffkfs()));
        params.put("jylfjnsfyd",NoNullString(stockTrade.getJylfjnsfyd()));

        params.put("htqjrdsrn",DoubleToString(stockTrade.getHtqjrdsrn()));

        params.put("fwydjfsj", DateUtils.DateToString(stockTrade.getFwydjfsj()));
        params.put("jffwqfjqsj", DateUtils.DateToString(stockTrade.getJffwqfjqsj()));

        if(stockTrade.getJfwyj()!=null&&stockTrade.getJfwyj()>0){
            params.put("jfwyj",NumberToCNUtils.convert(BigDecimal.valueOf(stockTrade.getJfwyj())));
        }else{
            params.put("jfwyj","  ");
        }
        params.put("jfwyts",DoubleToString(stockTrade.getJfwyts()));

        if(stockTrade.getYfwyj()!=null&&stockTrade.getYfwyj()>0){
            params.put("yfwyj",NumberToCNUtils.convert(BigDecimal.valueOf(stockTrade.getYfwyj())));
        }else{
            params.put("yfwyj","  ");
        }
        params.put("yfwyts",DoubleToString(stockTrade.getYfwyts()));


        if(stockTrade.getWyfwyj()!=null&&stockTrade.getWyfwyj()>0){
            params.put("wyfwyj",NumberToCNUtils.convert(BigDecimal.valueOf(stockTrade.getWyfwyj())));
        }else{
            params.put("wyfwyj","  ");
        }



        //甲方
        params.put("jflxdz", buildInfo(stockTrade.getJflxdz()));
        if(stockTrade.getJfzjlx()!=null){
            params.put("jfzjlx", NoNullString(systemDictionaryService.getBtachDicName("zjlb",buildInfo(stockTrade.getJfzjlx()))));
        }else{
            params.put("jfzjlx","");
        }
        params.put("jfzjh", buildInfo(stockTrade.getJfzjhm()));
        params.put("jflxdh", buildInfo(stockTrade.getJflxdz()));

        //乙方
        params.put("yflxdz", buildInfo(stockTrade.getYflxdz()));
        if(stockTrade.getYfzjlx()!=null){
            params.put("yfzjlx", NoNullString(systemDictionaryService.getBtachDicName("zjlb",buildInfo(stockTrade.getYfzjlx()))));
        }else{
            params.put("yfzjlx","");
        }
        params.put("yfzjh", buildInfo(stockTrade.getYfzjhm()));
        params.put("yflxdh", buildInfo(stockTrade.getYflxdh()));


        params.put("zj",stockTrade.getZj()!=null? DoubleToString(stockTrade.getZj()):"  ");
        if(stockTrade.getZj()!=null&&stockTrade.getZj()>0){
            params.put("zjdx",NumberToCNUtils.convert(BigDecimal.valueOf(stockTrade.getZj())));
        }else{
            params.put("zjdx","  ");
        }

        params.put("dj",DoubleToString(stockTrade.getDj()));

        params.put("bdcqzh", NoNullString(stockTrade.getBdcqzh()));


        Map<String,String> map = stockTradeMapper.queryHinfoByStockId(id);

        if(map!=null) {
            params.put("zl",map.get("ZL")!=null?map.get("ZL").toString():"");

            String fwyt = systemDictionaryService.getDicName("fwyt",map.get("FWYT")!=null?Integer.parseInt(map.get("FWYT").toString()):null);
            params.put("fwyt",NoNullString(fwyt));

            params.put("jzmj",map.get("SCJZMJ")!=null?String.valueOf(map.get("SCJZMJ")):"");

            String jzjg = systemDictionaryService.getDicName("jzjg",map.get("FWJG1")!=null?Integer.parseInt(map.get("FWJG1").toString()):null);
            params.put("jzjg",NoNullString(jzjg));
        }else{
            params.put("zl","");
            params.put("fwyt","");
            params.put("jzmj","");
            params.put("jzjg","");
        }
    }

    public String buildInfo(String param) {
        if (StrUtil.isNullOrEmpty(param)) {
            return "";
        }

        String result = "";
        String[] str = param.split(",");
        for (int i = 0; i < str.length; i++) {

            if (!StrUtil.isNullOrEmpty(str[i])) {
                result += str[i] + ",";
            }

        }

        if (!StrUtil.isNullOrEmpty(result)) {
            result = result.substring(0, result.length() - 1);
        }


        return result;

    }

    @Override
    public ResponseBo sh(String id){

        stockTradeMapper.sh(id);
        return ResponseBo.ok();
    }

    @Override
    public ResponseBo getHInfo(String hid) {
        StockTrade result=stockTradeMapper.getHInfo(hid);
        return ResponseBo.ok(result);
    }

}
