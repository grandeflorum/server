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
import com.grandeflorum.contract.dao.ContractEntrustmentMapper;
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
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.BreakClear;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;
import com.grandeflorum.contract.dao.StockTradeTemplateMapper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
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

import java.io.FileOutputStream;
import java.io.OutputStream;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HeaderFooter;
import org.apache.poi.ss.usermodel.Footer;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

@Service("StockTradeService")
public class StockTradeServiceImpl extends BaseService<StockTrade> implements StockTradeService {

    @Autowired
    ContractEntrustmentMapper contractEntrustmentMapper;

    @Autowired
    StockTradeMapper stockTradeMapper ;

    @Autowired
    StockTradeTemplateMapper stockTradeTemplateMapper ;

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
            StockTradeTemplate template = stockTradeTemplateMapper.getAllByStocktradeId(id);

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
                            if(template==null){
                                template=new StockTradeTemplate();
                                template.setId(GuidHelper.getGuid());
                                template.setStocktradeid(stockTrade.getId());
                                template.setHt1(stockTrade.getHtbah());
                                stockTradeTemplateMapper.insert(template);
                            }else {
                                template.setHt1(stockTrade.getHtbah());
                                stockTradeTemplateMapper.updateByPrimaryKey(template);
                            }

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
        ContractEntrustment contractEntrustment=stockTrade.getContractEntrustment();
        StockTradeTemplate stockTradeTemplate= stockTrade.getStockTradeTemplate();

        // 合同中甲方和乙方信息 绑定到基本信息中
        if(stockTradeTemplate!=null){
            stockTrade.setJf(stockTradeTemplate.getJf1()); // 甲方
            stockTrade.setJfzjlx(stockTradeTemplate.getJf5()); // 甲方证件类型
            stockTrade.setJfzjhm(stockTradeTemplate.getJf6()); // 甲方证件类型
            stockTrade.setJflxdz(stockTradeTemplate.getJf3()); // 甲方联系地址
            stockTrade.setJflxdh(stockTradeTemplate.getJf7()); // 甲方联系电话
            stockTrade.setYf(stockTradeTemplate.getYf1()); // 乙方
            stockTrade.setYfzjlx(stockTradeTemplate.getYf5()); // 乙方证件类型
            stockTrade.setYfzjhm(stockTradeTemplate.getYf6()); // 乙方证件类型
            stockTrade.setYflxdz(stockTradeTemplate.getYf3()); // 乙方联系地址
            stockTrade.setYflxdh(stockTradeTemplate.getYf7()); // 乙方联系电话
        }

        //合同中合同备案号
        if(stockTrade.getHtbah()!=null){
            contractEntrustment.setHt1(stockTrade.getHtbah().toString());
            stockTradeTemplate.setHt1(stockTrade.getHtbah().toString());

        }
        //合同中转让总价
        if(stockTrade.getZj()!=null){
            if(StrUtil.isNullOrEmpty(stockTradeTemplate.getD2t1())){
                stockTradeTemplate.setD2t1(stockTrade.getZj().toString());
            }

        }
        //合同中转让单价
        if(stockTrade.getDj()!=null){
            if(StrUtil.isNullOrEmpty(stockTradeTemplate.getD2t2())){
                stockTradeTemplate.setD2t2(stockTrade.getDj().toString());
            }

        }
        //合同中房屋结构
        if(stockTrade.getFwjg()!=null){
            if(StrUtil.isNullOrEmpty(stockTradeTemplate.getD1t4())){
                String fwjg = systemDictionaryService.getDicName("fwjg",stockTrade.getFwjg()!=null?Integer.parseInt(stockTrade.getFwjg().toString()):null);
                stockTradeTemplate.setD1t4(fwjg);
            }

        }
        //合同中的不动产单元号
        if(stockTrade.getBdcqzh()!=null){
            if(StrUtil.isNullOrEmpty(stockTradeTemplate.getD1t6())){
                String bdcqzh = stockTrade.getBdcqzh();
                stockTradeTemplate.setD1t6(bdcqzh);
            }

        }
        //合同中办证时间(权证时间)
        if(stockTrade.getDjsj()!=null){
            if(StrUtil.isNullOrEmpty(stockTradeTemplate.getD1t8())){
                String djsj = stockTrade.getDjsj().toString();
                stockTradeTemplate.setD1t8(djsj);
            }

        }


        if (stockTrade.getId() == null) {
            stockTrade.setId(GuidHelper.getGuid());
            stockTrade.setCurrentStatus(0);
            stockTrade.setSysDate(new Date());
            stockTrade.setSysUpdDate(new Date());

            if(user!=null){
                stockTrade.setDjr(user.getId());
            }
            stockTradeTemplate.setId(GuidHelper.getGuid());
            stockTradeTemplate.setStocktradeid(stockTrade.getId());
            contractEntrustment.setId(GuidHelper.getGuid());
            contractEntrustment.setStocktradeid(stockTrade.getId());
            stockTradeMapper.insert(stockTrade);

            stockTradeTemplateMapper.insert(stockTradeTemplate);
            contractEntrustmentMapper.insert(contractEntrustment);
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

            String ceId=contractEntrustmentMapper.getIdByStocktradeId(stockTrade.getId());
            if(StrUtil.isNullOrEmpty(ceId)){
                contractEntrustment.setId(GuidHelper.getGuid());
                contractEntrustment.setStocktradeid(stockTrade.getId());
                contractEntrustmentMapper.insert(contractEntrustment);
            }else {
                contractEntrustment.setId(ceId);
                contractEntrustment.setStocktradeid(stockTrade.getId());
                contractEntrustmentMapper.updateByPrimaryKey(contractEntrustment);
            }

            String temId=stockTradeTemplateMapper.getIdByStocktradeId(stockTrade.getId());
            if(StrUtil.isNullOrEmpty(temId)){
                stockTradeTemplate.setId(GuidHelper.getGuid());
                stockTradeTemplate.setStocktradeid(stockTrade.getId());
                stockTradeTemplateMapper.insert(stockTradeTemplate);
            }else {
                stockTradeTemplate.setId(temId);
                stockTradeTemplate.setStocktradeid(stockTrade.getId());
                stockTradeTemplateMapper.updateByPrimaryKey(stockTradeTemplate);
            }
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
            StockTradeTemplate template=stockTradeTemplateMapper.getAllByStocktradeId(result.getId());
            if(template==null){
                template=new StockTradeTemplate();
            }
            result.setStockTradeTemplate(template);
            ContractEntrustment ce=contractEntrustmentMapper.getAllByStocktradeId(result.getId());


            if(ce==null){
                ce=new ContractEntrustment();
            }

            result.setContractEntrustment(ce);
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

            Example exampleTemplate=new  Example(StockTradeTemplate.class);
            exampleTemplate.createCriteria().andEqualTo("stocktradeid", id);
            stockTradeTemplateMapper.deleteByExample(exampleTemplate);

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
//            response.setContentType("application/vnd.ms-excel");
//            response.setHeader("content-disposition", "Attachment;filename=" + URLEncoder.encode("万年县存量房买卖合同.docx", "utf-8"));
//            OutputStream os = response.getOutputStream();
//
//            creatWord(id,os);
//
//            //File file = creatWord(id,os);
//            os.flush();
//            os.close();

            String sourcePath = grandeflorumProperties.getUploadFolder()+"ht";
            String filePath = sourcePath+"/"+id+".pdf";
            File file1 = new File(filePath);

            OutputStream os = response.getOutputStream();
            creatWord(id,null);
            File file2 = new File(filePath);
            os.write(FileUtils.readFileToByteArray(file2));

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-disposition", "Attachment;filename=" + URLEncoder.encode(id+".pdf", "utf-8"));
            os.flush();
            os.close();
            printFileAction(file2);
        }catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void printCeHt(String id, HttpServletResponse response){
        try{

            String sourcePath = grandeflorumProperties.getUploadFolder()+"ht";
            String filePath = sourcePath+"/"+id+".pdf";
            File file1 = new File(filePath);

            OutputStream os = response.getOutputStream();
            creatCeWord(id,null);
            File file2 = new File(filePath);
            os.write(FileUtils.readFileToByteArray(file2));

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-disposition", "Attachment;filename=" + URLEncoder.encode(id+".pdf", "utf-8"));
            os.flush();
            os.close();
            printFileAction(file2);
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

    //委托预览
    @Override
    public void previewCeHt(String id, HttpServletResponse response){


        try {
            //String ceid=contractEntrustmentMapper.getIdByStocktradeId(id);
            String sourcePath = grandeflorumProperties.getUploadFolder()+"ht";
            String filePath = sourcePath+"/"+id+".pdf";
            File file1 = new File(filePath);

            OutputStream os = response.getOutputStream();
            creatCeWord(id,null);

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
                //xwpfTUtil.createHeader(doc);
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

    public File creatCeWord(String id,OutputStream os){

        String sourcePath = grandeflorumProperties.getUploadFolder()+"ht";
        File file = new File(sourcePath);

        if (!file.exists()) {
            file.mkdirs();
        }
        String fileSavePath = sourcePath+"/"+id+".docx";
        File file1 = new File(fileSavePath);

        try{

            // ContractEntrustment contractEntrustment = contractEntrustmentMapper.selectByPrimaryKey(id);
            //读入流中
            String path = this.getClass().getResource("/").getPath()+ "templates/存量房经济委托合同.docx";
            //新建一个word文档
            XWPFDocument doc = new XWPFDocument(new FileInputStream(path));
            XWPFDocument docTemp = new XWPFDocument(new FileInputStream(path));



            Map<String, Object> params = new HashMap<String, Object>();

            //生成二维码
            QrCodeUtil.createQrCode(grandeflorumProperties.getQrCodePath()+"?id="+id+"&type=2",sourcePath+"/",id+".png");

            getCeParams(params,id);

            XwpfTUtil xwpfTUtil = new XwpfTUtil();

            xwpfTUtil.createFooter(doc,id,sourcePath+"/"+id+".png");
            //xwpfTUtil.createFooter(doc);
            //xwpfTUtil.createHeader(doc);
            xwpfTUtil.replaceInPara(doc, params,id,sourcePath+"/"+id+".png");

            xwpfTUtil.setStyle(docTemp,doc,params);

            xwpfTUtil.createFooter(doc);
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






    //调用打印机
    private void printFileAction(File file) {

        //file = new File("D:/11.txt"); // 获取选择的文件
        // 构建打印请求属性集
        HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
        // 设置打印格式，因为未确定类型，所以选择autosense
        DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
        // 定位默认的打印服务
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
        InputStream fis = null;
        try {
            DocPrintJob job = defaultService.createPrintJob(); // 创建打印作业
            fis = new FileInputStream(file); // 构造待打印的文件流
            DocAttributeSet das = new HashDocAttributeSet();
            Doc doc = new SimpleDoc(fis, flavor, das);
            job.print(doc, pras);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void getParams(Map<String, Object> params,String id){
        try {
        //StockTrade stockTrade = stockTradeMapper.getStockTradeById(id);
        StockTradeTemplate template = stockTradeTemplateMapper.getAllByStocktradeId(id);

//        params.put("htbh", NoNullString(stockTrade.getHtbah()));
//        params.put("cmr", NoNullString(stockTrade.getJf()));
//        params.put("msr", NoNullString(stockTrade.getYf()));

        //20200319 大改版
        //合同开头
        params.put("ht1", NoNullString(template.getHt1()));
        params.put("ht2", NoNullString(template.getHt2()));
        params.put("ht3", NoNullString(template.getHt3()));
        //甲方
        params.put("jf1", NoNullString(template.getJf1()));
        params.put("jf2", NoNullString(template.getJf2()));
        params.put("jf3", NoNullString(template.getJf3()));
        params.put("jf4", NoNullString(template.getJf4()));
        params.put("jf5", NoNullString(template.getJf5()));
        params.put("jf6", NoNullString(template.getJf6()));
        params.put("jf7", NoNullString(template.getJf7()));
        params.put("jf8", NoNullString(template.getJf8()));
        params.put("jf9", NoNullString(template.getJf9()));
        params.put("jf10", NoNullString(template.getJf10()));
        params.put("jf11", NoNullString(template.getJf11()));
        params.put("jf12", NoNullString(template.getJf12()));
        params.put("jf13", NoNullString(template.getJf13()));
        params.put("jf14", NoNullString(template.getJf14()));
        params.put("jf15", NoNullString(template.getJf15()));
        params.put("jf16", NoNullString(template.getJf16()));
        params.put("jf17", NoNullString(template.getJf17()));
        params.put("jf18", NoNullString(template.getJf18()));
        
        //乙方
        params.put("yf1", NoNullString(template.getYf1()));
        params.put("yf2", NoNullString(template.getYf2()));
        params.put("yf3", NoNullString(template.getYf3()));
        params.put("yf4", NoNullString(template.getYf4()));
        params.put("yf5", NoNullString(template.getYf5()));
        params.put("yf6", NoNullString(template.getYf6()));
        params.put("yf7", NoNullString(template.getYf7()));
        params.put("yf8", NoNullString(template.getYf8()));
        params.put("yf9", NoNullString(template.getYf9()));
        params.put("yf10", NoNullString(template.getYf10()));
        params.put("yf11", NoNullString(template.getYf11()));
        params.put("yf12", NoNullString(template.getYf12()));
        params.put("yf13", NoNullString(template.getYf13()));
        params.put("yf14", NoNullString(template.getYf14()));
        params.put("yf15", NoNullString(template.getYf15()));
        params.put("yf16", NoNullString(template.getYf16()));
        params.put("yf17", NoNullString(template.getYf17()));
        params.put("yf18", NoNullString(template.getYf18()));

        //第一条
        params.put("d1t1", NoNullString(template.getD1t1()));
        params.put("d1t2", NoNullString(template.getD1t2()));
        params.put("d1t3", NoNullString(template.getD1t3()));
        params.put("d1t4", NoNullString(template.getD1t4()));
        params.put("d1t5", NoNullString(template.getD1t5()));
        params.put("d1t6", NoNullString(template.getD1t6()));
        params.put("d1t7", NoNullString(template.getD1t7()));
        //params.put("d1t8", NoNullString(template.getD1t8()));
        if(template.getD1t8()!=null){

            String time=DateUtils.StringToDateString(template.getD1t8());
            params.put("d1t8",NoNullString(time));

        }else {
            params.put("d1t8"," ");
        }
        params.put("d1t9", NoNullString(template.getD1t9()));
        params.put("d1t10", NoNullString(template.getD1t10()));

        //第二条
        if(template.getD2t1()!=null){
            BigDecimal dx = new BigDecimal(template.getD2t1());
            params.put("zjdx",NumberToCNUtils.convert(dx));
        }else {
            params.put("zjdx","    ");
        }
        params.put("d2t1", NoNullString(template.getD2t1()));

        params.put("d2t2", NoNullString(template.getD2t2()));

        //第三条
        params.put("d3t1", NoNullString(template.getD3t1()));

        if(template.getD3t2()!=null){
            BigDecimal dx = new BigDecimal(template.getD3t2());
            params.put("jgfkdx",NumberToCNUtils.convert(dx));
        }else {
            params.put("jgfkdx","    ");
        }
        params.put("d3t2", NoNullString(template.getD3t2()));
        params.put("d3t3", NoNullString(template.getD3t3()));
        params.put("d3t4", NoNullString(template.getD3t4()));
        params.put("d3t5", NoNullString(template.getD3t5()));
        //params.put("d3t6", NoNullString(template.getD3t6()));
        if(template.getD3t6()!=null){
            String time=DateUtils.StringToDateString(template.getD3t6());
            params.put("d3t6",NoNullString(time));

        }else {
            params.put("d3t6"," ");
        }

        if(template.getD3t7()!=null){
            BigDecimal dx = new BigDecimal(template.getD3t7());
            params.put("ajsfkdx",NumberToCNUtils.convert(dx));
        }else {
            params.put("ajsfkdx","    ");
        }
        params.put("d3t7", NoNullString(template.getD3t7()));

        if(template.getD3t8()!=null){
            BigDecimal dx = new BigDecimal(template.getD3t8());
            params.put("ajyfkdx",NumberToCNUtils.convert(dx));
        }else {
            params.put("ajyfkdx","    ");
        }
        params.put("d3t8", NoNullString(template.getD3t8()));
        params.put("d3t9", NoNullString(template.getD3t9()));
        params.put("d3t10", NoNullString(template.getD3t10()));
        params.put("d3t11", NoNullString(template.getD3t11()));
        params.put("d3t12", NoNullString(template.getD3t12()));
        params.put("d3t13", NoNullString(template.getD3t13()));

        if(template.getD3t14()!=null){
            BigDecimal dx = new BigDecimal(template.getD3t14());
            params.put("ycxfkdx",NumberToCNUtils.convert(dx));
        }else {
            params.put("ycxfkdx","    ");
        }
        params.put("d3t14", NoNullString(template.getD3t14()));
        params.put("d3t15", NoNullString(template.getD3t15()));
        params.put("d3t16", NoNullString(template.getD3t16()));

        if(template.getD3t16()!=null){
            BigDecimal dx = new BigDecimal(template.getD3t16());
            params.put("fqsfkdx",NumberToCNUtils.convert(dx));
        }else {
            params.put("fqsfkdx","    ");
        }
        params.put("d3t17", NoNullString(template.getD3t17()));

        if(template.getD3t18()!=null){
            BigDecimal dx = new BigDecimal(template.getD3t18());
            params.put("fqyfkdx",NumberToCNUtils.convert(dx));
        }else {
            params.put("fqyfkdx","    ");
        }
        params.put("d3t18", NoNullString(template.getD3t18()));
        params.put("d3t19", NoNullString(template.getD3t19()));

        if(template.getD3t20()!=null){
            BigDecimal dx = new BigDecimal(template.getD3t20());
            params.put("ajtgsfkdx",NumberToCNUtils.convert(dx));
        }else {
            params.put("ajtgsfkdx","    ");
        }
        params.put("d3t20", NoNullString(template.getD3t20()));
        params.put("d3t21", NoNullString(template.getD3t21()));

        if(template.getD3t22()!=null){
            BigDecimal dx = new BigDecimal(template.getD3t22());
            params.put("ajtgyfkdx",NumberToCNUtils.convert(dx));
        }else {
            params.put("ajtgyfkdx","    ");
        }
        params.put("d3t22", NoNullString(template.getD3t22()));
        params.put("d3t23", NoNullString(template.getD3t23()));
        params.put("d3t24", NoNullString(template.getD3t24()));
        params.put("d3t25", NoNullString(template.getD3t25()));
        params.put("d3t26", NoNullString(template.getD3t26()));
        params.put("d3t27", NoNullString(template.getD3t27()));

        //第四条
        params.put("d4t1", NoNullString(template.getD4t1()));
        params.put("d4t2", NoNullString(template.getD4t2()));

        //第五条
        params.put("d5t1", NoNullString(template.getD5t1()));

        //第六条
        //params.put("d6t1", NoNullString(template.getD6t1()));
        if(template.getD6t1()!=null){

            String time=DateUtils.StringToDateString(template.getD6t1());
            params.put("d6t1",NoNullString(time));

        }else {
            params.put("d6t1"," ");
        }
        //params.put("d6t2", NoNullString(template.getD6t2()));
        if(template.getD6t2()!=null){

            String time=DateUtils.StringToDateString(template.getD6t2());
            params.put("d6t2",NoNullString(time));

        }else {
            params.put("d6t2"," ");
        }

        //第七条
        params.put("d7t1", NoNullString(template.getD7t1()));
        params.put("d7t2", NoNullString(template.getD7t2()));
        params.put("d7t3", NoNullString(template.getD7t3()));
        params.put("d7t4", NoNullString(template.getD7t4()));

        //第八条
        params.put("d8t1", NoNullString(template.getD8t1()));
        params.put("d8t2", NoNullString(template.getD8t2()));
        params.put("d8t3", NoNullString(template.getD8t3()));
        params.put("d8t4", NoNullString(template.getD8t4()));

        //第八条
        params.put("d8t1", NoNullString(template.getD8t1()));
        params.put("d8t2", NoNullString(template.getD8t2()));
        params.put("d8t3", NoNullString(template.getD8t3()));
        params.put("d8t4", NoNullString(template.getD8t4()));

        //第九条
        params.put("d9t1", NoNullString(template.getD9t1()));
        params.put("d9t2", NoNullString(template.getD9t2()));
        params.put("d9t3", NoNullString(template.getD9t3()));
        params.put("d9t4", NoNullString(template.getD9t4()));

        //第十条
        params.put("d10t1", NoNullString(template.getD10t1()));
        params.put("d10t2", NoNullString(template.getD10t2()));
        params.put("d10t3", NoNullString(template.getD10t3()));

        //第十一条
        params.put("d11t1", NoNullString(template.getD11t1()));
        params.put("d11t2", NoNullString(template.getD11t2()));

        //第十二条
        params.put("d12t1", NoNullString(template.getD12t1()));
        params.put("d12t2", NoNullString(template.getD12t2()));

        //结尾签章
        params.put("qz1", NoNullString(template.getQz1()));
        params.put("qz2", NoNullString(template.getQz2()));
        params.put("qz3", NoNullString(template.getQz3()));
        if(template.getQz4()!=null){

            String time=DateUtils.StringToDateString(template.getQz4());
            params.put("qz4",NoNullString(time));

        }else {
            params.put("qz4"," ");
        }
        //params.put("qz4", NoNullString(template.getQz4()));
        params.put("qz5", NoNullString(template.getQz5()));
        params.put("qz6", NoNullString(template.getQz6()));
        params.put("qz7", NoNullString(template.getQz7()));
        //params.put("qz8", NoNullString(template.getQz8()));
        if(template.getQz8()!=null){
            String time=DateUtils.StringToDateString(template.getQz8());
            params.put("qz8",NoNullString(time));
        }else {
            params.put("qz8"," ");
        }

        } catch (Exception e) {
            e.printStackTrace();
        }
        //20200115新增
//        params.put("jfyzbm",NoNullString(stockTrade.getJfyzbm()));
//        params.put("yfyzbm",NoNullString(stockTrade.getYfyzbm()));
//        params.put("bzsj", DateUtils.DateToString(stockTrade.getBzsj()));
//
//
//        params.put("yfsfksj", DateUtils.DateToString(stockTrade.getYfsfksj()));
//
//        params.put("yfsfkje",stockTrade.getYfsfkje()!=null? DoubleToString(stockTrade.getYfsfkje()):"  ");
//        if(stockTrade.getYfsfkje()!=null&&stockTrade.getYfsfkje()>0){
//            params.put("yfsfkjedx",NumberToCNUtils.convert(BigDecimal.valueOf(stockTrade.getYfsfkje())));
//        }else{
//            params.put("yfsfkjedx","  ");
//        }
//
//        params.put("yfsyfk",stockTrade.getYfsyfk()!=null? DoubleToString(stockTrade.getYfsyfk()):"  ");
//        if(stockTrade.getYfsyfk()!=null&&stockTrade.getYfsyfk()>0){
//            params.put("yfsyfkdx",NumberToCNUtils.convert(BigDecimal.valueOf(stockTrade.getYfsyfk())));
//        }else{
//            params.put("yfsyfkdx","  ");
//        }
//
//
//        params.put("jylfjnsfyd",NoNullString(stockTrade.getJylfjnsfyd()));
//
//        params.put("htqjrdsrn",DoubleToString(stockTrade.getHtqjrdsrn()));
//
//        params.put("fwydjfsj", DateUtils.DateToString(stockTrade.getFwydjfsj()));
//        params.put("jffwqfjqsj", DateUtils.DateToString(stockTrade.getJffwqfjqsj()));



        //20200305 新增改动
//        params.put("jgfkfs","1");//交割房款方式 默认为 1
//        params.put("zyjjfs","1");//争议的解决方式 默认为 1
//        params.put("ycxfksm","一次性付清");//一次性付款说明 默认为 一次性付清
//        params.put("yffkfs",NoNullString(stockTrade.getYffkfs()));//乙方付款方式
//        params.put("jfwyj",NoNullString(stockTrade.getJfwyj()));//甲方违约金
//        params.put("jfwyts",NoNullString(stockTrade.getJfwyts()));//甲方违约天数
//        params.put("yfwyj",NoNullString(stockTrade.getYfwyj()));//乙方违约金
//        params.put("yfwyts",NoNullString(stockTrade.getYfwyts()));//乙方违约天数
//
//
//        params.put("wyclfs",NoNullString(stockTrade.getWyclfs()));//违约处理方式
//        params.put("wyfwyj",NoNullString(stockTrade.getWyfwyj()));//违约方违约金



        //20200128新增
        // String fwyt = systemDictionaryService.getDicName("fwyt",map.get("FWYT")!=null?Integer.parseInt(map.get("FWYT").toString()):null);
        // params.put("fwyt",NoNullString(fwyt));
//        String htfwyt = systemDictionaryService.getDicName("htfwyt",stockTrade.getHtfwyt()!=null?Integer.parseInt(stockTrade.getHtfwyt().toString()):null);
//        String hthx = systemDictionaryService.getDicName("hthx",stockTrade.getHthx()!=null?Integer.parseInt(stockTrade.getHthx().toString()):null);
//        boolean status = htfwyt.contains("住宅");
//        if (status){
//            params.put("htfwyt",NoNullString(htfwyt));
//            params.put("hthx",NoNullString(",户型为"+hthx));
//        }else{
//            params.put("htfwyt",NoNullString(htfwyt));
//            params.put("hthx","   ");
//        }

        //params.put("htfwyt",NoNullString(systemDictionaryService.getBtachDicName("htfwyt",stockTrade.getHtfwyt().toString())));
        //params.put("hthx", (stockTrade.getHthx()).toString());

//        String fwjg = systemDictionaryService.getDicName("fwjg",stockTrade.getFwjg()!=null?Integer.parseInt(stockTrade.getFwjg().toString()):null);
//        params.put("fwjg",NoNullString(fwjg));
//        //params.put("fwjg", (stockTrade.getFwjg()).toString());
//
//        //甲方
//        params.put("jflxdz", buildInfo(stockTrade.getJflxdz()));
//        if(stockTrade.getJfzjlx()!=null){
//            params.put("jfzjlx", NoNullString(systemDictionaryService.getBtachDicName("zjlb",buildInfo(stockTrade.getJfzjlx()))));
//        }else{
//            params.put("jfzjlx","");
//        }
//        params.put("jfzjh", buildInfo(stockTrade.getJfzjhm()));
//        params.put("jflxdh", buildInfo(stockTrade.getJflxdh()));
//
//        //乙方
//        params.put("yflxdz", buildInfo(stockTrade.getYflxdz()));
//        if(stockTrade.getYfzjlx()!=null){
//            params.put("yfzjlx", NoNullString(systemDictionaryService.getBtachDicName("zjlb",buildInfo(stockTrade.getYfzjlx()))));
//        }else{
//            params.put("yfzjlx","");
//        }
//        params.put("yfzjh", buildInfo(stockTrade.getYfzjhm()));
//        params.put("yflxdh", buildInfo(stockTrade.getYflxdh()));
//
//
//        params.put("zj",stockTrade.getZj()!=null? DoubleToString(stockTrade.getZj()):"  ");
//        if(stockTrade.getZj()!=null&&stockTrade.getZj()>0){
//            params.put("zjdx",NumberToCNUtils.convert(BigDecimal.valueOf(stockTrade.getZj())));
//        }else{
//            params.put("zjdx","  ");
//        }
//
//        params.put("dj",DoubleToString(stockTrade.getDj()));
//
//        params.put("bdcqzh", NoNullString(stockTrade.getBdcqzh()));


        //Map<String,String> map = stockTradeMapper.queryHinfoByStockId(id);

        //if(map!=null) {
         //   params.put("zl",map.get("ZL")!=null?map.get("ZL").toString():"");
//
//            // String fwyt = systemDictionaryService.getDicName("fwyt",map.get("FWYT")!=null?Integer.parseInt(map.get("FWYT").toString()):null);
//            // params.put("fwyt",NoNullString(fwyt));
//
//            params.put("jzmj",map.get("SCJZMJ")!=null?String.valueOf(map.get("SCJZMJ")):"");
//
//            //String jzjg = systemDictionaryService.getDicName("jzjg",map.get("FWJG1")!=null?Integer.parseInt(map.get("FWJG1").toString()):null);
//            // params.put("jzjg",NoNullString(jzjg));
//        }else{
//            params.put("zl","");
//            //params.put("fwyt","");
//            params.put("jzmj","");
//            //params.put("jzjg","");
//        }
    }

    @Override
    public void getCeParams(Map<String, Object> params,String id){
        try {

            ContractEntrustment template = contractEntrustmentMapper.getContractEntrustmentByStocktradeId(id);


            //20200319 大改版
            //合同开头
            params.put("ht1", NoNullString(template.getHt1()));
            //甲方
            params.put("jf1", NoNullString(template.getJf1()));
            params.put("jf2", NoNullString(template.getJf2()));
            params.put("jf3", NoNullString(template.getJf3()));
            params.put("jf4", NoNullString(template.getJf4()));
            params.put("jf5", NoNullString(template.getJf5()));
            params.put("jf6", NoNullString(template.getJf6()));
            params.put("jf7", NoNullString(template.getJf7()));
            params.put("jf8", NoNullString(template.getJf8()));
            params.put("jf9", NoNullString(template.getJf9()));

            if(template.getIsjfsfzzh()==null||template.getIsjfsfzzh()!=1){
                params.put("isjfsfzzh","☐");
            }else {
                params.put("isjfsfzzh","☑");
            }

            if(template.getIsjfhzh()==null||template.getIsjfhzh()!=1){
                params.put("isjfhzh","☐");
            }else {
                params.put("isjfhzh","☑");
            }

            if(template.getIsjfyyzz()==null||template.getIsjfyyzz()!=1){
                params.put("isjfyyzz","☐");
            }else {
                params.put("isjfyyzz","☑");
            }

            //乙方
            params.put("yf1", NoNullString(template.getYf1()));
            params.put("yf2", NoNullString(template.getYf2()));
            params.put("yf3", NoNullString(template.getYf3()));
            params.put("yf4", NoNullString(template.getYf4()));
            params.put("yf5", NoNullString(template.getYf5()));
            params.put("yf6", NoNullString(template.getYf6()));
            params.put("yf7", NoNullString(template.getYf7()));
            params.put("yf8", NoNullString(template.getYf8()));
            params.put("yf9", NoNullString(template.getYf9()));

            if(template.getIsyfsfzzh()==null||template.getIsyfsfzzh()!=1){
                params.put("isyfsfzzh","☐");
            }else {
                params.put("isyfsfzzh","☑");
            }

            if(template.getIsyfhzh()==null||template.getIsyfhzh()!=1){
                params.put("isyfhzh","☐");
            }else {
                params.put("isyfhzh","☑");
            }

            if(template.getIsyfyyzz()==null||template.getIsyfyyzz()!=1){
                params.put("isyfyyzz","☐");
            }else {
                params.put("isyfyyzz","☑");
            }

            //第一条
            params.put("d1t1", NoNullString(template.getD1t1()));

            //第二条
            params.put("d2t1", NoNullString(template.getD2t1()));

            //第三条
            params.put("d3t1", NoNullString(template.getD3t1()));
            params.put("d3t2", NoNullString(template.getD3t2()));


            //第四条
            if(template.getD4t1()!=null){
                String time=DateUtils.StringToDateString(template.getD4t1());
                params.put("d4t1",NoNullString(time));
            }else {
                params.put("d4t1"," ");
            }
            if(template.getD4t2()!=null){
                String time=DateUtils.StringToDateString(template.getD4t2());
                params.put("d4t2",NoNullString(time));
            }else {
                params.put("d4t2"," ");
            }

            if(template.getIscs()==null||template.getIscs()!=1){
                params.put("iscs","☐");
            }else {
                params.put("iscs","☑");
            }

            if(template.getIscz()==null||template.getIscz()!=1){
                params.put("iscz","☐");
            }else {
                params.put("iscz","☑");
            }

            if(template.getIscz()==null||template.getIscg()!=1){
                params.put("iscg","☐");
            }else {
                params.put("iscg","☑");
            }
            if(template.getIscz()==null||template.getIscz1()!=1){
                params.put("iscz1","☐");
            }else {
                params.put("iscz1","☑");
            }

            if(template.getIscz()==null||template.getIszh()!=1){
                params.put("iszh","☐");
            }else {
                params.put("iszh","☑");
            }


            //第六条
            params.put("d6t1", NoNullString(template.getD6t1()));
            params.put("d6t2", NoNullString(template.getD6t2()));
            if(template.getD6t3()!=null){
                BigDecimal dx = new BigDecimal(template.getD6t3());
                params.put("d6t3",NumberToCNUtils.convert(dx));
            }else {
                params.put("d6t3","    ");
            }
            params.put("d6t4", NoNullString(template.getD6t4()));
            params.put("d6t5", NoNullString(template.getD6t5()));
            //第七条


            //第八条


            //第八条


            //第九条
            params.put("d9t1", NoNullString(template.getD9t1()));
            params.put("d9t2", NoNullString(template.getD9t2()));


            //第十条


            //第十一条


            //第十二条
            params.put("d12t1", NoNullString(template.getD12t1()));

            //附件一
            params.put("fj1jw1", NoNullString(template.getFj1jw1()));
            params.put("fj1jw2", NoNullString(template.getFj1jw2()));
            params.put("fj1jw3", NoNullString(template.getFj1jw3()));
            params.put("fj1jw4", NoNullString(template.getFj1jw4()));
            params.put("fj1jw5", NoNullString(template.getFj1jw5()));
            params.put("fj1jw6", NoNullString(template.getFj1jw6()));
            params.put("fj1jw7", NoNullString(template.getFj1jw7()));
            params.put("fj1jw8", NoNullString(template.getFj1jw8()));
            params.put("fj1jw9", NoNullString(template.getFj1jw9()));
            params.put("fj1jw10", NoNullString(template.getFj1jw10()));
            params.put("fj1jw11", NoNullString(template.getFj1jw11()));
            params.put("fj1jw12", NoNullString(template.getFj1jw12()));
            params.put("fj1jw13", NoNullString(template.getFj1jw13()));
            params.put("fj1jw14", NoNullString(template.getFj1jw14()));
            params.put("fj1jw15", NoNullString(template.getFj1jw15()));
            params.put("fj1jw16", NoNullString(template.getFj1jw16()));
            params.put("fj1jw17", NoNullString(template.getFj1jw17()));
            params.put("fj1jw18", NoNullString(template.getFj1jw18()));

            //结尾签章
            params.put("qz1", NoNullString(template.getQz1()));
            params.put("qz2", NoNullString(template.getQz2()));
            params.put("qz3", NoNullString(template.getQz3()));
            params.put("qz4", NoNullString(template.getQz4()));
            if(template.getQz5()!=null){
                String time=DateUtils.StringToDateString(template.getQz5());
                params.put("qz5",NoNullString(time));
            }else {
                params.put("qz5"," ");
            }

            params.put("qz5", NoNullString(template.getQz5()));
            params.put("qz6", NoNullString(template.getQz6()));
            params.put("qz7", NoNullString(template.getQz7()));
            params.put("qz8", NoNullString(template.getQz8()));
            if(template.getQz9()!=null){
                String time=DateUtils.StringToDateString(template.getQz9());
                params.put("qz9",NoNullString(time));
            }else {
                params.put("qz9"," ");
            }

        } catch (Exception e) {
            e.printStackTrace();
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
        int currentStatus=stockTradeMapper.getShZtById(id);
        if(currentStatus==0){
            stockTradeMapper.sh(id);
        }else {
            stockTradeMapper.xgsh(id);
        }
        return ResponseBo.ok();
    }

    @Override
    public ResponseBo getHInfo(String hid) {
        StockTrade result=stockTradeMapper.getHInfo(hid);
        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getWFAuditListByProjectid(Page page) {

        Map<String, Object> map = page.getQueryParameter();

        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<WFAudit> list = wFAuditMapper.getWFAuditListByProjectid(map);

        PageInfo<WFAudit> pageInfo = new PageInfo<WFAudit>(list);

        PagingEntity<WFAudit> result = new PagingEntity<>(pageInfo);
        return ResponseBo.ok(result);
    }

}
