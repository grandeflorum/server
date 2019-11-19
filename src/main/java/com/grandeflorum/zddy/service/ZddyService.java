package com.grandeflorum.zddy.service;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.IService;
import com.grandeflorum.zddy.domain.Zddy;

import java.util.List;

/**
 * Created by 13260 on 2019/11/19.
 */
public interface ZddyService extends IService<Zddy>{

    /**
     * 保存
     * @param dy
     * @return
     */
    ResponseBo SaveOrUpdateZddy(Zddy dy);

    /**
     * 获取宗地抵押
     * @return
     */
    ResponseBo getZddyList(Page page);

    /**
     * 获取宗地抵押详情
     * @param id
     * @return
     */
    ResponseBo getZddyById(String id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    ResponseBo deleteZddyByIds(List<String> ids);

    ResponseBo updateZddyTypeById(String id,int type);
}
