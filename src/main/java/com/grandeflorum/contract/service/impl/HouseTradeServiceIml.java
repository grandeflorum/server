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
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import java.util.Date;
import java.util.List;

import static com.grandeflorum.common.util.StrUtil.DoubleToString;
import static com.grandeflorum.common.util.StrUtil.NoNullString;

@Service("HouseTradeService")
public class HouseTradeServiceIml extends BaseService<HouseTrade> implements HouseTradeService {

    @Autowired
    HouseTradeMapper houseTradeMapper;

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

            HouseTrade houseTrade = houseTradeMapper.selectByPrimaryKey(id);
            String name = "商品房买卖合同（预售）.docx";
            if(1==houseTrade.getHouseType()) {
                name = "商品房买卖合同（现售）.docx";
            }
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-disposition", "Attachment;filename=" + URLEncoder.encode(name, "utf-8"));
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
//            if(file1.exists()){
//                if(os!=null){
//
//                    os.write(FileUtils.readFileToByteArray(file1));
//                }
//                office2PDF.office2PDF(sourcePath+"/"+id+".docx",sourcePath+"/"+id+".pdf",grandeflorumProperties.getOpenoffice());
//
//            }else{

                //生成二维码
                QrCodeUtil.createQrCode(grandeflorumProperties.getQrCodePath()+"?id="+id+"&type=1",sourcePath+"/",id+".png");

                //读入流中
                String path = this.getClass().getResource("/").getPath()+ "templates/"+name;
                //新建一个word文档
                XWPFDocument doc = new XWPFDocument(new FileInputStream(path));
                XWPFDocument docTemp = new XWPFDocument(new FileInputStream(path));

                Map<String, Object> params = new HashMap<String, Object>();

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
            getParams(map,id);
            map.put("house",houseTrade);

            return ResponseBo.ok(map);
        }

        stockTradeService.getParams(map,id);
        map.put("house",stockTradeMapper.getStockTradeById(id));

        return ResponseBo.ok(map);
    }

    public void getParams(Map<String, Object> params,String id){


        HouseTrade houseTrade = houseTradeMapper.getHouseTradeById(id);
        params.put("htbh", NoNullString(houseTrade.getHtbah()));
        //企业
        Company company =  houseTradeMapper.getCompanyByAssociatedId(id);
        if(company!=null) {

            params.put("cmr",company.getQymc());
            params.put("txdz",NoNullString(company.getAddress()));
            params.put("yzbm",NoNullString(company.getYzbm()));
            params.put("yyzz",NoNullString(company.getYyzz()));
            params.put("zzzsh",NoNullString(company.getZzzsh()));
            params.put("qyfr",NoNullString(company.getQyfr()));
            params.put("lxdh",NoNullString(company.getPhone()));

            //开发项目
            String companyId = company.getId();
            Project project = houseTradeMapper.getProjectByCompanyId(companyId);
            if(project!=null) {

                String qdfs = systemDictionaryService.getDicName("ydqdfs",project.getYdqdfs());
                params.put("qdfs",qdfs);
                params.put("xmzl",NoNullString(project.getAddress()));

                params.put("tdsyzh",NoNullString(project.getTdsyzh()));
                params.put("zdmj",DoubleToString(project.getZdmj()));

                String xmyt = systemDictionaryService.getDicName("xmyt",project.getXmyt());
                params.put("xmyt",NoNullString(xmyt));

                params.put("xmmc",project.getXmmc());

                params.put("tdsyzzzrq",DateUtils.DateToString(project.getTdsyzzzrq()));
                params.put("jsgcghxkzh",NoNullString(project.getJsgcghxkzh()));
                params.put("jsgcsgxkzh",NoNullString(project.getJsgcsgxkzh()));

            }else{

                params.put("qdfs","");
                params.put("xmzl","");
                params.put("tdsyzh","");
                params.put("zdmj","");
                params.put("xmyt","");
                params.put("xmmc",houseTrade.getXmmc());
                params.put("tdsyzzzrq","");
                params.put("jsgcghxkzh","");
                params.put("jsgcsgxkzh","");
            }

        }else{
            params.put("cmr","");
            params.put("txdz","");
            params.put("yzbm","");
            params.put("yyzz","");
            params.put("zzzsh","");
            params.put("qyfr","");
            params.put("lxdh","");
            params.put("qdfs","");
            params.put("xmzl","");
            params.put("tdsyzh","");
            params.put("zdmj","");
            params.put("xmyt","");
            params.put("xmmc","");
            params.put("tdsyzzzrq","");
            params.put("jsgcghxkzh","");
            params.put("jsgcsgxkzh","");
        }


        List<Map<String,String>> listR = houseTradeMapper.getRelationshipByProjectId(id);

        String msr = NoNullString(houseTrade.getBuyer())+",";
        String msrzjlx = "居民身份证"+",";
        String zh = NoNullString(houseTrade.getSfzh())+",";
        String birthday = IDCardUtil.getBirthday(houseTrade.getSfzh())+",";
        String sex = IDCardUtil.getSex(houseTrade.getSfzh())+",";

        if(listR!=null&&listR.size()>0){

            for (Map<String,String> map:listR) {

                if(!houseTrade.getBuyer().equalsIgnoreCase(map.get("NAME").toString())){
                    msr += map.get("NAME").toString() + ",";
                    msrzjlx += map.get("ZJLB").toString() + ",";

                    String zhTemp = map.get("SFZH").toString();
                    zh += zhTemp + ",";
                    birthday += IDCardUtil.getBirthday(zhTemp) + ",";
                    sex += IDCardUtil.getSex(zhTemp) + ",";
                }

            }
            msr = msr.substring(0,msr.length()-1);
            msrzjlx = msrzjlx.substring(0,msrzjlx.length()-1);
            zh = zh.substring(0,zh.length()-1);
            birthday = birthday.substring(0,birthday.length()-1);
            sex = sex.substring(0,sex.length()-1);

        }


        params.put("msr", msr);

        params.put("msrzjlx",msrzjlx);
        params.put("zh", zh);

        params.put("birthday",birthday);
        params.put("sex",sex);

        params.put("txdz", NoNullString(houseTrade.getLxdz()));


        params.put("ysxkz",houseTrade.getYsxkz());

        Map<String,String> map = houseTradeMapper.queryHinfoByTradeId(id);
        if(map!=null) {

            String fwyt = systemDictionaryService.getDicName("fwyt",map.get("FWYT")!=null?Integer.parseInt(map.get("FWYT").toString()):null);
            params.put("fwyt",NoNullString(fwyt));

            String jzjg = systemDictionaryService.getDicName("jzjg",map.get("FWJG1")!=null?Integer.parseInt(map.get("FWJG1").toString()):null);
            params.put("jzjg",NoNullString(jzjg));

            params.put("zcs",map.get("ZCS")!=null?String.valueOf(map.get("ZCS")):"");
            params.put("dscs",map.get("DSCS")!=null?String.valueOf(map.get("DSCS")):"");
            params.put("dxcs",map.get("DXCS")!=null?String.valueOf(map.get("DXCS")):"");

            if(1==houseTrade.getHouseType()){
                params.put("scjzmj",map.get("SCJZMJ")!=null?String.valueOf(map.get("SCJZMJ")):"");
                params.put("sctnjzmj",map.get("SCTNJZMJ")!=null?String.valueOf(map.get("SCTNJZMJ")):"");
                params.put("scftjzmj",map.get("SCFTJZMJ")!=null?String.valueOf(map.get("SCFTJZMJ")):"");
            }else{
                params.put("ycjzmj",map.get("YCJZMJ")!=null?String.valueOf(map.get("YCJZMJ")):"");
                params.put("yctnjzmj",map.get("YCTNJZMJ")!=null?String.valueOf(map.get("YCTNJZMJ")):"");
                params.put("ycftjzmj",map.get("YCFTJZMJ")!=null?String.valueOf(map.get("YCFTJZMJ")):"");
            }
        }else{
            params.put("fwyt","");
            params.put("jzjg","");
            params.put("zcs","");
            params.put("dscs","");
            params.put("dxcs","");

            if(1==houseTrade.getHouseType()){
                params.put("scjzmj","");
                params.put("sctnjzmj","");
                params.put("scftjzmj","");
            }else{
                params.put("ycjzmj","");
                params.put("yctnjzmj","");
                params.put("ycftjzmj","");
            }

        }
    }

    @Override
    public ResponseBo sh(String id){

        houseTradeMapper.sh(id);
        return ResponseBo.ok();
    }
}
