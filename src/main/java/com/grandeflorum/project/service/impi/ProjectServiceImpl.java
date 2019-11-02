package com.grandeflorum.project.service.impi;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.PagingEntity;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.service.impl.BaseService;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.project.dao.ProjectMapper;
import com.grandeflorum.project.domain.AuditParam;
import com.grandeflorum.project.domain.Project;
import com.grandeflorum.project.domain.WFAudit;
import com.grandeflorum.project.service.ProjectService;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.plugin2.message.PrintAppletMessage;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("ProjectService")
public class ProjectServiceImpl extends BaseService<Project> implements ProjectService {

    @Autowired
    ProjectMapper projectMapper;

    @Override
    public String saveOrUpdateProject(Project project) {
        if (project.getId() == null) {
            project.setId(GuidHelper.getGuid());
            project.setAuditType(0);
            project.setSysDate(new Date());
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
        for (String id : param.ids) {
            //更新项目表信息
            auditProjectById(id, 2);

            //添加或更新审核表信息
            if (param.getWfAudit() != null && param.getWfAudit().getId() == null) {
                param.getWfAudit().setId(GuidHelper.getGuid());
            }
            param.getWfAudit().setProjectId(id);
            projectMapper.addOrUpdateAudit(param.getWfAudit());
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
}
