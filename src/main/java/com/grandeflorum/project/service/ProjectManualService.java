package com.grandeflorum.project.service;

import com.grandeflorum.attachment.domain.FileInfo;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.IService;

public interface ProjectManualService extends IService<FileInfo> {
    //获取项目列表
    ResponseBo getProjectManualList(Page page);
}
