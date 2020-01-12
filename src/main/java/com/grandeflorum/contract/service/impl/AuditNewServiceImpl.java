package com.grandeflorum.contract.service.impl;


import com.grandeflorum.common.cache.EHCacheUtils;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.contract.dao.HouseTradeHistoryMapper;
import com.grandeflorum.contract.dao.HouseTradeMapper;
import com.grandeflorum.contract.domain.AuditNew;
import com.grandeflorum.contract.service.AuditNewService;
import com.grandeflorum.project.dao.WFAuditMapper;
import com.grandeflorum.project.domain.WFAudit;
import com.grandeflorum.system.domain.SystemUser;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by 13260 on 2020/1/12.
 */
@Service("AuditNewService")
public class AuditNewServiceImpl implements AuditNewService {

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private WFAuditMapper wFAuditMapper;

    @Override
    public ResponseBo getAuditNewInfo(String id, int type){

        AuditNew auditNew = new AuditNew();
        SystemUser user = EHCacheUtils.getCurrentUser(cacheManager);

        Map<String,Object> map = new HashMap<>();

        map.put("projectId",id);
        map.put("currentStatus",type);

        map.put("userType",0);
        int otherType = 0;
        if("万年县自然资源局".equalsIgnoreCase(user.getRootOrgName())){
            map.put("userType",1);
            otherType = 2;
        }else if("万年县住建局".equalsIgnoreCase(user.getRootOrgName())){
            map.put("userType",2);
            otherType = 1;
        }

        map.put("userId",user.getId());
        //1 查询自己的
        WFAudit wfAudit = wFAuditMapper.getWFAuditInfo(map);
        if(wfAudit == null){
            wfAudit = new WFAudit();
        }
        //2 查询别人的
        List<WFAudit> list = wFAuditMapper.getWFAuditOtherInfo(map);
        if(list == null){
            list = new ArrayList<>();
        }
        wfAudit.setOtherWf(list);

        //3 查询另一个局的意见
        WFAudit wFAuditOther =  new WFAudit();
        map.put("userType",otherType);
        List<WFAudit> listOther = wFAuditMapper.getWFAuditOtherInfo(map);
        if(listOther == null){
            listOther = new ArrayList<>();
        }
        wFAuditOther.setOtherWf(listOther);


        if("万年县自然资源局".equalsIgnoreCase(user.getRootOrgName())){
            auditNew.setZrzyj(wfAudit);
            auditNew.setZjj(wFAuditOther);
        }else if("万年县住建局".equalsIgnoreCase(user.getRootOrgName())){
            auditNew.setZjj(wfAudit);
            auditNew.setZrzyj(wFAuditOther);
        }


        return ResponseBo.ok(auditNew);
    }

}
