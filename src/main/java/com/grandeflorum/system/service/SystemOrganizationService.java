package com.grandeflorum.system.service;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.IService;
import com.grandeflorum.system.domain.SystemOrganization;

/**
 * Created by 13260 on 2019/11/1.
 */
public interface SystemOrganizationService extends IService<SystemOrganization> {

    int addOrganization(SystemOrganization organization);

    int modifyOrganization(SystemOrganization organization);

    ResponseBo getOrganizationList(Page page);

    ResponseBo getOrganizationById(String id);
}
