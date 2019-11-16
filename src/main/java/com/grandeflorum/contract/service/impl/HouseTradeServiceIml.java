package com.grandeflorum.contract.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.common.config.GrandeflorumProperties;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.*;
import com.grandeflorum.contract.dao.ContractnumMapper;
import com.grandeflorum.contract.dao.HouseTradeHistoryMapper;
import com.grandeflorum.contract.dao.HouseTradeMapper;
import com.grandeflorum.contract.domain.Contractnum;
import com.grandeflorum.contract.domain.HouseTrade;
import com.grandeflorum.contract.domain.HouseTradeHistory;
import com.grandeflorum.contract.service.HouseTradeService;
import com.grandeflorum.project.dao.WFAuditMapper;
import com.grandeflorum.project.domain.AuditParam;
import com.grandeflorum.project.domain.WFAudit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
                wfAudit.setCurrentStatus(houseTrade.getCurrentStatus());
                wFAuditMapper.insert(wfAudit);
                if(wfAudit.getShjg()==1){
                    houseTrade.setIsPass(1);
                    houseTrade.setCurrentStatus(houseTrade.getCurrentStatus() + 1);

                    if(houseTrade.getCurrentStatus()==4){
                        houseTrade.setHtbah(this.getHTBAH("HouseTrade"));
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
    public String saveOrUpdateHouseTrade(HouseTrade houseTrade) {
        if (houseTrade.getId() == null) {
            houseTrade.setId(GuidHelper.getGuid());
            houseTrade.setCurrentStatus(0);
            houseTrade.setSysDate(new Date());
            houseTradeMapper.insert(houseTrade);
        } else {
            houseTrade.setSysUpdDate(new Date());
            houseTradeMapper.updateByPrimaryKey(houseTrade);
        }
        return houseTrade.getId();
    }

    @Override
    public ResponseBo getHouseTradeById(String id) {
        HouseTrade result = houseTradeMapper.selectByPrimaryKey(id);
        if (result != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("shjg", 1);
            map.put("projectid", id);
            List<WFAudit> list = wFAuditMapper.getWFAuditList(map);
            result.setWfAuditList(list);
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public ResponseBo getHouseTradeList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<HouseTrade> list = houseTradeMapper.getHouseTradeList(map);

        PageInfo<HouseTrade> pageInfo = new PageInfo<HouseTrade>(list);

        PagingEntity<HouseTrade> result = new PagingEntity<>(pageInfo);
        return ResponseBo.ok(result);
    }

    //打印
    @Override
    public void printHt(String id ,String type, HttpServletResponse response){

        try{

            HouseTrade houseTrade = houseTradeMapper.selectByPrimaryKey(id);
            //读入流中
            String path = this.getClass().getResource("/").getPath()+ "templates/htTemplates.docx";
            //新建一个word文档
            XWPFDocument doc = new XWPFDocument(new FileInputStream(path));
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("htbh", StrUtil.NoNullString(houseTrade.getHtbah()));
            params.put("gmr",StrUtil.NoNullString(houseTrade.getBuyer()));
            params.put("dj", StrUtil.DoubleToString(houseTrade.getDj()));
            params.put("zj", StrUtil.DoubleToString(houseTrade.getZj()));
            params.put("rwsj", DateUtils.DateToString(houseTrade.getRwsj()));

            XwpfTUtil xwpfTUtil = new XwpfTUtil();
            xwpfTUtil.replaceInPara(doc, params);

            //转化为pdf
            String sourcePath = grandeflorumProperties.getUploadFolder()+"ht";
            File file = new File(sourcePath);

            if (!file.exists()) {
                file.mkdirs();
            }
            String fileSavePath = sourcePath+"/"+id+".docx";
            File file1 = new File(fileSavePath);
            doc.write(new FileOutputStream(fileSavePath));
            office2PDF.office2PDF(fileSavePath,sourcePath+"/"+id+".pdf",grandeflorumProperties.getOpenoffice());

            OutputStream os = response.getOutputStream();
            response.setContentType("application/vnd.ms-excel");

            String fileName = type.equals("1")?"合同模版.docx":"合同模版.pdf";
            response.setHeader("content-disposition", "Attachment;filename=" + URLEncoder.encode("合同模版.docx", "utf-8"));


            if(type.equals("1")){
                doc.write(os);
            }else{
                File filePdf = new File(sourcePath+"/"+id+".pdf");
                if(filePdf.exists()){
                    os.write(FileUtils.readFileToByteArray(filePdf));
                }
            }


            xwpfTUtil.close(os);

            os.flush();
            os.close();

        }catch (Exception e) {
            e.printStackTrace();
        }


    }
}
