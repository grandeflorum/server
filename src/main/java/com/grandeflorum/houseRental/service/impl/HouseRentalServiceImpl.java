package com.grandeflorum.houseRental.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import com.grandeflorum.system.service.SysRegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public ResponseBo SaveOrUpdateHouseRental(HouseRental houseRental){


        houseRental.setSysUpdDate(new Date());

        //新增
        if(StrUtil.isNullOrEmpty(houseRental.getId())){
            houseRental.setId(GuidHelper.getGuid());
            houseRental.setSysDate(new Date());

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
