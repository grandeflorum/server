package com.grandeflorum.project.service.impi;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.attachment.domain.FileInfo;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.project.dao.ProjectManualMapper;
import com.grandeflorum.project.service.ProjectManualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service("ProjectManualService")
public class ProjectManualServiceImpl extends BaseService<FileInfo> implements ProjectManualService{

    @Autowired
    ProjectManualMapper projectManualMapper;

    @Override
    public ResponseBo getProjectManualList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<FileInfo> list = projectManualMapper.getProjectManualList(map);

        PageInfo<FileInfo> pageInfo = new PageInfo<FileInfo>(list);

        PagingEntity<FileInfo> result = new PagingEntity<>(pageInfo);
        return ResponseBo.ok(result);
    }
}
