package com.grandeflorum.zddy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.common.cache.EHCacheUtils;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.common.util.StrUtil;
import com.grandeflorum.system.domain.SystemUser;
import com.grandeflorum.system.service.SystemUserService;
import com.grandeflorum.zddy.dao.ZddyMapper;
import com.grandeflorum.zddy.domain.Zddy;
import com.grandeflorum.zddy.service.ZddyService;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 13260 on 2019/11/19.
 */
@Service("zddyService")
public class ZddyServiceImpl extends BaseService<Zddy> implements ZddyService {

    @Autowired
    ZddyMapper zddyMapper;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    SystemUserService systemUserService;

    @Override
    public ResponseBo SaveOrUpdateZddy(Zddy dy){
        SystemUser user = EHCacheUtils.getCurrentUser(cacheManager);

        dy.setSysUpdDate(new Date());

        //新增
        if(StrUtil.isNullOrEmpty(dy.getId())){
            dy.setId(GuidHelper.getGuid());
            dy.setSysDate(new Date());

            dy.setDyType((short)0);

            if(user!=null){
                dy.setDjr(user.getId());
            }

            zddyMapper.insert(dy);

            return ResponseBo.ok(dy.getId());

        }

        zddyMapper.updateByPrimaryKey(dy);
        return ResponseBo.ok(dy.getId());
    }

    /**
     * 获取宗地抵押列表
     * @param page
     * @return
     */
    @Override
    public ResponseBo getZddyList(Page page){

        Map<String, Object> map = page.getQueryParameter();

        //获取过滤条件
        systemUserService.getSelectInfo(map);

        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        List<Zddy> list = zddyMapper.getZddyList(map);

        PageInfo<Zddy> pageInfo = new PageInfo<Zddy>(list);

        PagingEntity<Zddy> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getZddyById(String id){

        Zddy zddy =  zddyMapper.getZddyById(id);
        //Zddy zddy =  zddyMapper.selectByPrimaryKey(id);
        return ResponseBo.ok(zddy);
    }
//    public ResponseBo getZddyById(String id){
//
//        Zddy zddy =  zddyMapper.selectByPrimaryKey(id);
//        return ResponseBo.ok(zddy);
//    }

    @Override
    public ResponseBo deleteZddyByIds(List<String> ids){

        if ((ids != null) && (ids.size() > 0)) {
            for (String str:ids) {
                zddyMapper.deleteByPrimaryKey(str);
            }
        }
        return ResponseBo.ok();
    }

    @Override
    public ResponseBo updateZddyTypeById(String id,int type) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("type", type);

        zddyMapper.updateZddyTypeById(map);
        return ResponseBo.ok();
    }

    @Override
    public ResponseBo linkDyxxByBdcdyh(String id,String bdcdyh){

        String zrzh = zddyMapper.selectZrzhByBdcdyh(bdcdyh);

        if(StrUtil.isNullOrEmpty(zrzh)){
            return ResponseBo.ok("不存在该不动产单元号对应的楼盘");
        }

        restrictedProperty(id,zrzh,bdcdyh,"1");
        return ResponseBo.ok();
    }

    @Override
    public ResponseBo restrictedProperty(String id,String zh,String bdcdyh,String type) {

        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("type", type);
        map.put("zh", zh);

        if(StrUtil.isNullOrEmpty(bdcdyh)){
            bdcdyh =zddyMapper.selectBdcdyhByZh(map);
        }
        map.put("bdcdyh", bdcdyh);

        zddyMapper.restrictedProperty(map);
        return ResponseBo.ok();
    }
}
