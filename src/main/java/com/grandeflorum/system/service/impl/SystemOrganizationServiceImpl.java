package com.grandeflorum.system.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.common.util.StrUtil;
import com.grandeflorum.system.dao.SystemOrganizationMapper;
import com.grandeflorum.system.domain.SystemOrganization;
import com.grandeflorum.system.service.SystemOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 13260 on 2019/11/1.
 */
@Service("SystemOrganizationService")
public class SystemOrganizationServiceImpl extends BaseService<SystemOrganization> implements SystemOrganizationService {

    @Autowired
    SystemOrganizationMapper mapper;

    @Override
    public int addOrganization(SystemOrganization organization) {

        if(StrUtil.isNullOrEmpty(organization.getId())){
            organization.setId(GuidHelper.getGuid());
        }
        return mapper.insert(organization);
    }

    @Override
    public int modifyOrganization(SystemOrganization organization) {
        return mapper.updateByPrimaryKey(organization);
    }

    @Override
    public ResponseBo getOrganizationList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<SystemOrganization> list = mapper.getOrganizationList(map);

        PageInfo<SystemOrganization> pageInfo = new PageInfo<SystemOrganization>(list);

        PagingEntity<SystemOrganization> result = new PagingEntity<>(pageInfo);

        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo getOrganizationById(String id) {
        SystemOrganization result = mapper.selectByPrimaryKey(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }
}
