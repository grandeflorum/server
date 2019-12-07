package com.grandeflorum.houseRental.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.common.cache.EHCacheUtils;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.common.util.StrUtil;
import com.grandeflorum.houseRental.dao.HouseRentalMapper;
import com.grandeflorum.houseRental.domain.HouseRental;
import com.grandeflorum.houseRental.domain.HouseRentalExtend;
import com.grandeflorum.houseRental.service.HouseRentalService;
import com.grandeflorum.system.domain.SystemUser;
import com.grandeflorum.system.service.SysRegionService;
import com.grandeflorum.system.service.SystemUserService;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 13260 on 2019/11/9.
 */
@Service("HouseRentalService")
public class HouseRentalServiceImpl extends BaseService<HouseRental> implements HouseRentalService {

    @Autowired
    HouseRentalMapper houseRentalMapper;

    @Autowired
    SysRegionService sysRegionService;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    SystemUserService systemUserService;

    @Override
    public ResponseBo SaveOrUpdateHouseRental(HouseRental houseRental){

        SystemUser user = EHCacheUtils.getCurrentUser(cacheManager);
        houseRental.setSysUpdDate(new Date());

        //新增
        if(StrUtil.isNullOrEmpty(houseRental.getId())){
            houseRental.setId(GuidHelper.getGuid());
            houseRental.setSysDate(new Date());

            if(user!=null){
                houseRental.setDjr(user.getId());
            }

            //房屋出租编号
            String num = houseRental.getRegioncode();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            String dateString = formatter.format(new Date());

            String nums = StrUtil.FillNum(houseRentalMapper.getHouseRentalLastXh());

            houseRental.setSerialnumber(num+dateString+nums);

            houseRentalMapper.insert(houseRental);
            return ResponseBo.ok(houseRental.getId());
        }

        houseRentalMapper.updateByPrimaryKey(houseRental);
        return ResponseBo.ok(houseRental.getId());
    }

    /**
     * 获取企业列表
     * @param page
     * @return
     */
    @Override
    public ResponseBo getHouseRentalList(Page page){

        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();

        //获取过滤条件
        systemUserService.getSelectInfo(map);

        if(map.containsKey("regioncode")) {
            String code = sysRegionService.getFilterCode(map.get("regioncode").toString());
            map.put("regioncode", code);
        }
        List<HouseRentalExtend> list = houseRentalMapper.getHouseRentalList(map);

        PageInfo<HouseRentalExtend> pageInfo = new PageInfo<HouseRentalExtend>(list);

        PagingEntity<HouseRentalExtend> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getHouseRentalById(String id){

        HouseRental houseRental =  houseRentalMapper.selectByPrimaryKey(id);

        if(!StrUtil.isNullOrEmpty(houseRental.getHouseId())){
            houseRental.setLjzid( houseRentalMapper.getLjzh(houseRental.getHouseId()));
        }
        return ResponseBo.ok(houseRental);
    }

    @Override
    public ResponseBo deleteHouseRentalByIds(List<String> ids){

        if ((ids != null) && (ids.size() > 0)) {
            for (String str:ids) {
                houseRentalMapper.deleteByPrimaryKey(str);
            }
        }
        return ResponseBo.ok();
    }

    @Override
    public ResponseBo linkH(String id,String hid){

        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("hid",hid);

        houseRentalMapper.linkH(map);

        return ResponseBo.ok();
    }

}
