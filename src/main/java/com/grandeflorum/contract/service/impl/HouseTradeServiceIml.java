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
import com.grandeflorum.contract.dao.*;
import com.grandeflorum.contract.domain.*;
import com.grandeflorum.contract.service.HouseTradeService;
import com.grandeflorum.contract.service.StockTradeService;
import com.grandeflorum.practitioner.dao.CompanyMapper;
import com.grandeflorum.practitioner.domain.Company;
import com.grandeflorum.project.dao.WFAuditMapper;
import com.grandeflorum.project.domain.AuditParam;
import com.grandeflorum.project.domain.Project;
import com.grandeflorum.project.domain.WFAudit;

import com.grandeflorum.system.domain.SystemUser;
import com.grandeflorum.system.service.SystemDictionaryService;
import com.grandeflorum.system.service.SystemUserService;
import net.sf.ehcache.CacheManager;
import org.apache.commons.io.FileUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.xmlbeans.XmlCursor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.print.Win32PrintService;
import tk.mybatis.mapper.entity.Example;

import javax.print.DocPrintJob;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.PrinterJob;
import java.io.*;
import java.io.File;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.print.*;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
//import javax.print.PrintService;
//import javax.print.PrintServiceLookup;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.DocAttributeSet;

import java.util.Date;
import java.util.List;

import static com.grandeflorum.common.util.StrUtil.DoubleToString;
import static com.grandeflorum.common.util.StrUtil.NoNullString;






@Service("HouseTradeService")
public class HouseTradeServiceIml extends BaseService<HouseTrade> implements HouseTradeService {

    @Autowired
    HouseTradeMapper houseTradeMapper;

    @Autowired
    CashSalesTemplateMapper cashSalesTemplateMapper;

    @Autowired
    AdvanceSalesTemplateMapper advanceSalesTemplateMapper;

    @Autowired
    HouseTradeHistoryMapper houseTradeHistoryMapper;

    @Autowired
    GrandeflorumProperties grandeflorumProperties;

    @Autowired
    WFAuditMapper wFAuditMapper;

    @Autowired
    ContractnumMapper contractnumMapper;

    @Autowired
    RelationShipMapper relationShipMapper;

    @Autowired
    FileInfoService fileInfoService ;

    @Autowired
    ContractTemplateMapper contractTemplateMapper;

    @Autowired
    CompanyMapper companyMapper;

    @Autowired
    SystemDictionaryService systemDictionaryService;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    SystemUserService systemUserService;

    @Autowired
    StockTradeMapper stockTradeMapper ;

    @Autowired
    StockTradeService stockTradeService;

    @Override
    public ResponseBo getHouseTradeHistory(String id){
        List<HouseTradeHistory> list = houseTradeHistoryMapper.getHistoryList(id);

        if(list!=null&&list.size()>0){
            list.stream().forEach(x->x.setHouseTrade((HouseTrade) JSON.parse(x.getHistoryobj())));
        }

        return ResponseBo.ok(list);
    }

    @Override
    @Transactional
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
                wfAudit.setZxly(wf.getZxly());
                wfAudit.setProjectid(id);
                wfAudit.setSysDate(new Date());
                wfAudit.setSysUpdDate(new Date());
                wfAudit.setIsActive(1);
                wfAudit.setCurrentStatus(houseTrade.getCurrentStatus());
                wFAuditMapper.insert(wfAudit);
                if(wf.getFileInfoList()!=null&&wf.getFileInfoList().size()>0){
                    fileInfoService.updateFileInfoByIds(wf.getFileInfoList(),wfAudit.getId());
                }
                //备案记录备案时间
                if(houseTrade.getCurrentStatus()==4){
                    houseTrade.setBasj(new Date());
                }
                //合同为已备案状态后可修改为已注销
                if(houseTrade.getCurrentStatus()==5){
                    houseTrade.setIsCancel(1);
                    if(auditParam.getStatus()==2){
                        houseTrade.setIsCancel(2);
                    }

                }else{
                    if(wfAudit.getShjg()==1){
                        houseTrade.setIsPass(1);
                        houseTrade.setCurrentStatus(houseTrade.getCurrentStatus() + 1);

                        if(houseTrade.getCurrentStatus()==4){
                            houseTrade.setHtbah(this.getHTBAH("HouseTrade"));
                            if(houseTrade.getHouseType()==1){
                                CashSalesTemplate cashSalesTemplate = cashSalesTemplateMapper.getAllByHousetradeId(id);
                                if(houseTrade.getHtbah()!=null){
                                    if(cashSalesTemplate==null){
                                        cashSalesTemplate=new CashSalesTemplate();
                                        cashSalesTemplate.setId(GuidHelper.getGuid());
                                        cashSalesTemplate.setHousetradeid(houseTrade.getId());
                                        cashSalesTemplate.setHt1(houseTrade.getHtbah());
                                        cashSalesTemplateMapper.insert(cashSalesTemplate);
                                    }else {
                                        cashSalesTemplate.setHt1(houseTrade.getHtbah().toString());
                                        cashSalesTemplateMapper.updateByPrimaryKey(cashSalesTemplate);
                                    }

                                }

                            }
                            if(houseTrade.getHouseType()==2){
                                AdvanceSalesTemplate advanceSalesTemplate = advanceSalesTemplateMapper.getAllByHousetradeId(id);
                                if(houseTrade.getHtbah()!=null){
                                    if(advanceSalesTemplate==null){
                                        advanceSalesTemplate=new AdvanceSalesTemplate();
                                        advanceSalesTemplate.setId(GuidHelper.getGuid());
                                        advanceSalesTemplate.setHousetradeid(houseTrade.getId());
                                        advanceSalesTemplate.setHt1(houseTrade.getHtbah());
                                        advanceSalesTemplateMapper.insert(advanceSalesTemplate);
                                    }else {
                                        advanceSalesTemplate.setHt1(houseTrade.getHtbah().toString());
                                        advanceSalesTemplateMapper.updateByPrimaryKey(advanceSalesTemplate);
                                    }

                                }
                            }
                        }

                        if(houseTrade.getCurrentStatus()==5){
                            houseTrade.setBasj(new Date());
                        }

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
                }

            }else{
                if(houseTrade.getIsPass()!=null&&houseTrade.getIsPass()==2){
                    houseTrade.setIsPass(1);
                }else{
                    houseTrade.setCurrentStatus(houseTrade.getCurrentStatus() + 1);
                }
            }
            houseTrade.setSysUpdDate(new Date());
            houseTradeMapper.updateByPrimaryKey(houseTrade);
        }

        return ResponseBo.ok();
    }



    @Override
    @Transactional
    public ResponseBo AuditHouseTradeNew(WFAudit wfAudit) {

        HouseTrade houseTrade = houseTradeMapper.selectByPrimaryKey(wfAudit.getProjectid());

        if (StrUtil.isNullOrEmpty(wfAudit.getId())) {
            wfAudit.setId(GuidHelper.getGuid());
        }
        wfAudit.setSysDate(new Date());
        wfAudit.setSysUpdDate(new Date());
        wfAudit.setCurrentStatus(houseTrade.getCurrentStatus());
        wfAudit.setIsActive(1);
        wFAuditMapper.insert(wfAudit);

        //备案记录备案时间
        if (houseTrade.getCurrentStatus() == 4) {
            houseTrade.setBasj(new Date());
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
                houseTrade.setIsPass(1);
                houseTrade.setCurrentStatus(houseTrade.getCurrentStatus() + 1);

                if (houseTrade.getCurrentStatus() == 4) {
                    houseTrade.setHtbah(this.getHTBAH("HouseTrade"));
                }

                if (houseTrade.getCurrentStatus() == 5) {
                    houseTrade.setBasj(new Date());
                }
            }



        } else if (wfAudit.getShjg() == 2) {

            //设置以前的审核位失效
            wFAuditMapper.updateWfActive(mapUpdate);


            //设置历史信息
            houseTrade.setIsPass(2);
            HouseTradeHistory history = new HouseTradeHistory();
            history.setId(GuidHelper.getGuid());
            history.setHousetradeid(houseTrade.getId());
            history.setCurrentstatus(houseTrade.getCurrentStatus().shortValue());
            history.setSysDate(new Date());
            history.setHistoryobj(JSON.toJSONString(houseTrade));
            houseTradeHistoryMapper.insert(history);
        }

        houseTrade.setSysUpdDate(new Date());
        houseTradeMapper.updateByPrimaryKey(houseTrade);


        //合同备案号覆盖合同20200604
        if(houseTrade.getHouseType()==1){
            //获取现售合同信息
            CashSalesTemplate cashSalesTemplate=cashSalesTemplateMapper.getAllByHousetradeId(houseTrade.getId());
            if(cashSalesTemplate==null){
                cashSalesTemplate=new CashSalesTemplate();
                cashSalesTemplate.setId(GuidHelper.getGuid());
                cashSalesTemplate.setHousetradeid(houseTrade.getId());
                if(houseTrade.getHtbah()!=null){
                    cashSalesTemplate.setHt1(houseTrade.getHtbah().toString());
                }
                cashSalesTemplateMapper.insert(cashSalesTemplate);

            }else{
                if(houseTrade.getHtbah()!=null){
                    cashSalesTemplate.setHt1(houseTrade.getHtbah().toString());
                }
                cashSalesTemplateMapper.updateByPrimaryKey(cashSalesTemplate);
            }
        }
        if(houseTrade.getHouseType()==2){
            //预售合同信息保存
            AdvanceSalesTemplate advanceSalesTemplate=advanceSalesTemplateMapper.getAllByHousetradeId(houseTrade.getId());
            if(advanceSalesTemplate==null){
                advanceSalesTemplate=new AdvanceSalesTemplate();
                advanceSalesTemplate.setId(GuidHelper.getGuid());
                advanceSalesTemplate.setHousetradeid(houseTrade.getId());
                if(houseTrade.getHtbah()!=null){
                    advanceSalesTemplate.setHt1(houseTrade.getHtbah().toString());
                }
                advanceSalesTemplateMapper.insert(advanceSalesTemplate);
            }else{
                if(houseTrade.getHtbah()!=null){
                    advanceSalesTemplate.setHt1(houseTrade.getHtbah().toString());
                }
                advanceSalesTemplateMapper.updateByPrimaryKey(advanceSalesTemplate);
            }
        }


        return ResponseBo.ok();
    }

    public String getHTBAH(String type) {

        String result = "";

        SimpleDateFormat df = new SimpleDateFormat("yyMMdd");
        String date = df.format(new Date());
        String num;

        Map<String, Object> map = new HashMap<>();
        map.put("time", date);
        map.put("type", type);

        Contractnum con = this.contractnumMapper.getObjByTypeAndTime(map);

        if (con == null) {
            con = new Contractnum();

            con.setId(GuidHelper.getGuid());
            con.setTime(date);
            con.setType(type);
            con.setMaxnum((short) 1);

            this.contractnumMapper.insert(con);

            num = "1";
        } else {
            num = con.getMaxnum() + 1 + "";

            con.setMaxnum((short) (con.getMaxnum() + 1));

            this.contractnumMapper.updateByPrimaryKey(con);
        }

        if (type.equals("HouseTrade")) {
            result = "01";
        } else if (type.equals("StockTrade")) {
            result = "C01";
        }
        switch (num.length()) {
            case 1:
                num = "000" + num;
                break;
            case 2:
                num = "00" + num;
                break;
            case 3:
                num = "0" + num;
                break;
        }
        result += date + num;

        return result;
    }

    @Override
    public ResponseBo auditHouseTradeById(String id, int type) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("type", type);

        houseTradeMapper.auditHouseTradeById(map);
        return ResponseBo.ok();
    }

    @Override
    @Transactional
    public HouseTrade saveOrUpdateHouseTrade(HouseTrade houseTrade) {

        SystemUser user = EHCacheUtils.getCurrentUser(cacheManager);


        if (houseTrade.getId() == null) {
            houseTrade.setId(GuidHelper.getGuid());
            houseTrade.setCurrentStatus(0);
            houseTrade.setSysDate(new Date());
            houseTrade.setSysUpdDate(new Date());
            if(user!=null){
                houseTrade.setDjr(user.getId());
            }
            houseTradeMapper.insert(houseTrade);
            if(houseTrade.getHouseType()==1){
                //现售合同信息保存
                CashSalesTemplate cashSalesTemplate=houseTrade.getCashSalesTemplate();
                //合同中合同备案号
                if(houseTrade.getHtbah()!=null){
                    cashSalesTemplate.setHt1(houseTrade.getHtbah().toString());
                }

                cashSalesTemplate.setId(GuidHelper.getGuid());
                cashSalesTemplate.setHousetradeid(houseTrade.getId());
                cashSalesTemplateMapper.insert(cashSalesTemplate);
            }
            if(houseTrade.getHouseType()==2){
                //现售合同信息保存
                AdvanceSalesTemplate advanceSalesTemplate=houseTrade.getAdvanceSalesTemplate();
                //合同中合同备案号
                if(houseTrade.getHtbah()!=null){
                    advanceSalesTemplate.setHt1(houseTrade.getHtbah().toString());
                }

                advanceSalesTemplate.setId(GuidHelper.getGuid());
                advanceSalesTemplate.setHousetradeid(houseTrade.getId());
                advanceSalesTemplateMapper.insert(advanceSalesTemplate);
            }

        } else {
            houseTrade.setSysUpdDate(new Date());

            //变更
            if(!StrUtil.isNullOrEmpty(houseTrade.getBg())){

                HouseTrade old = houseTradeMapper.selectByPrimaryKey(houseTrade.getId());
                HouseTrade houseTradeBg = (HouseTrade)old.clone();
                houseTradeBg.setIsCancel(2);
                houseTradeBg.setSysUpdDate(new Date());
                String id = GuidHelper.getGuid();
                houseTradeBg.setId(id);
                houseTradeMapper.insert(houseTradeBg);

                if(houseTradeBg.getHouseType()==1){
                    //现售合同信息保存
                    CashSalesTemplate cashSalesTemplate=houseTrade.getCashSalesTemplate();
                    if(cashSalesTemplate==null){
                        cashSalesTemplate=new CashSalesTemplate();
                    }
                    //合同中合同备案号
                    if(houseTrade.getHtbah()!=null){
                        cashSalesTemplate.setHt1(houseTrade.getHtbah().toString());
                    }

                    cashSalesTemplate.setId(GuidHelper.getGuid());
                    cashSalesTemplate.setHousetradeid(houseTradeBg.getId());
                    cashSalesTemplateMapper.insert(cashSalesTemplate);
                }
                if(houseTradeBg.getHouseType()==2){
                    //预售合同信息保存
                    AdvanceSalesTemplate advanceSalesTemplate=houseTrade.getAdvanceSalesTemplate();
                    //合同中合同备案号
                    if(houseTradeBg.getHtbah()!=null){
                        advanceSalesTemplate.setHt1(houseTradeBg.getHtbah().toString());
                    }

                    advanceSalesTemplate.setId(GuidHelper.getGuid());
                    advanceSalesTemplate.setHousetradeid(houseTradeBg.getId());
                    advanceSalesTemplateMapper.insert(advanceSalesTemplate);
                }

                HouseTradeHistory history = new HouseTradeHistory();
                history.setId(GuidHelper.getGuid());
                history.setHousetradeid(houseTrade.getId());
                history.setCurrentstatus(houseTrade.getCurrentStatus().shortValue());
                history.setSysDate(new Date());
                history.setHistoryobj(JSON.toJSONString(old));
                houseTradeHistoryMapper.insert(history);

                WFAudit wfAudit = new WFAudit();
                wfAudit.setId(GuidHelper.getGuid());
                wfAudit.setZxly(houseTrade.getBgly());

                wfAudit.setProjectid(id);
                wfAudit.setSysDate(new Date());
                wfAudit.setSysUpdDate(new Date());
                wfAudit.setCurrentStatus(houseTrade.getCurrentStatus());
                wFAuditMapper.insert(wfAudit);
            }
            if(houseTrade.getHouseType()==1){
                //现售合同信息保存
                CashSalesTemplate cashSalesTemplate=houseTrade.getCashSalesTemplate();
                //合同中合同备案号
                if(houseTrade.getHtbah()!=null){
                    cashSalesTemplate.setHt1(houseTrade.getHtbah().toString());
                }

                String temId=cashSalesTemplateMapper.getIdByHousetradeId(houseTrade.getId());
                if(StrUtil.isNullOrEmpty(temId)){
                    cashSalesTemplate.setId(GuidHelper.getGuid());
                    cashSalesTemplate.setHousetradeid(houseTrade.getId());
                    cashSalesTemplateMapper.insert(cashSalesTemplate);
                }else {
                    cashSalesTemplate.setId(temId);
                    cashSalesTemplate.setHousetradeid(houseTrade.getId());
                    cashSalesTemplateMapper.updateByPrimaryKey(cashSalesTemplate);
                }
            }
            if(houseTrade.getHouseType()==2){
                //预售合同信息保存
                AdvanceSalesTemplate advanceSalesTemplate=houseTrade.getAdvanceSalesTemplate();
                //合同中合同备案号
                if(houseTrade.getHtbah()!=null){
                    advanceSalesTemplate.setHt1(houseTrade.getHtbah().toString());
                }

                String temId=advanceSalesTemplateMapper.getIdByHousetradeId(houseTrade.getId());
                if(StrUtil.isNullOrEmpty(temId)){
                    advanceSalesTemplate.setId(GuidHelper.getGuid());
                    advanceSalesTemplate.setHousetradeid(houseTrade.getId());
                    advanceSalesTemplateMapper.insert(advanceSalesTemplate);
                }else {
                    advanceSalesTemplate.setId(temId);
                    advanceSalesTemplate.setHousetradeid(houseTrade.getId());
                    advanceSalesTemplateMapper.updateByPrimaryKey(advanceSalesTemplate);
                }
            }


            houseTradeMapper.updateByPrimaryKey(houseTrade);
            //如果更新的话先删除原来关系数据
            relationShipMapper.deleteRelationShipByProjectId(houseTrade.getId());
        }
        //添加存量房相关的人员信息
        if (houseTrade.getRelationShips() != null && houseTrade.getRelationShips().size() > 0) {
            for (RelationShip relationShip : houseTrade.getRelationShips()) {
                relationShip.setId(GuidHelper.getGuid());
                relationShip.setProjectId(houseTrade.id);
                relationShip.setSysDate(new Date());
                relationShip.setSysUpdDate(new Date());
                relationShipMapper.insert(relationShip);
            }
        }
        return houseTrade;
    }

    @Override
    public ResponseBo getHouseTradeById(String id) {
        HouseTrade result = houseTradeMapper.getHouseTradeById(id);
        if (result != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("shjg", 1);
            map.put("projectid", id);
            List<WFAudit> list = wFAuditMapper.getWFAuditList(map);
            result.setWfAuditList(list);


            if(!StrUtil.isNullOrEmpty(result.getHouseId())){
                result.setLjzid( houseTradeMapper.getLjzh(result.getHouseId()));
            }
            result.setRelationShips(relationShipMapper.getRelationShipByProjectId(result.getId()));

            if(result.getHouseType()==null|| result.getHouseType()==0){
                //通过完工验收材料判断预售与现售
                Integer count = houseTradeMapper.checkExistCompletionFile(id);
                result.setHouseType(2);
                if(count>0){
                    result.setHouseType(1);
                }
            }

            CashSalesTemplate cashSalesTemplate=cashSalesTemplateMapper.getAllByHousetradeId(result.getId());
            if(cashSalesTemplate==null){
                cashSalesTemplate=new CashSalesTemplate();
            }
            AdvanceSalesTemplate advanceSalesTemplate=advanceSalesTemplateMapper.getAllByHousetradeId(result.getId());
            if(advanceSalesTemplate==null){
                advanceSalesTemplate=new AdvanceSalesTemplate();
            }

            result.setCashSalesTemplate(cashSalesTemplate);
            result.setAdvanceSalesTemplate(advanceSalesTemplate);
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    /**
     * 通过完工验收材料判断预售与现售
     * @param id
     * @return
     */
    @Override
    public ResponseBo checkExistCompletionFile(String id){
        return ResponseBo.ok(houseTradeMapper.checkExistCompletionFile(id));
    }

    @Override
    public ResponseBo getHouseTradeList(Page page) {
        Map<String, Object> map = page.getQueryParameter();

        //获取过滤条件
        systemUserService.getSelectInfo(map);

        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<HouseTrade> list = houseTradeMapper.getHouseTradeList(map);

        PageInfo<HouseTrade> pageInfo = new PageInfo<HouseTrade>(list);

        PagingEntity<HouseTrade> result = new PagingEntity<>(pageInfo);
        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getHouseTradeCancelList(Page page) {
        Map<String, Object> map = page.getQueryParameter();

        //获取过滤条件
        systemUserService.getSelectInfo(map);

        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<ContractCancel> list = houseTradeMapper.getHouseTradeCancelList(map);

        PageInfo<ContractCancel> pageInfo = new PageInfo<ContractCancel>(list);

        PagingEntity<ContractCancel> result = new PagingEntity<>(pageInfo);
        return ResponseBo.ok(result);
    }


    //打印
    @Override
    public void printHt(String id, HttpServletResponse response){

        try{

//            HouseTrade houseTrade = houseTradeMapper.selectByPrimaryKey(id);
//            String name = "商品房买卖合同（预售）.docx";
//            if(1==houseTrade.getHouseType()) {
//                name = "商品房买卖合同（现售）.docx";
//            }
//            response.setContentType("application/vnd.ms-excel");
//            response.setHeader("content-disposition", "Attachment;filename=" + URLEncoder.encode(name, "utf-8"));
            //OutputStream os = response.getOutputStream();

            //creatWord(id,os);
//            os.flush();
//            os.close();


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

    //生成word
    @Override
    public File creatWord(String id,OutputStream os){

        HouseTrade houseTrade = houseTradeMapper.selectByPrimaryKey(id);
        String name = "商品房买卖合同（预售）.docx";
        if(1==houseTrade.getHouseType()) {
            name = "商品房买卖合同（现售）.docx";
        }

        String sourcePath = grandeflorumProperties.getUploadFolder()+"ht";
        File file = new File(sourcePath);

        if (!file.exists()) {
            file.mkdirs();
        }
        String fileSavePath = sourcePath+"/"+id+".docx";
        File file1 = new File(fileSavePath);

        try{
//            File file2 = new File(sourcePath+"/"+id+"_edit.docx");
//            if(file2.exists()){
//                FileUtils.copyFile(file2, file1);
//                office2PDF.office2PDF(sourcePath+"/"+id+".docx",sourcePath+"/"+id+".pdf",grandeflorumProperties.getOpenoffice());
//            }else{

                //生成二维码
                QrCodeUtil.createQrCode(grandeflorumProperties.getQrCodePath()+"?id="+id+"&type=1",sourcePath+"/",id+".png");

                //读入流中
                String path = this.getClass().getResource("/").getPath()+ "templates/"+name;
                //新建一个word文档
                XWPFDocument doc = new XWPFDocument(new FileInputStream(path));
                XWPFDocument docTemp = new XWPFDocument(new FileInputStream(path));

                Map<String, Object> params = new HashMap<String, Object>();
                if(houseTrade.houseType==1){
                    getCashSalesParams(params,id);
                }
                if(houseTrade.houseType==2){
                    getAdvanceSalesParams(params,id); 
                }
               
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
    public ResponseBo linkH(String id,String hid){

        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("hid",hid);

        houseTradeMapper.linkH(map);

        return ResponseBo.ok();
    }

    @Override
    @Transactional
    public ResponseBo deleteHouseTradeByIds(List<String> ids){
        for (String id:ids){
            houseTradeMapper.deleteByPrimaryKey(id);

            Example exampleAudit = new Example(WFAudit.class);
            exampleAudit.createCriteria().andEqualTo("projectid", id);
            wFAuditMapper.deleteByExample(exampleAudit);

            Example exampleHistory = new Example(HouseTradeHistory.class);
            exampleHistory.createCriteria().andEqualTo("housetradeid", id);
            houseTradeHistoryMapper.deleteByExample(exampleHistory);

            Example exampleRelationShip=new Example(RelationShip.class);
            exampleRelationShip.createCriteria().andEqualTo("projectId", id);
            relationShipMapper.deleteByExample(exampleRelationShip);
        }
        return ResponseBo.ok();
    }

    @Override
    public ResponseBo getHInfo(String hid) {
        HouseTrade result=houseTradeMapper.getHInfo(hid);
        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getEwmCheckInfo(String id,String type){

        Map<String,Object> map = new HashMap<>();

        if("1".equalsIgnoreCase(type)){

            HouseTrade houseTrade = houseTradeMapper.getHouseTradeById(id);
            // getParams(map,id);
            map.put("house",houseTrade);

            return ResponseBo.ok(map);
        }

        // stockTradeService.getParams(map,id);
        //StockTrade tockTrade=stockTradeMapper.getStockTradeById(id);
        map.put("house",stockTradeMapper.getEwmById(id));

        return ResponseBo.ok(map);
    }

        public void getCashSalesParams(Map<String, Object> params,String id){
            CashSalesTemplate cashSalesTemplate=cashSalesTemplateMapper.getAllByHousetradeId(id);

            //合同开头
            params.put("ht1", NoNullString(cashSalesTemplate.getHt1()));
            params.put("ht2", NoNullString(cashSalesTemplate.getHt2()));
            params.put("ht3", NoNullString(cashSalesTemplate.getHt3()));
            //甲方
            params.put("jf1", NoNullString(cashSalesTemplate.getJf1()));
            params.put("jf2", NoNullString(cashSalesTemplate.getJf2()));
            params.put("jf3", NoNullString(cashSalesTemplate.getJf3()));
            params.put("jf4", NoNullString(cashSalesTemplate.getJf4()));
            params.put("jf5", NoNullString(cashSalesTemplate.getJf5()));
            params.put("jf6", NoNullString(cashSalesTemplate.getJf6()));
            params.put("jf7", NoNullString(cashSalesTemplate.getJf7()));
            params.put("jf8", NoNullString(cashSalesTemplate.getJf8()));
            params.put("jf9", NoNullString(cashSalesTemplate.getJf9()));
            params.put("jf10", NoNullString(cashSalesTemplate.getJf10()));
            params.put("jf11", NoNullString(cashSalesTemplate.getJf11()));
            params.put("jf12", NoNullString(cashSalesTemplate.getJf12()));
            params.put("jf13", NoNullString(cashSalesTemplate.getJf13()));
            params.put("jf14", NoNullString(cashSalesTemplate.getJf14()));
            params.put("jf15", NoNullString(cashSalesTemplate.getJf15()));
            params.put("jf16", NoNullString(cashSalesTemplate.getJf16()));

            //乙方
            params.put("yf1", NoNullString(cashSalesTemplate.getYf1()));
            params.put("yf2", NoNullString(cashSalesTemplate.getYf2()));
            params.put("yf3", NoNullString(cashSalesTemplate.getYf3()));
            params.put("yf4", NoNullString(cashSalesTemplate.getYf4()));
            params.put("yf5", NoNullString(cashSalesTemplate.getYf5()));
            if(cashSalesTemplate.getYf6()!=null){

                String time=DateUtils.StringToDateString(cashSalesTemplate.getYf6());
                params.put("yf6",NoNullString(time));

            }else {
                params.put("yf6","         ");
            }
            //params.put("yf6", NoNullString(cashSalesTemplate.getYf6()));
            params.put("yf7", NoNullString(cashSalesTemplate.getYf7()));
            params.put("yf8", NoNullString(cashSalesTemplate.getYf8()));
            params.put("yf9", NoNullString(cashSalesTemplate.getYf9()));
            params.put("yf10", NoNullString(cashSalesTemplate.getYf10()));
            params.put("yf11", NoNullString(cashSalesTemplate.getYf11()));
            params.put("yf12", NoNullString(cashSalesTemplate.getYf12()));
            params.put("yf13", NoNullString(cashSalesTemplate.getYf13()));
            params.put("yf14", NoNullString(cashSalesTemplate.getYf14()));
            if(cashSalesTemplate.getYf15()!=null){

                String time=DateUtils.StringToDateString(cashSalesTemplate.getYf15());
                params.put("yf15",NoNullString(time));

            }else {
                params.put("yf15","         ");
            }
            //params.put("yf15", NoNullString(cashSalesTemplate.getYf15()));
            params.put("yf16", NoNullString(cashSalesTemplate.getYf16()));
            params.put("yf17", NoNullString(cashSalesTemplate.getYf17()));
            params.put("yf18", NoNullString(cashSalesTemplate.getYf18()));
            params.put("yf19", NoNullString(cashSalesTemplate.getYf19()));

            //第一条
            params.put("d1t1", NoNullString(cashSalesTemplate.getD1t1()));
            params.put("d1t2", NoNullString(cashSalesTemplate.getD1t2()));
            params.put("d1t3", NoNullString(cashSalesTemplate.getD1t3()));
            params.put("d1t4", NoNullString(cashSalesTemplate.getD1t4()));
            params.put("d1t5", NoNullString(cashSalesTemplate.getD1t5()));
            params.put("d1t6", NoNullString(cashSalesTemplate.getD1t6()));
            if(cashSalesTemplate.getD1t7()!=null){

                String time=DateUtils.StringToDateString(cashSalesTemplate.getD1t7());
                params.put("d1t7",NoNullString(time));

            }else {
                params.put("d1t7","         ");
            }
            //params.put("d1t7", NoNullString(cashSalesTemplate.getD1t7()));
            params.put("d1t8", NoNullString(cashSalesTemplate.getD1t8()));
            params.put("d1t9", NoNullString(cashSalesTemplate.getD1t9()));
            params.put("d1t10", NoNullString(cashSalesTemplate.getD1t10()));

            //第二条

            params.put("d2t1", NoNullString(cashSalesTemplate.getD2t1()));
            params.put("d2t2", NoNullString(cashSalesTemplate.getD2t2()));

            //第三条
            params.put("d3t1", NoNullString(cashSalesTemplate.getD3t1()));
            params.put("d3t2", NoNullString(cashSalesTemplate.getD3t2()));
            params.put("d3t3", NoNullString(cashSalesTemplate.getD3t3()));
            params.put("d3t4", NoNullString(cashSalesTemplate.getD3t4()));
            params.put("d3t5", NoNullString(cashSalesTemplate.getD3t5()));
            params.put("d3t6", NoNullString(cashSalesTemplate.getD3t6()));
            params.put("d3t7", NoNullString(cashSalesTemplate.getD3t7()));
            params.put("d3t8", NoNullString(cashSalesTemplate.getD3t8()));
            params.put("d3t9", NoNullString(cashSalesTemplate.getD3t9()));
            params.put("d3t10", NoNullString(cashSalesTemplate.getD3t10()));
            params.put("d3t11", NoNullString(cashSalesTemplate.getD3t11()));
            params.put("d3t12", NoNullString(cashSalesTemplate.getD3t12()));
            params.put("d3t13", NoNullString(cashSalesTemplate.getD3t13()));
            params.put("d3t14", NoNullString(cashSalesTemplate.getD3t14()));
            params.put("d3t15", NoNullString(cashSalesTemplate.getD3t15()));
            params.put("d3t16", NoNullString(cashSalesTemplate.getD3t16()));
            params.put("d3t17", NoNullString(cashSalesTemplate.getD3t17()));
            params.put("d3t18", NoNullString(cashSalesTemplate.getD3t18()));


            //第四条
            params.put("d4t1", NoNullString(cashSalesTemplate.getD4t1()));
            params.put("d4t2", NoNullString(cashSalesTemplate.getD4t2()));
            params.put("d4t3", NoNullString(cashSalesTemplate.getD4t3()));
            if(cashSalesTemplate.getD4t4()!=null){

                String time=DateUtils.StringToDateString(cashSalesTemplate.getD4t4());
                params.put("d4t4",NoNullString(time));

            }else {
                params.put("d4t4","         ");
            }
           // params.put("d4t4", NoNullString(cashSalesTemplate.getD4t4()));
            params.put("d4t5", NoNullString(cashSalesTemplate.getD4t5()));


            //第五条
            if(cashSalesTemplate.getD5t1()!=null){

                String time=DateUtils.StringToDateString(cashSalesTemplate.getD5t1());
                params.put("d5t1",NoNullString(time));

            }else {
                params.put("d5t1","         ");
            }
            //params.put("d5t1", NoNullString(cashSalesTemplate.getD5t1()));
            if(cashSalesTemplate.getD5t2()!=null){

                String time=DateUtils.StringToDateString(cashSalesTemplate.getD5t2());
                params.put("d5t2",NoNullString(time));

            }else {
                params.put("d5t2","         ");
            }
            //params.put("d5t2", NoNullString(cashSalesTemplate.getD5t2()));
            params.put("d5t3", NoNullString(cashSalesTemplate.getD5t3()));

            //第六条
            params.put("d6t1", NoNullString(cashSalesTemplate.getD6t1()));
            params.put("d6t2", NoNullString(cashSalesTemplate.getD6t2()));
            params.put("d6t3", NoNullString(cashSalesTemplate.getD6t3()));

            //第七条
            params.put("d7t1", NoNullString(cashSalesTemplate.getD7t1()));
            params.put("d7t2", NoNullString(cashSalesTemplate.getD7t2()));
            params.put("d7t3", NoNullString(cashSalesTemplate.getD7t3()));
            params.put("d7t4", NoNullString(cashSalesTemplate.getD7t4()));
            params.put("d7t5", NoNullString(cashSalesTemplate.getD7t5()));
            params.put("d7t6", NoNullString(cashSalesTemplate.getD7t6()));
            params.put("d7t7", NoNullString(cashSalesTemplate.getD7t7()));
            params.put("d7t8", NoNullString(cashSalesTemplate.getD7t8()));
            params.put("d7t9", NoNullString(cashSalesTemplate.getD7t9()));
            params.put("d7t10", NoNullString(cashSalesTemplate.getD7t10()));
            params.put("d7t11", NoNullString(cashSalesTemplate.getD7t11()));
            params.put("d7t12", NoNullString(cashSalesTemplate.getD7t12()));
            params.put("d7t13", NoNullString(cashSalesTemplate.getD7t13()));
            params.put("d7t14", NoNullString(cashSalesTemplate.getD7t14()));
            params.put("d7t15", NoNullString(cashSalesTemplate.getD7t15()));
            params.put("d7t16", NoNullString(cashSalesTemplate.getD7t16()));
            params.put("d7t17", NoNullString(cashSalesTemplate.getD7t17()));
            params.put("d7t18", NoNullString(cashSalesTemplate.getD7t18()));


            //第八条
            params.put("d8t1", NoNullString(cashSalesTemplate.getD8t1()));
            params.put("d8t2", NoNullString(cashSalesTemplate.getD8t2()));
            params.put("d8t3", NoNullString(cashSalesTemplate.getD8t3()));
            params.put("d8t4", NoNullString(cashSalesTemplate.getD8t4()));
            params.put("d8t5", NoNullString(cashSalesTemplate.getD8t5()));
            if(cashSalesTemplate.getD8t6()!=null){

                String time=DateUtils.StringToDateString(cashSalesTemplate.getD8t6());
                params.put("d8t6",NoNullString(time));

            }else {
                params.put("d8t6","         ");
            }
            //params.put("d8t6", NoNullString(cashSalesTemplate.getD8t6()));
            if(cashSalesTemplate.getD8t7()!=null){

                String time=DateUtils.StringToDateString(cashSalesTemplate.getD8t7());
                params.put("d8t7",NoNullString(time));

            }else {
                params.put("d8t7","         ");
            }
            //params.put("d8t7", NoNullString(cashSalesTemplate.getD8t7()));
            params.put("d8t8", NoNullString(cashSalesTemplate.getD8t8()));
            params.put("d8t9", NoNullString(cashSalesTemplate.getD8t9()));
            params.put("d8t10", NoNullString(cashSalesTemplate.getD8t10()));
            params.put("d8t11", NoNullString(cashSalesTemplate.getD8t11()));
            if(cashSalesTemplate.getD8t12()!=null){

                String time=DateUtils.StringToDateString(cashSalesTemplate.getD8t12());
                params.put("d8t12",NoNullString(time));

            }else {
                params.put("d8t12","         ");
            }
            //params.put("d8t12", NoNullString(cashSalesTemplate.getD8t12()));
            params.put("d8t13", NoNullString(cashSalesTemplate.getD8t13()));
            params.put("d8t14", NoNullString(cashSalesTemplate.getD8t14()));
            if(cashSalesTemplate.getD8t15()!=null){

                String time=DateUtils.StringToDateString(cashSalesTemplate.getD8t15());
                params.put("d8t15",NoNullString(time));

            }else {
                params.put("d8t15","         ");
            }
            //params.put("d8t15", NoNullString(cashSalesTemplate.getD8t15()));
            params.put("d8t16", NoNullString(cashSalesTemplate.getD8t16()));
            params.put("d8t17", NoNullString(cashSalesTemplate.getD8t17()));
            params.put("d8t18", NoNullString(cashSalesTemplate.getD8t18()));
            params.put("d8t19", NoNullString(cashSalesTemplate.getD8t19()));
            params.put("d8t20", NoNullString(cashSalesTemplate.getD8t20()));
            params.put("d8t21", NoNullString(cashSalesTemplate.getD8t21()));
            params.put("d8t22", NoNullString(cashSalesTemplate.getD8t22()));
            params.put("d8t23", NoNullString(cashSalesTemplate.getD8t23()));
            params.put("d8t24", NoNullString(cashSalesTemplate.getD8t24()));
            params.put("d8t25", NoNullString(cashSalesTemplate.getD8t25()));
            params.put("d8t26", NoNullString(cashSalesTemplate.getD8t26()));
            params.put("d8t27", NoNullString(cashSalesTemplate.getD8t27()));

            //第9条
            params.put("d9t1", NoNullString(cashSalesTemplate.getD9t1()));
            params.put("d9t2", NoNullString(cashSalesTemplate.getD9t2()));
            params.put("d9t3", NoNullString(cashSalesTemplate.getD9t3()));
            params.put("d9t4", NoNullString(cashSalesTemplate.getD9t4()));
            params.put("d9t5", NoNullString(cashSalesTemplate.getD9t5()));
            params.put("d9t6", NoNullString(cashSalesTemplate.getD9t6()));
            params.put("d9t7", NoNullString(cashSalesTemplate.getD9t7()));
            params.put("d9t8", NoNullString(cashSalesTemplate.getD9t8()));

            //第10条
            params.put("d10t1", NoNullString(cashSalesTemplate.getD10t1()));
            params.put("d10t2", NoNullString(cashSalesTemplate.getD10t2()));
            params.put("d10t3", NoNullString(cashSalesTemplate.getD10t3()));
            params.put("d10t4", NoNullString(cashSalesTemplate.getD10t4()));

            //第十一条
            params.put("d11t1", NoNullString(cashSalesTemplate.getD11t1()));
            params.put("d11t2", NoNullString(cashSalesTemplate.getD11t2()));
            params.put("d11t3", NoNullString(cashSalesTemplate.getD11t3()));
            params.put("d11t4", NoNullString(cashSalesTemplate.getD11t4()));
            params.put("d11t5", NoNullString(cashSalesTemplate.getD11t5()));
            params.put("d11t6", NoNullString(cashSalesTemplate.getD11t6()));
            params.put("d11t7", NoNullString(cashSalesTemplate.getD11t7()));
            params.put("d11t8", NoNullString(cashSalesTemplate.getD11t8()));
            params.put("d11t9", NoNullString(cashSalesTemplate.getD11t9()));
            params.put("d11t10", NoNullString(cashSalesTemplate.getD11t10()));
            if(cashSalesTemplate.getD11t11()!=null){

                String time=DateUtils.StringToDateString(cashSalesTemplate.getD11t11());
                params.put("d11t11",NoNullString(time));

            }else {
                params.put("d11t11","         ");
            }
            //params.put("d11t11", NoNullString(cashSalesTemplate.getD11t11()));
            params.put("d11t12", NoNullString(cashSalesTemplate.getD11t12()));
            if(cashSalesTemplate.getD11t13()!=null){

                String time=DateUtils.StringToDateString(cashSalesTemplate.getD11t13());
                params.put("d11t13",NoNullString(time));

            }else {
                params.put("d11t13","         ");
            }
            //params.put("d11t13", NoNullString(cashSalesTemplate.getD11t13()));
            params.put("d11t14", NoNullString(cashSalesTemplate.getD11t14()));
            if(cashSalesTemplate.getD11t15()!=null){

                String time=DateUtils.StringToDateString(cashSalesTemplate.getD11t15());
                params.put("d11t15",NoNullString(time));

            }else {
                params.put("d11t15","         ");
            }
            //params.put("d11t15", NoNullString(cashSalesTemplate.getD11t15()));
            params.put("d11t16", NoNullString(cashSalesTemplate.getD11t16()));
            if(cashSalesTemplate.getD11t17()!=null){

                String time=DateUtils.StringToDateString(cashSalesTemplate.getD11t17());
                params.put("d11t17",NoNullString(time));

            }else {
                params.put("d11t17","         ");
            }
            //params.put("d11t17", NoNullString(cashSalesTemplate.getD11t17()));
            params.put("d11t18", NoNullString(cashSalesTemplate.getD11t18()));
            if(cashSalesTemplate.getD11t19()!=null){

                String time=DateUtils.StringToDateString(cashSalesTemplate.getD11t19());
                params.put("d11t19",NoNullString(time));

            }else {
                params.put("d11t19","         ");
            }
            //params.put("d11t19", NoNullString(cashSalesTemplate.getD11t19()));
            params.put("d11t20", NoNullString(cashSalesTemplate.getD11t20()));
            if(cashSalesTemplate.getD11t21()!=null){

                String time=DateUtils.StringToDateString(cashSalesTemplate.getD11t21());
                params.put("d11t21",NoNullString(time));

            }else {
                params.put("d11t21","         ");
            }
            //params.put("d11t21", NoNullString(cashSalesTemplate.getD11t21()));
            params.put("d11t22", NoNullString(cashSalesTemplate.getD11t22()));
            if(cashSalesTemplate.getD11t23()!=null){

                String time=DateUtils.StringToDateString(cashSalesTemplate.getD11t23());
                params.put("d11t23",NoNullString(time));

            }else {
                params.put("d11t23","         ");
            }
            //params.put("d11t23", NoNullString(cashSalesTemplate.getD11t23()));
            params.put("d11t24", NoNullString(cashSalesTemplate.getD11t24()));
            params.put("d11t25", NoNullString(cashSalesTemplate.getD11t25()));
            params.put("d11t26", NoNullString(cashSalesTemplate.getD11t26()));
            params.put("d11t27", NoNullString(cashSalesTemplate.getD11t27()));
            params.put("d11t28", NoNullString(cashSalesTemplate.getD11t28()));
            params.put("d11t29", NoNullString(cashSalesTemplate.getD11t29()));
            params.put("d11t30", NoNullString(cashSalesTemplate.getD11t30()));
            params.put("d11t31", NoNullString(cashSalesTemplate.getD11t31()));

            //第12条
            if(cashSalesTemplate.getD12t1()!=null){

                String time=DateUtils.StringToDateString(cashSalesTemplate.getD12t1());
                params.put("d12t1",NoNullString(time));

            }else {
                params.put("d12t1","         ");
            }
            //params.put("d12t1", NoNullString(cashSalesTemplate.getD12t1()));
            params.put("d12t2", NoNullString(cashSalesTemplate.getD12t2()));
            params.put("d12t3", NoNullString(cashSalesTemplate.getD12t3()));
            params.put("d12t4", NoNullString(cashSalesTemplate.getD12t4()));
            params.put("d12t5", NoNullString(cashSalesTemplate.getD12t5()));
            params.put("d12t6", NoNullString(cashSalesTemplate.getD12t6()));
            params.put("d12t7", NoNullString(cashSalesTemplate.getD12t7()));
            params.put("d12t8", NoNullString(cashSalesTemplate.getD12t8()));

            //第13条
            params.put("d13t1", NoNullString(cashSalesTemplate.getD13t1()));
            params.put("d13t2", NoNullString(cashSalesTemplate.getD13t2()));
            params.put("d13t3", NoNullString(cashSalesTemplate.getD13t3()));
            params.put("d13t4", NoNullString(cashSalesTemplate.getD13t4()));
            params.put("d13t5", NoNullString(cashSalesTemplate.getD13t5()));
            params.put("d13t6", NoNullString(cashSalesTemplate.getD13t6()));
            params.put("d13t7", NoNullString(cashSalesTemplate.getD13t7()));
            params.put("d13t8", NoNullString(cashSalesTemplate.getD13t8()));
            
            //第14条
            params.put("d14t1", NoNullString(cashSalesTemplate.getD14t1()));
            params.put("d14t2", NoNullString(cashSalesTemplate.getD14t2()));
            params.put("d14t3", NoNullString(cashSalesTemplate.getD14t3()));
            params.put("d14t4", NoNullString(cashSalesTemplate.getD14t4()));
            params.put("d14t5", NoNullString(cashSalesTemplate.getD14t5()));
            params.put("d14t6", NoNullString(cashSalesTemplate.getD14t6()));
            params.put("d14t7", NoNullString(cashSalesTemplate.getD14t7()));
            params.put("d14t8", NoNullString(cashSalesTemplate.getD14t8()));
            params.put("d14t9", NoNullString(cashSalesTemplate.getD14t9()));
            params.put("d14t10", NoNullString(cashSalesTemplate.getD14t10()));
            params.put("d14t11", NoNullString(cashSalesTemplate.getD14t11()));
            params.put("d14t12", NoNullString(cashSalesTemplate.getD14t12()));
            params.put("d14t13", NoNullString(cashSalesTemplate.getD14t13()));
            params.put("d14t14", NoNullString(cashSalesTemplate.getD14t14()));
            params.put("d14t15", NoNullString(cashSalesTemplate.getD14t15()));

            //第15条
            params.put("d15t1", NoNullString(cashSalesTemplate.getD15t1()));
            params.put("d15t2", NoNullString(cashSalesTemplate.getD15t2()));

            //第16条
            params.put("d16t1", NoNullString(cashSalesTemplate.getD16t1()));


            //第17条
            params.put("d17t1", NoNullString(cashSalesTemplate.getD17t1()));
            params.put("d17t2", NoNullString(cashSalesTemplate.getD17t2()));
            params.put("d17t3", NoNullString(cashSalesTemplate.getD17t3()));
            params.put("d17t4", NoNullString(cashSalesTemplate.getD17t4()));
            params.put("d17t5", NoNullString(cashSalesTemplate.getD17t5()));

            //第18条
            params.put("d18t1", NoNullString(cashSalesTemplate.getD18t1()));
            if(cashSalesTemplate.getD18t2()!=null){

                String time=DateUtils.StringToDateString(cashSalesTemplate.getD18t2());
                params.put("d18t2",NoNullString(time));

            }else {
                params.put("d18t2","         ");
            }
            //params.put("d18t2", NoNullString(cashSalesTemplate.getD18t2()));
            if(cashSalesTemplate.getD18t3()!=null){

                String time=DateUtils.StringToDateString(cashSalesTemplate.getD18t3());
                params.put("d18t3",NoNullString(time));

            }else {
                params.put("d18t3","         ");
            }
            //params.put("d18t3", NoNullString(cashSalesTemplate.getD18t3()));
            params.put("d18t4", NoNullString(cashSalesTemplate.getD18t4()));
            params.put("d18t5", NoNullString(cashSalesTemplate.getD18t5()));

            //第19条
            params.put("d19t1", NoNullString(cashSalesTemplate.getD19t1()));
            params.put("d19t2", NoNullString(cashSalesTemplate.getD19t2()));
            params.put("d19t3", NoNullString(cashSalesTemplate.getD19t3()));
            params.put("d19t4", NoNullString(cashSalesTemplate.getD19t4()));

            //第21条
            params.put("d21t1", NoNullString(cashSalesTemplate.getD21t1()));
            params.put("d21t2", NoNullString(cashSalesTemplate.getD21t2()));

            //第22条
            params.put("d22t1", NoNullString(cashSalesTemplate.getD22t1()));
            params.put("d22t2", NoNullString(cashSalesTemplate.getD22t2()));

            //第24条
            params.put("d24t1", NoNullString(cashSalesTemplate.getD24t1()));
            params.put("d24t2", NoNullString(cashSalesTemplate.getD24t2()));

            //第26条
            params.put("d26t1", NoNullString(cashSalesTemplate.getD26t1()));
            params.put("d26t2", NoNullString(cashSalesTemplate.getD26t2()));
            params.put("d26t3", NoNullString(cashSalesTemplate.getD26t3()));
            params.put("d26t4", NoNullString(cashSalesTemplate.getD26t4()));
            params.put("d26t5", NoNullString(cashSalesTemplate.getD26t5()));
            params.put("d26t6", NoNullString(cashSalesTemplate.getD26t6()));
            params.put("d26t7", NoNullString(cashSalesTemplate.getD26t7()));
            params.put("d26t8", NoNullString(cashSalesTemplate.getD26t8()));
            
            //结尾签章
            params.put("qz1", NoNullString(cashSalesTemplate.getQz1()));
            params.put("qz2", NoNullString(cashSalesTemplate.getQz2()));
            params.put("qz3", NoNullString(cashSalesTemplate.getQz3()));
            if(cashSalesTemplate.getQz4()!=null){

                String time=DateUtils.StringToDateString(cashSalesTemplate.getQz4());
                params.put("qz4",NoNullString(time));

            }else {
                params.put("qz4"," ");
            }
            //params.put("qz4", NoNullString(cashSalesTemplate.getQz4()));
            params.put("qz5", NoNullString(cashSalesTemplate.getQz5()));
            params.put("qz6", NoNullString(cashSalesTemplate.getQz6()));
            params.put("qz7", NoNullString(cashSalesTemplate.getQz7()));
            params.put("qz8", NoNullString(cashSalesTemplate.getQz8()));
            params.put("qz9", NoNullString(cashSalesTemplate.getQz9()));
            if(cashSalesTemplate.getQz10()!=null){
                String time=DateUtils.StringToDateString(cashSalesTemplate.getQz10());
                params.put("qz10",NoNullString(time));
            }else {
                params.put("qz10"," ");
            }
            params.put("qz11", NoNullString(cashSalesTemplate.getQz11()));


            //附件7 
            params.put("fj7jw1", NoNullString(cashSalesTemplate.getFj7jw1()));
            params.put("fj7jw2", NoNullString(cashSalesTemplate.getFj7jw2()));
            params.put("fj7jw3", NoNullString(cashSalesTemplate.getFj7jw3()));
            params.put("fj7jw4", NoNullString(cashSalesTemplate.getFj7jw4()));
            params.put("fj7jw5", NoNullString(cashSalesTemplate.getFj7jw5()));
            params.put("fj7jw6", NoNullString(cashSalesTemplate.getFj7jw6()));
            params.put("fj7jw7", NoNullString(cashSalesTemplate.getFj7jw7()));
            params.put("fj7jw8", NoNullString(cashSalesTemplate.getFj7jw8()));
            params.put("fj7jw9", NoNullString(cashSalesTemplate.getFj7jw9()));
            params.put("fj7jw10", NoNullString(cashSalesTemplate.getFj7jw10()));
            params.put("fj7jw11", NoNullString(cashSalesTemplate.getFj7jw11()));
            params.put("fj7jw12", NoNullString(cashSalesTemplate.getFj7jw12()));
            params.put("fj7jw13", NoNullString(cashSalesTemplate.getFj7jw13()));
            params.put("fj7jw14", NoNullString(cashSalesTemplate.getFj7jw14()));
            params.put("fj7jw15", NoNullString(cashSalesTemplate.getFj7jw15()));
            params.put("fj7jw16", NoNullString(cashSalesTemplate.getFj7jw16()));
            params.put("fj7jw17", NoNullString(cashSalesTemplate.getFj7jw17()));
            params.put("fj7jw18", NoNullString(cashSalesTemplate.getFj7jw18()));
            params.put("fj7jw19", NoNullString(cashSalesTemplate.getFj7jw19()));
            params.put("fj7jw20", NoNullString(cashSalesTemplate.getFj7jw20()));
            params.put("fj7jw21", NoNullString(cashSalesTemplate.getFj7jw21()));
            params.put("fj7jw22", NoNullString(cashSalesTemplate.getFj7jw22()));
            params.put("fj7jw23", NoNullString(cashSalesTemplate.getFj7jw23()));
            params.put("fj7jw24", NoNullString(cashSalesTemplate.getFj7jw24()));
            params.put("fj7jw25", NoNullString(cashSalesTemplate.getFj7jw25()));
            params.put("fj7jw26", NoNullString(cashSalesTemplate.getFj7jw26()));
            params.put("fj7jw27", NoNullString(cashSalesTemplate.getFj7jw27()));
            params.put("fj7jw28", NoNullString(cashSalesTemplate.getFj7jw28()));
            params.put("fj7jw29", NoNullString(cashSalesTemplate.getFj7jw29()));
            params.put("fj7jw30", NoNullString(cashSalesTemplate.getFj7jw30()));


            //附件7 
            params.put("fj8jw1", NoNullString(cashSalesTemplate.getFj8jw1()));
            params.put("fj8jw2", NoNullString(cashSalesTemplate.getFj8jw2()));
            params.put("fj8jw3", NoNullString(cashSalesTemplate.getFj8jw3()));
            params.put("fj8jw4", NoNullString(cashSalesTemplate.getFj8jw4()));
            params.put("fj8jw5", NoNullString(cashSalesTemplate.getFj8jw5()));
            params.put("fj8jw6", NoNullString(cashSalesTemplate.getFj8jw6()));
            params.put("fj8jw7", NoNullString(cashSalesTemplate.getFj8jw7()));
            params.put("fj8jw8", NoNullString(cashSalesTemplate.getFj8jw8()));
            params.put("fj8jw9", NoNullString(cashSalesTemplate.getFj8jw9()));
            params.put("fj8jw10", NoNullString(cashSalesTemplate.getFj8jw10()));
            params.put("fj8jw11", NoNullString(cashSalesTemplate.getFj8jw11()));
            params.put("fj8jw12", NoNullString(cashSalesTemplate.getFj8jw12()));
            params.put("fj8jw13", NoNullString(cashSalesTemplate.getFj8jw13()));
            params.put("fj8jw14", NoNullString(cashSalesTemplate.getFj8jw14()));
            
        }

        public void getAdvanceSalesParams(Map<String, Object> params,String id){
            AdvanceSalesTemplate advanceSalesTemplate=advanceSalesTemplateMapper.getAllByHousetradeId(id);
 
            //合同开头
            params.put("ht1", NoNullString(advanceSalesTemplate.getHt1()));
            params.put("ht2", NoNullString(advanceSalesTemplate.getHt2()));
            params.put("ht3", NoNullString(advanceSalesTemplate.getHt3()));
            //甲方
            params.put("jf1", NoNullString(advanceSalesTemplate.getJf1()));
            params.put("jf2", NoNullString(advanceSalesTemplate.getJf2()));
            params.put("jf3", NoNullString(advanceSalesTemplate.getJf3()));
            params.put("jf4", NoNullString(advanceSalesTemplate.getJf4()));
            params.put("jf5", NoNullString(advanceSalesTemplate.getJf5()));
            params.put("jf6", NoNullString(advanceSalesTemplate.getJf6()));
            params.put("jf7", NoNullString(advanceSalesTemplate.getJf7()));
            params.put("jf8", NoNullString(advanceSalesTemplate.getJf8()));
            params.put("jf9", NoNullString(advanceSalesTemplate.getJf9()));
            params.put("jf10", NoNullString(advanceSalesTemplate.getJf10()));
            params.put("jf11", NoNullString(advanceSalesTemplate.getJf11()));
            params.put("jf12", NoNullString(advanceSalesTemplate.getJf12()));
            params.put("jf13", NoNullString(advanceSalesTemplate.getJf13()));
            params.put("jf14", NoNullString(advanceSalesTemplate.getJf14()));
            params.put("jf15", NoNullString(advanceSalesTemplate.getJf15()));
            params.put("jf16", NoNullString(advanceSalesTemplate.getJf16()));

            //乙方
            params.put("yf1", NoNullString(advanceSalesTemplate.getYf1()));
            params.put("yf2", NoNullString(advanceSalesTemplate.getYf2()));
            params.put("yf3", NoNullString(advanceSalesTemplate.getYf3()));
            params.put("yf4", NoNullString(advanceSalesTemplate.getYf4()));
            params.put("yf5", NoNullString(advanceSalesTemplate.getYf5()));
            if(advanceSalesTemplate.getYf6()!=null){

                String time=DateUtils.StringToDateString(advanceSalesTemplate.getYf6());
                params.put("yf6",NoNullString(time));

            }else {
                params.put("yf6","         ");
            }
            //params.put("yf6", NoNullString(advanceSalesTemplate.getYf6()));
            params.put("yf7", NoNullString(advanceSalesTemplate.getYf7()));
            params.put("yf8", NoNullString(advanceSalesTemplate.getYf8()));
            params.put("yf9", NoNullString(advanceSalesTemplate.getYf9()));
            params.put("yf10", NoNullString(advanceSalesTemplate.getYf10()));
            params.put("yf11", NoNullString(advanceSalesTemplate.getYf11()));
            params.put("yf12", NoNullString(advanceSalesTemplate.getYf12()));
            params.put("yf13", NoNullString(advanceSalesTemplate.getYf13()));
            params.put("yf14", NoNullString(advanceSalesTemplate.getYf14()));
            if(advanceSalesTemplate.getYf15()!=null){

                String time=DateUtils.StringToDateString(advanceSalesTemplate.getYf15());
                params.put("yf15",NoNullString(time));

            }else {
                params.put("yf15","         ");
            }
            //params.put("yf15", NoNullString(advanceSalesTemplate.getYf15()));
            params.put("yf16", NoNullString(advanceSalesTemplate.getYf16()));
            params.put("yf17", NoNullString(advanceSalesTemplate.getYf17()));
            params.put("yf18", NoNullString(advanceSalesTemplate.getYf18()));
            params.put("yf19", NoNullString(advanceSalesTemplate.getYf19()));

            //第一条
            params.put("d1t1", NoNullString(advanceSalesTemplate.getD1t1()));
            params.put("d1t2", NoNullString(advanceSalesTemplate.getD1t2()));
            params.put("d1t3", NoNullString(advanceSalesTemplate.getD1t3()));
            params.put("d1t4", NoNullString(advanceSalesTemplate.getD1t4()));
            params.put("d1t5", NoNullString(advanceSalesTemplate.getD1t5()));
            params.put("d1t6", NoNullString(advanceSalesTemplate.getD1t6()));
            if(advanceSalesTemplate.getD1t7()!=null){

                String time=DateUtils.StringToDateString(advanceSalesTemplate.getD1t7());
                params.put("d1t7",NoNullString(time));

            }else {
                params.put("d1t7","         ");
            }
            //params.put("d1t7", NoNullString(advanceSalesTemplate.getD1t7()));
            params.put("d1t8", NoNullString(advanceSalesTemplate.getD1t8()));
            params.put("d1t9", NoNullString(advanceSalesTemplate.getD1t9()));
            params.put("d1t10", NoNullString(advanceSalesTemplate.getD1t10()));

            //第二条

            params.put("d2t1", NoNullString(advanceSalesTemplate.getD2t1()));
            params.put("d2t2", NoNullString(advanceSalesTemplate.getD2t2()));

            //第三条
            params.put("d3t1", NoNullString(advanceSalesTemplate.getD3t1()));
            params.put("d3t2", NoNullString(advanceSalesTemplate.getD3t2()));
            params.put("d3t3", NoNullString(advanceSalesTemplate.getD3t3()));
            params.put("d3t4", NoNullString(advanceSalesTemplate.getD3t4()));
            params.put("d3t5", NoNullString(advanceSalesTemplate.getD3t5()));
            params.put("d3t6", NoNullString(advanceSalesTemplate.getD3t6()));
            params.put("d3t7", NoNullString(advanceSalesTemplate.getD3t7()));
            params.put("d3t8", NoNullString(advanceSalesTemplate.getD3t8()));
            params.put("d3t9", NoNullString(advanceSalesTemplate.getD3t9()));
            params.put("d3t10", NoNullString(advanceSalesTemplate.getD3t10()));
            params.put("d3t11", NoNullString(advanceSalesTemplate.getD3t11()));
            params.put("d3t12", NoNullString(advanceSalesTemplate.getD3t12()));
            params.put("d3t13", NoNullString(advanceSalesTemplate.getD3t13()));
            params.put("d3t14", NoNullString(advanceSalesTemplate.getD3t14()));
            params.put("d3t15", NoNullString(advanceSalesTemplate.getD3t15()));
            params.put("d3t16", NoNullString(advanceSalesTemplate.getD3t16()));
            params.put("d3t17", NoNullString(advanceSalesTemplate.getD3t17()));
            params.put("d3t18", NoNullString(advanceSalesTemplate.getD3t18()));


            //第四条
            params.put("d4t1", NoNullString(advanceSalesTemplate.getD4t1()));
            params.put("d4t2", NoNullString(advanceSalesTemplate.getD4t2()));
            params.put("d4t3", NoNullString(advanceSalesTemplate.getD4t3()));
            params.put("d4t4", NoNullString(advanceSalesTemplate.getD4t4()));
            if(advanceSalesTemplate.getD4t5()!=null){

                String time=DateUtils.StringToDateString(advanceSalesTemplate.getD4t5());
                params.put("d4t5",NoNullString(time));

            }else {
                params.put("d4t5","         ");
            }
            //params.put("d4t5", NoNullString(advanceSalesTemplate.getD4t5()));
            params.put("d4t6", NoNullString(advanceSalesTemplate.getD4t6()));
            params.put("d4t7", NoNullString(advanceSalesTemplate.getD4t7()));
            params.put("d4t8", NoNullString(advanceSalesTemplate.getD4t8()));
            params.put("d4t9", NoNullString(advanceSalesTemplate.getD4t9()));
            params.put("d4t10", NoNullString(advanceSalesTemplate.getD4t10()));
            if(advanceSalesTemplate.getD4t11()!=null){

                String time=DateUtils.StringToDateString(advanceSalesTemplate.getD4t11());
                params.put("d4t11",NoNullString(time));

            }else {
                params.put("d4t11","         ");
            }
            //params.put("d4t11", NoNullString(advanceSalesTemplate.getD4t11()));
            params.put("d4t12", NoNullString(advanceSalesTemplate.getD4t12()));
            
            //第五条
            params.put("d5t1", NoNullString(advanceSalesTemplate.getD5t1()));
            params.put("d5t2", NoNullString(advanceSalesTemplate.getD5t2()));
            params.put("d5t3", NoNullString(advanceSalesTemplate.getD5t3()));

            //第六条
            params.put("d6t1", NoNullString(advanceSalesTemplate.getD6t1()));
            params.put("d6t2", NoNullString(advanceSalesTemplate.getD6t2()));
            params.put("d6t3", NoNullString(advanceSalesTemplate.getD6t3()));
            params.put("d6t4", NoNullString(advanceSalesTemplate.getD6t4()));
            params.put("d6t5", NoNullString(advanceSalesTemplate.getD6t5()));
            params.put("d6t6", NoNullString(advanceSalesTemplate.getD6t6()));
            params.put("d6t7", NoNullString(advanceSalesTemplate.getD6t7()));
            params.put("d6t8", NoNullString(advanceSalesTemplate.getD6t8()));
            params.put("d6t9", NoNullString(advanceSalesTemplate.getD6t9()));
            params.put("d6t10", NoNullString(advanceSalesTemplate.getD6t10()));
            params.put("d6t11", NoNullString(advanceSalesTemplate.getD6t11()));
            params.put("d6t12", NoNullString(advanceSalesTemplate.getD6t12()));
            params.put("d6t13", NoNullString(advanceSalesTemplate.getD6t13()));
            params.put("d6t14", NoNullString(advanceSalesTemplate.getD6t14()));
            params.put("d6t15", NoNullString(advanceSalesTemplate.getD6t15()));
            params.put("d6t16", NoNullString(advanceSalesTemplate.getD6t16()));
            params.put("d6t17", NoNullString(advanceSalesTemplate.getD6t17()));
            params.put("d6t18", NoNullString(advanceSalesTemplate.getD6t18()));

            //第七条
            params.put("d7t1", NoNullString(advanceSalesTemplate.getD7t1()));
            params.put("d7t2", NoNullString(advanceSalesTemplate.getD7t2()));
            params.put("d7t3", NoNullString(advanceSalesTemplate.getD7t3()));
            params.put("d7t4", NoNullString(advanceSalesTemplate.getD7t4()));
            params.put("d7t5", NoNullString(advanceSalesTemplate.getD7t5()));
            if(advanceSalesTemplate.getD7t6()!=null){

                String time=DateUtils.StringToDateString(advanceSalesTemplate.getD7t6());
                params.put("d7t6",NoNullString(time));

            }else {
                params.put("d7t6","         ");
            }
            //params.put("d7t6", NoNullString(advanceSalesTemplate.getD7t6()));
            if(advanceSalesTemplate.getD7t7()!=null){

                String time=DateUtils.StringToDateString(advanceSalesTemplate.getD7t7());
                params.put("d7t7",NoNullString(time));

            }else {
                params.put("d7t7","         ");
            }
            //params.put("d7t7", NoNullString(advanceSalesTemplate.getD7t7()));
            params.put("d7t8", NoNullString(advanceSalesTemplate.getD7t8()));
            params.put("d7t9", NoNullString(advanceSalesTemplate.getD7t9()));
            params.put("d7t10", NoNullString(advanceSalesTemplate.getD7t10()));
            params.put("d7t11", NoNullString(advanceSalesTemplate.getD7t11()));
            if(advanceSalesTemplate.getD7t12()!=null){

                String time=DateUtils.StringToDateString(advanceSalesTemplate.getD7t12());
                params.put("d7t12",NoNullString(time));

            }else {
                params.put("d7t12","         ");
            }
            //params.put("d7t12", NoNullString(advanceSalesTemplate.getD7t12()));
            params.put("d7t13", NoNullString(advanceSalesTemplate.getD7t13()));
            params.put("d7t14", NoNullString(advanceSalesTemplate.getD7t14()));
            if(advanceSalesTemplate.getD7t15()!=null){

                String time=DateUtils.StringToDateString(advanceSalesTemplate.getD7t15());
                params.put("d7t15",NoNullString(time));

            }else {
                params.put("d7t15","         ");
            }
            //params.put("d7t15", NoNullString(advanceSalesTemplate.getD7t15()));
            params.put("d7t16", NoNullString(advanceSalesTemplate.getD7t16()));
            params.put("d7t17", NoNullString(advanceSalesTemplate.getD7t17()));
            params.put("d7t18", NoNullString(advanceSalesTemplate.getD7t18()));
            params.put("d7t19", NoNullString(advanceSalesTemplate.getD7t19()));
            params.put("d7t20", NoNullString(advanceSalesTemplate.getD7t20()));
            params.put("d7t21", NoNullString(advanceSalesTemplate.getD7t21()));
            params.put("d7t22", NoNullString(advanceSalesTemplate.getD7t22()));
            params.put("d7t23", NoNullString(advanceSalesTemplate.getD7t23()));
            params.put("d7t24", NoNullString(advanceSalesTemplate.getD7t24()));
            params.put("d7t25", NoNullString(advanceSalesTemplate.getD7t25()));
            params.put("d7t26", NoNullString(advanceSalesTemplate.getD7t26()));
            params.put("d7t27", NoNullString(advanceSalesTemplate.getD7t27()));


            //第八条
            params.put("d8t1", NoNullString(advanceSalesTemplate.getD8t1()));
            params.put("d8t2", NoNullString(advanceSalesTemplate.getD8t2()));
            params.put("d8t3", NoNullString(advanceSalesTemplate.getD8t3()));
            params.put("d8t4", NoNullString(advanceSalesTemplate.getD8t4()));
            params.put("d8t5", NoNullString(advanceSalesTemplate.getD8t5()));
            params.put("d8t6", NoNullString(advanceSalesTemplate.getD8t6()));
            params.put("d8t7", NoNullString(advanceSalesTemplate.getD8t7()));
            params.put("d8t8", NoNullString(advanceSalesTemplate.getD8t8()));

            //第9条
            params.put("d9t1", NoNullString(advanceSalesTemplate.getD9t1()));
            params.put("d9t2", NoNullString(advanceSalesTemplate.getD9t2()));
            params.put("d9t3", NoNullString(advanceSalesTemplate.getD9t3()));
            params.put("d9t4", NoNullString(advanceSalesTemplate.getD9t4())); 

            //第10条
            params.put("d10t1", NoNullString(advanceSalesTemplate.getD10t1()));
            params.put("d10t2", NoNullString(advanceSalesTemplate.getD10t2()));
            params.put("d10t3", NoNullString(advanceSalesTemplate.getD10t3()));
            params.put("d10t4", NoNullString(advanceSalesTemplate.getD10t4()));
            params.put("d10t5", NoNullString(advanceSalesTemplate.getD10t5()));
            params.put("d10t6", NoNullString(advanceSalesTemplate.getD10t6()));
            params.put("d10t7", NoNullString(advanceSalesTemplate.getD10t7()));
            params.put("d10t8", NoNullString(advanceSalesTemplate.getD10t8()));
            params.put("d10t9", NoNullString(advanceSalesTemplate.getD10t9()));
            params.put("d10t10", NoNullString(advanceSalesTemplate.getD10t10()));
            if(advanceSalesTemplate.getD10t11()!=null){

                String time=DateUtils.StringToDateString(advanceSalesTemplate.getD10t11());
                params.put("d10t11",NoNullString(time));

            }else {
                params.put("d10t11","         ");
            }
            //params.put("d10t11", NoNullString(advanceSalesTemplate.getD10t11()));
            params.put("d10t12", NoNullString(advanceSalesTemplate.getD10t12()));
            if(advanceSalesTemplate.getD10t13()!=null){

                String time=DateUtils.StringToDateString(advanceSalesTemplate.getD10t13());
                params.put("d10t13",NoNullString(time));

            }else {
                params.put("d10t13","         ");
            }
            //params.put("d10t13", NoNullString(advanceSalesTemplate.getD10t13()));
            params.put("d10t14", NoNullString(advanceSalesTemplate.getD10t14()));
            if(advanceSalesTemplate.getD10t15()!=null){

                String time=DateUtils.StringToDateString(advanceSalesTemplate.getD10t15());
                params.put("d10t15",NoNullString(time));

            }else {
                params.put("d10t15","         ");
            }
            //params.put("d10t15", NoNullString(advanceSalesTemplate.getD10t15()));
            params.put("d10t16", NoNullString(advanceSalesTemplate.getD10t16()));
            if(advanceSalesTemplate.getD10t17()!=null){

                String time=DateUtils.StringToDateString(advanceSalesTemplate.getD10t17());
                params.put("d10t17",NoNullString(time));

            }else {
                params.put("d10t17","         ");
            }
            //params.put("d10t17", NoNullString(advanceSalesTemplate.getD10t17()));
            params.put("d10t18", NoNullString(advanceSalesTemplate.getD10t18()));
            if(advanceSalesTemplate.getD10t19()!=null){

                String time=DateUtils.StringToDateString(advanceSalesTemplate.getD10t19());
                params.put("d10t19",NoNullString(time));

            }else {
                params.put("d10t19","         ");
            }
            //params.put("d10t19", NoNullString(advanceSalesTemplate.getD10t19()));
            params.put("d10t20", NoNullString(advanceSalesTemplate.getD10t20()));
            if(advanceSalesTemplate.getD10t21()!=null){

                String time=DateUtils.StringToDateString(advanceSalesTemplate.getD10t21());
                params.put("d10t21",NoNullString(time));

            }else {
                params.put("d10t21","         ");
            }
            //params.put("d10t21", NoNullString(advanceSalesTemplate.getD10t21()));
            params.put("d10t22", NoNullString(advanceSalesTemplate.getD10t22()));
            if(advanceSalesTemplate.getD10t23()!=null){

                String time=DateUtils.StringToDateString(advanceSalesTemplate.getD10t23());
                params.put("d10t23",NoNullString(time));

            }else {
                params.put("d10t23","         ");
            }
            //params.put("d10t23", NoNullString(advanceSalesTemplate.getD10t23()));
            params.put("d10t24", NoNullString(advanceSalesTemplate.getD10t24()));
            params.put("d10t25", NoNullString(advanceSalesTemplate.getD10t25()));
            params.put("d10t26", NoNullString(advanceSalesTemplate.getD10t26()));
            params.put("d10t27", NoNullString(advanceSalesTemplate.getD10t27()));
            params.put("d10t28", NoNullString(advanceSalesTemplate.getD10t28()));
            params.put("d10t29", NoNullString(advanceSalesTemplate.getD10t29()));
            params.put("d10t30", NoNullString(advanceSalesTemplate.getD10t30()));
            params.put("d10t31", NoNullString(advanceSalesTemplate.getD10t31()));

            //第十一条
            if(advanceSalesTemplate.getD11t1()!=null){

                String time=DateUtils.StringToDateString(advanceSalesTemplate.getD11t1());
                params.put("d11t1",NoNullString(time));

            }else {
                params.put("d11t1","         ");
            }
            //params.put("d11t1", NoNullString(advanceSalesTemplate.getD11t1()));
            params.put("d11t2", NoNullString(advanceSalesTemplate.getD11t2()));
            params.put("d11t3", NoNullString(advanceSalesTemplate.getD11t3()));
            params.put("d11t4", NoNullString(advanceSalesTemplate.getD11t4()));
            params.put("d11t5", NoNullString(advanceSalesTemplate.getD11t5()));
            params.put("d11t6", NoNullString(advanceSalesTemplate.getD11t6()));
            params.put("d11t7", NoNullString(advanceSalesTemplate.getD11t7()));
            params.put("d11t8", NoNullString(advanceSalesTemplate.getD11t8()));

            //第12条
            params.put("d12t1", NoNullString(advanceSalesTemplate.getD12t1()));
            params.put("d12t2", NoNullString(advanceSalesTemplate.getD12t2()));
            params.put("d12t3", NoNullString(advanceSalesTemplate.getD12t3()));
            params.put("d12t4", NoNullString(advanceSalesTemplate.getD12t4()));
            params.put("d12t5", NoNullString(advanceSalesTemplate.getD12t5()));
            params.put("d12t6", NoNullString(advanceSalesTemplate.getD12t6()));
            params.put("d12t7", NoNullString(advanceSalesTemplate.getD12t7()));
            params.put("d12t8", NoNullString(advanceSalesTemplate.getD12t8()));

            //第13条
            params.put("d13t1", NoNullString(advanceSalesTemplate.getD13t1()));
            params.put("d13t2", NoNullString(advanceSalesTemplate.getD13t2()));
            params.put("d13t3", NoNullString(advanceSalesTemplate.getD13t3()));
            params.put("d13t4", NoNullString(advanceSalesTemplate.getD13t4()));
            //丢失
            params.put("d13t5", NoNullString(advanceSalesTemplate.getD13t5()));
            params.put("d13t6", NoNullString(advanceSalesTemplate.getD13t6()));
            params.put("d13t7", NoNullString(advanceSalesTemplate.getD13t7()));

            //第14条
            params.put("d14t1", NoNullString(advanceSalesTemplate.getD14t1()));
            params.put("d14t2", NoNullString(advanceSalesTemplate.getD14t2()));
            params.put("d14t3", NoNullString(advanceSalesTemplate.getD14t3())); 

            //第15条
            params.put("d15t1", NoNullString(advanceSalesTemplate.getD15t1()));
            params.put("d15t2", NoNullString(advanceSalesTemplate.getD15t2()));
            params.put("d15t3", NoNullString(advanceSalesTemplate.getD15t3()));
            params.put("d15t4", NoNullString(advanceSalesTemplate.getD15t4()));
            params.put("d15t5", NoNullString(advanceSalesTemplate.getD15t5()));
            params.put("d15t6", NoNullString(advanceSalesTemplate.getD15t6()));

            //第16条
            params.put("d16t1", NoNullString(advanceSalesTemplate.getD16t1()));
            params.put("d16t2", NoNullString(advanceSalesTemplate.getD16t2()));
            params.put("d16t3", NoNullString(advanceSalesTemplate.getD16t3()));
            params.put("d16t4", NoNullString(advanceSalesTemplate.getD16t4()));
            params.put("d16t5", NoNullString(advanceSalesTemplate.getD16t5()));
            params.put("d16t6", NoNullString(advanceSalesTemplate.getD16t6()));
            params.put("d16t7", NoNullString(advanceSalesTemplate.getD16t7()));
            params.put("d16t8", NoNullString(advanceSalesTemplate.getD16t8()));
            params.put("d16t9", NoNullString(advanceSalesTemplate.getD16t9()));
            params.put("d16t10", NoNullString(advanceSalesTemplate.getD16t10()));
            params.put("d16t11", NoNullString(advanceSalesTemplate.getD16t11()));
            params.put("d16t12", NoNullString(advanceSalesTemplate.getD16t12()));
            //缺失
            params.put("d16t13", NoNullString(advanceSalesTemplate.getD16t13()));
            params.put("d16t14", NoNullString(advanceSalesTemplate.getD16t14()));
            params.put("d16t15", NoNullString(advanceSalesTemplate.getD16t15()));


            //第17条
            params.put("d17t1", NoNullString(advanceSalesTemplate.getD17t1()));
            params.put("d17t2", NoNullString(advanceSalesTemplate.getD17t2()));

            //第18条
            params.put("d18t1", NoNullString(advanceSalesTemplate.getD18t1()));

            //第19条
            params.put("d19t1", NoNullString(advanceSalesTemplate.getD19t1()));
            params.put("d19t2", NoNullString(advanceSalesTemplate.getD19t2()));
            params.put("d19t3", NoNullString(advanceSalesTemplate.getD19t3()));

            //第20条
            params.put("d20t1", NoNullString(advanceSalesTemplate.getD20t1()));
            if(advanceSalesTemplate.getD20t2()!=null){

                String time=DateUtils.StringToDateString(advanceSalesTemplate.getD20t2());
                params.put("d20t2",NoNullString(time));

            }else {
                params.put("d20t2","         ");
            }
            //params.put("d20t2", NoNullString(advanceSalesTemplate.getD20t2()));
            if(advanceSalesTemplate.getD20t3()!=null){

                String time=DateUtils.StringToDateString(advanceSalesTemplate.getD20t3());
                params.put("d20t3",NoNullString(time));

            }else {
                params.put("d20t3","         ");
            }
            //params.put("d20t3", NoNullString(advanceSalesTemplate.getD20t3()));
            params.put("d20t4", NoNullString(advanceSalesTemplate.getD20t4()));
            params.put("d20t5", NoNullString(advanceSalesTemplate.getD20t5()));

            //第21条
            params.put("d21t1", NoNullString(advanceSalesTemplate.getD21t1()));
            params.put("d21t2", NoNullString(advanceSalesTemplate.getD21t2()));
            params.put("d21t3", NoNullString(advanceSalesTemplate.getD21t3()));
            params.put("d21t4", NoNullString(advanceSalesTemplate.getD21t4()));
            params.put("d21t5", NoNullString(advanceSalesTemplate.getD21t5()));

            //第22条
            params.put("d22t1", NoNullString(advanceSalesTemplate.getD22t1()));
            params.put("d22t2", NoNullString(advanceSalesTemplate.getD22t2()));
            params.put("d22t3", NoNullString(advanceSalesTemplate.getD22t3()));
            params.put("d22t4", NoNullString(advanceSalesTemplate.getD22t4()));

            //第23条
            params.put("d23t1", NoNullString(advanceSalesTemplate.getD23t1()));

            //第24条
            params.put("d24t1", NoNullString(advanceSalesTemplate.getD24t1()));
            params.put("d24t2", NoNullString(advanceSalesTemplate.getD24t2()));

            //第25条
            params.put("d25t1", NoNullString(advanceSalesTemplate.getD25t1()));
            params.put("d25t2", NoNullString(advanceSalesTemplate.getD25t2()));

            //第27条
            params.put("d27t1", NoNullString(advanceSalesTemplate.getD27t1()));
            params.put("d27t2", NoNullString(advanceSalesTemplate.getD27t2()));

            //第29条
            params.put("d29t1", NoNullString(advanceSalesTemplate.getD29t1()));
            params.put("d29t2", NoNullString(advanceSalesTemplate.getD29t2()));
            params.put("d29t3", NoNullString(advanceSalesTemplate.getD29t3()));
            params.put("d29t4", NoNullString(advanceSalesTemplate.getD29t4()));
            params.put("d29t5", NoNullString(advanceSalesTemplate.getD29t5()));
            params.put("d29t6", NoNullString(advanceSalesTemplate.getD29t6()));
            params.put("d29t7", NoNullString(advanceSalesTemplate.getD29t7()));
            params.put("d29t8", NoNullString(advanceSalesTemplate.getD29t8()));

            //结尾签章
            params.put("qz1", NoNullString(advanceSalesTemplate.getQz1()));
            params.put("qz2", NoNullString(advanceSalesTemplate.getQz2()));
            params.put("qz3", NoNullString(advanceSalesTemplate.getQz3()));
            if(advanceSalesTemplate.getQz4()!=null){

                String time=DateUtils.StringToDateString(advanceSalesTemplate.getQz4());
                params.put("qz4",NoNullString(time));

            }else {
                params.put("qz4"," ");
            }
            //params.put("qz4", NoNullString(advanceSalesTemplate.getQz4()));
            params.put("qz5", NoNullString(advanceSalesTemplate.getQz5()));
            params.put("qz6", NoNullString(advanceSalesTemplate.getQz6()));
            params.put("qz7", NoNullString(advanceSalesTemplate.getQz7()));
            params.put("qz8", NoNullString(advanceSalesTemplate.getQz8()));
            params.put("qz9", NoNullString(advanceSalesTemplate.getQz9()));
            if(advanceSalesTemplate.getQz10()!=null){
                String time=DateUtils.StringToDateString(advanceSalesTemplate.getQz10());
                params.put("qz10",NoNullString(time));
            }else {
                params.put("qz10"," ");
            }
            params.put("qz11", NoNullString(advanceSalesTemplate.getQz11()));


            //附件6 
            params.put("fj6jw1", NoNullString(advanceSalesTemplate.getFj6jw1()));
            params.put("fj6jw2", NoNullString(advanceSalesTemplate.getFj6jw2()));
            params.put("fj6jw3", NoNullString(advanceSalesTemplate.getFj6jw3()));
            params.put("fj6jw4", NoNullString(advanceSalesTemplate.getFj6jw4()));
            params.put("fj6jw5", NoNullString(advanceSalesTemplate.getFj6jw5()));
            params.put("fj6jw6", NoNullString(advanceSalesTemplate.getFj6jw6()));
            params.put("fj6jw7", NoNullString(advanceSalesTemplate.getFj6jw7()));
            params.put("fj6jw8", NoNullString(advanceSalesTemplate.getFj6jw8()));
            params.put("fj6jw9", NoNullString(advanceSalesTemplate.getFj6jw9()));
            params.put("fj6jw10", NoNullString(advanceSalesTemplate.getFj6jw10()));
            params.put("fj6jw11", NoNullString(advanceSalesTemplate.getFj6jw11()));
            params.put("fj6jw12", NoNullString(advanceSalesTemplate.getFj6jw12()));
            params.put("fj6jw13", NoNullString(advanceSalesTemplate.getFj6jw13()));
            params.put("fj6jw14", NoNullString(advanceSalesTemplate.getFj6jw14()));
            params.put("fj6jw15", NoNullString(advanceSalesTemplate.getFj6jw15()));
            params.put("fj6jw16", NoNullString(advanceSalesTemplate.getFj6jw16()));
            params.put("fj6jw17", NoNullString(advanceSalesTemplate.getFj6jw17()));
            params.put("fj6jw18", NoNullString(advanceSalesTemplate.getFj6jw18()));
            params.put("fj6jw19", NoNullString(advanceSalesTemplate.getFj6jw19()));
            params.put("fj6jw20", NoNullString(advanceSalesTemplate.getFj6jw20()));
            params.put("fj6jw21", NoNullString(advanceSalesTemplate.getFj6jw21()));
            params.put("fj6jw22", NoNullString(advanceSalesTemplate.getFj6jw22()));
            params.put("fj6jw23", NoNullString(advanceSalesTemplate.getFj6jw23()));
            params.put("fj6jw24", NoNullString(advanceSalesTemplate.getFj6jw24()));
            params.put("fj6jw25", NoNullString(advanceSalesTemplate.getFj6jw25()));
            params.put("fj6jw26", NoNullString(advanceSalesTemplate.getFj6jw26()));
            params.put("fj6jw27", NoNullString(advanceSalesTemplate.getFj6jw27()));
            params.put("fj6jw28", NoNullString(advanceSalesTemplate.getFj6jw28()));
            params.put("fj6jw29", NoNullString(advanceSalesTemplate.getFj6jw29()));
            params.put("fj6jw30", NoNullString(advanceSalesTemplate.getFj6jw30()));


            //附件7 
            params.put("fj7jw1", NoNullString(advanceSalesTemplate.getFj7jw1()));
            params.put("fj7jw2", NoNullString(advanceSalesTemplate.getFj7jw2()));
            params.put("fj7jw3", NoNullString(advanceSalesTemplate.getFj7jw3()));
            params.put("fj7jw4", NoNullString(advanceSalesTemplate.getFj7jw4()));
            params.put("fj7jw5", NoNullString(advanceSalesTemplate.getFj7jw5()));
            params.put("fj7jw6", NoNullString(advanceSalesTemplate.getFj7jw6()));
            params.put("fj7jw7", NoNullString(advanceSalesTemplate.getFj7jw7()));
            params.put("fj7jw8", NoNullString(advanceSalesTemplate.getFj7jw8()));
            params.put("fj7jw9", NoNullString(advanceSalesTemplate.getFj7jw9()));
            params.put("fj7jw10", NoNullString(advanceSalesTemplate.getFj7jw10()));
            params.put("fj7jw11", NoNullString(advanceSalesTemplate.getFj7jw11()));
            params.put("fj7jw12", NoNullString(advanceSalesTemplate.getFj7jw12()));
            params.put("fj7jw13", NoNullString(advanceSalesTemplate.getFj7jw13()));
            params.put("fj7jw14", NoNullString(advanceSalesTemplate.getFj7jw14()));

        }

//    public void getParams(Map<String, Object> params,String id){
//
//
//        HouseTrade houseTrade = houseTradeMapper.getHouseTradeById(id);
//        params.put("htbh", NoNullString(houseTrade.getHtbah()));
//        //企业
//        Company company =  houseTradeMapper.getCompanyByAssociatedId(id);
//        if(company!=null) {
//
//            params.put("cmr",company.getQymc());
//            params.put("txdz",NoNullString(company.getAddress()));
//            params.put("yzbm",NoNullString(company.getYzbm()));
//            params.put("yyzz",NoNullString(company.getYyzz()));
//            params.put("zzzsh",NoNullString(company.getZzzsh()));
//            params.put("qyfr",NoNullString(company.getQyfr()));
//            params.put("lxdh",NoNullString(company.getPhone()));
//
//            //开发项目
//            String companyId = company.getId();
//            Project project = houseTradeMapper.getProjectByCompanyId(companyId);
//            if(project!=null) {
//
//                String qdfs = systemDictionaryService.getDicName("ydqdfs",project.getYdqdfs());
//                params.put("qdfs",qdfs);
//                params.put("xmzl",NoNullString(project.getAddress()));
//
//                params.put("tdsyzh",NoNullString(project.getTdsyzh()));
//                params.put("zdmj",DoubleToString(project.getZdmj()));
//
//                String xmyt = systemDictionaryService.getDicName("xmyt",project.getXmyt());
//                params.put("xmyt",NoNullString(xmyt));
//
//                params.put("xmmc",project.getXmmc());
//
//                params.put("tdsyzzzrq",DateUtils.DateToString(project.getTdsyzzzrq()));
//                params.put("jsgcghxkzh",NoNullString(project.getJsgcghxkzh()));
//                params.put("jsgcsgxkzh",NoNullString(project.getJsgcsgxkzh()));
//
//            }else{
//
//                params.put("qdfs","");
//                params.put("xmzl","");
//                params.put("tdsyzh","");
//                params.put("zdmj","");
//                params.put("xmyt","");
//                params.put("xmmc",houseTrade.getXmmc());
//                params.put("tdsyzzzrq","");
//                params.put("jsgcghxkzh","");
//                params.put("jsgcsgxkzh","");
//            }
//
//        }else{
//            params.put("cmr","");
//            params.put("txdz","");
//            params.put("yzbm","");
//            params.put("yyzz","");
//            params.put("zzzsh","");
//            params.put("qyfr","");
//            params.put("lxdh","");
//            params.put("qdfs","");
//            params.put("xmzl","");
//            params.put("tdsyzh","");
//            params.put("zdmj","");
//            params.put("xmyt","");
//            params.put("xmmc","");
//            params.put("tdsyzzzrq","");
//            params.put("jsgcghxkzh","");
//            params.put("jsgcsgxkzh","");
//        }
//
//
//        List<Map<String,String>> listR = houseTradeMapper.getRelationshipByProjectId(id);
//
//        String msr = NoNullString(houseTrade.getBuyer())+",";
//        String msrzjlx = "居民身份证"+",";
//        String zh = NoNullString(houseTrade.getSfzh())+",";
//        String birthday = IDCardUtil.getBirthday(houseTrade.getSfzh())+",";
//        String sex = IDCardUtil.getSex(houseTrade.getSfzh())+",";
//
//        if(listR!=null&&listR.size()>0){
//
//            for (Map<String,String> map:listR) {
//
//                if(!houseTrade.getBuyer().equalsIgnoreCase(map.get("NAME").toString())){
//                    msr += map.get("NAME").toString() + ",";
//                    msrzjlx += map.get("ZJLB").toString() + ",";
//
//                    String zhTemp = map.get("SFZH").toString();
//                    zh += zhTemp + ",";
//                    birthday += IDCardUtil.getBirthday(zhTemp) + ",";
//                    sex += IDCardUtil.getSex(zhTemp) + ",";
//                }
//
//            }
//            msr = msr.substring(0,msr.length()-1);
//            msrzjlx = msrzjlx.substring(0,msrzjlx.length()-1);
//            zh = zh.substring(0,zh.length()-1);
//            birthday = birthday.substring(0,birthday.length()-1);
//            sex = sex.substring(0,sex.length()-1);
//
//        }
//
//
//        params.put("msr", msr);
//
//        params.put("msrzjlx",msrzjlx);
//        params.put("zh", zh);
//
//        params.put("birthday",birthday);
//        params.put("sex",sex);
//
//        params.put("txdz", NoNullString(houseTrade.getLxdz()));
//
//
//        params.put("ysxkz",houseTrade.getYsxkz());
//
//        Map<String,String> map = houseTradeMapper.queryHinfoByTradeId(id);
//        if(map!=null) {
//
//            String fwyt = systemDictionaryService.getDicName("fwyt",map.get("FWYT")!=null?Integer.parseInt(map.get("FWYT").toString()):null);
//            params.put("fwyt",NoNullString(fwyt));
//
//            String jzjg = systemDictionaryService.getDicName("jzjg",map.get("FWJG1")!=null?Integer.parseInt(map.get("FWJG1").toString()):null);
//            params.put("jzjg",NoNullString(jzjg));
//
//            params.put("zcs",map.get("ZCS")!=null?String.valueOf(map.get("ZCS")):"");
//            params.put("dscs",map.get("DSCS")!=null?String.valueOf(map.get("DSCS")):"");
//            params.put("dxcs",map.get("DXCS")!=null?String.valueOf(map.get("DXCS")):"");
//
//            if(1==houseTrade.getHouseType()){
//                params.put("scjzmj",map.get("SCJZMJ")!=null?String.valueOf(map.get("SCJZMJ")):"");
//                params.put("sctnjzmj",map.get("SCTNJZMJ")!=null?String.valueOf(map.get("SCTNJZMJ")):"");
//                params.put("scftjzmj",map.get("SCFTJZMJ")!=null?String.valueOf(map.get("SCFTJZMJ")):"");
//            }else{
//                params.put("ycjzmj",map.get("YCJZMJ")!=null?String.valueOf(map.get("YCJZMJ")):"");
//                params.put("yctnjzmj",map.get("YCTNJZMJ")!=null?String.valueOf(map.get("YCTNJZMJ")):"");
//                params.put("ycftjzmj",map.get("YCFTJZMJ")!=null?String.valueOf(map.get("YCFTJZMJ")):"");
//            }
//        }else{
//            params.put("fwyt","");
//            params.put("jzjg","");
//            params.put("zcs","");
//            params.put("dscs","");
//            params.put("dxcs","");
//
//            if(1==houseTrade.getHouseType()){
//                params.put("scjzmj","");
//                params.put("sctnjzmj","");
//                params.put("scftjzmj","");
//            }else{
//                params.put("ycjzmj","");
//                params.put("yctnjzmj","");
//                params.put("ycftjzmj","");
//            }
//
//        }
//    }

    @Override
    public ResponseBo sh(String id){

        houseTradeMapper.sh(id);
        return ResponseBo.ok();
    }
}
