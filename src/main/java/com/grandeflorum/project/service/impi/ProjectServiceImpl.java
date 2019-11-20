package com.grandeflorum.project.service.impi;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.attachment.domain.FileInfo;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.project.dao.ProjectMapper;
import com.grandeflorum.project.dao.WFAuditMapper;
import com.grandeflorum.project.domain.AuditParam;
import com.grandeflorum.project.domain.Project;
import com.grandeflorum.project.domain.ProjectDialog;
import com.grandeflorum.project.domain.WFAudit;
import com.grandeflorum.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("ProjectService")
public class ProjectServiceImpl extends BaseService<Project> implements ProjectService {

    @Autowired
    ProjectMapper projectMapper;

    @Autowired
    WFAuditMapper wFAuditMapper;

    @Override
    public String saveOrUpdateProject(Project project) {
        if (project.getId() == null) {
            project.setId(GuidHelper.getGuid());
            project.setAuditType(0);
            project.setSysDate(new Date());
            project.setSysUpdDate(new Date());
            projectMapper.insert(project);
        } else {
            project.setSysUpdDate(new Date());
            projectMapper.updateByPrimaryKey(project);
        }
        return project.getId();
    }

    @Override
    public ResponseBo getProjectById(String id) {
        Project result = projectMapper.selectByPrimaryKey(id);
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.error("查询失败");
    }

    @Override
    public ResponseBo getProjectList(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<Project> list = projectMapper.getProjectList(map);

        PageInfo<Project> pageInfo = new PageInfo<Project>(list);

        PagingEntity<Project> result = new PagingEntity<>(pageInfo);
        return ResponseBo.ok(result);
    }

    @Override
    public ResponseBo auditProjects(AuditParam param) {
        WFAudit wf = param.getWfAudit();
        for (String id : param.ids) {
            //更新项目表信息
            if(param.getWfAudit().getShjg()==1){
                auditProjectById(id, 2);
            }else{
                auditProjectById(id, 3);
            }

            //添加或更新审核表信息
            WFAudit wfAudit = new WFAudit();
            wfAudit.setId(GuidHelper.getGuid());

            wfAudit.setShjg(wf.getShjg());
            wfAudit.setShry(wf.getShry());
            wfAudit.setShrq(wf.getShrq());
            wfAudit.setBz(wf.getBz());

            wfAudit.setProjectid(id);
            wfAudit.setSysDate(new Date());
            wfAudit.setSysUpdDate(new Date());

            wFAuditMapper.insert(wfAudit);
        }
        return ResponseBo.ok();
    }

    @Override
    public ResponseBo auditProjectById(String id, int type) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("type", type);

        projectMapper.auditProjectById(map);

        return ResponseBo.ok();
    }

    @Override
    public ResponseBo getProjectDialog(Page page) {
        PageHelper.startPage(page.getPageNo(), page.getPageSize());
        Map<String, Object> map = page.getQueryParameter();
        List<ProjectDialog> list = projectMapper.getProjectDialog(map);

        PageInfo<ProjectDialog> pageInfo = new PageInfo<ProjectDialog>(list);

        PagingEntity<ProjectDialog> result = new PagingEntity<>(pageInfo);
        return ResponseBo.ok(result);
    }
}
