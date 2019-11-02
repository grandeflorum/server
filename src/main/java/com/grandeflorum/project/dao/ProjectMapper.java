package com.grandeflorum.project.dao;

import com.grandeflorum.common.config.MyMapper;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.project.domain.Project;
import com.grandeflorum.project.domain.WFAudit;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ProjectMapper extends MyMapper<Project> {
    List<Project> getProjectList(Map<String, Object> map);

    int addOrUpdateAudit(WFAudit param);

    void auditProjectById(Map<String,Object> map);

}
