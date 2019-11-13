package com.grandeflorum.houseRental.service;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.IService;
import com.grandeflorum.houseRental.domain.HouseRental;
import com.grandeflorum.practitioner.domain.Company;

import java.util.List;

/**
 * Created by 13260 on 2019/11/9.
 */
public interface HouseRentalService extends IService<HouseRental> {

    /**
     * 保存
     * @param houseRental
     * @return
     */
    ResponseBo SaveOrUpdateHouseRental(HouseRental houseRental);

    /**
     * 获取房屋租赁
     * @return
     */
    ResponseBo getHouseRentalList(Page page);

    /**
     * 获取房屋租赁详情
     * @param id
     * @return
     */
    ResponseBo getHouseRentalById(String id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    ResponseBo deleteHouseRentalByIds(List<String> ids);

    ResponseBo linkH(String id,String hid);
}
