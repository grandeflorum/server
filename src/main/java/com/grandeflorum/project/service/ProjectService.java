package com.grandeflorum.project.service;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.IService;
import com.grandeflorum.project.domain.AuditParam;
import com.grandeflorum.project.domain.Project;

import java.util.List;

public interface ProjectService extends IService<Project> {

    // 保持或更新项目信息
    String saveOrUpdateProject(Project project);

    // 获取项目信息
    ResponseBo getProjectById(String id);

    //获取项目列表
    ResponseBo getProjectList(Page page);

    //审核项目
    ResponseBo auditProjects(AuditParam auditParam);


    /**
     * 审核
     * @param id
     * @param type
     * @return
     */
    ResponseBo auditProjectById(String id,int type);

    ResponseBo getProjectDialog(Page page);


}
