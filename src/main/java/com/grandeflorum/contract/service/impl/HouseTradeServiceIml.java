package com.grandeflorum.contract.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.common.util.XwpfTUtil;
import com.grandeflorum.contract.dao.HouseTradeHistoryMapper;
import com.grandeflorum.contract.dao.HouseTradeMapper;
import com.grandeflorum.contract.domain.HouseTrade;
import com.grandeflorum.contract.domain.HouseTradeHistory;
import com.grandeflorum.contract.service.HouseTradeService;
import com.grandeflorum.project.dao.WFAuditMapper;
import com.grandeflorum.project.domain.AuditParam;
import com.grandeflorum.project.domain.WFAudit;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
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
//            houseTrade.setAuditType(0);
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
    public void printHt(String id , HttpServletResponse response){

        try{

            HouseTrade houseTrade = houseTradeMapper.selectByPrimaryKey(id);
            //读入流中
//            InputStream is =this.getClass().getResourceAsStream("/templates/htTemplates.docx");
            String path = this.getClass().getResource("/").getPath()+ "templates/htTemplates.docx";
            //新建一个word文档
            XWPFDocument doc = new XWPFDocument(new FileInputStream(path));
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("${htbh}", houseTrade.getHtbah());
            params.put("gmr", houseTrade.getBuyer());
            params.put("dj", houseTrade.getDj());
            params.put("zj", houseTrade.getZj());
            params.put("rwsj", houseTrade.getRwsj());

            XwpfTUtil xwpfTUtil = new XwpfTUtil();
            xwpfTUtil.replaceInPara(doc, params);

//            office2PDF.office2PDF(fileSavePath,storageFolder+id+".pdf",rainbowProperties.getOpenoffice());

            OutputStream os = response.getOutputStream();

            response.setContentType("application/vnd.ms-excel");
            response.setHeader("content-disposition", "Attachment;filename=" + URLEncoder.encode("合同模版.docx", "utf-8"));
            doc.write(os);

            xwpfTUtil.close(os);

            os.flush();
            os.close();

        }catch (Exception e) {
            e.printStackTrace();
        }


    }
}
