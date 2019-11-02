package com.grandeflorum.project.controller;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.project.domain.AuditParam;
import com.grandeflorum.project.domain.Project;
import com.grandeflorum.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Project")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    /**
     * 添加或修改开发项目
     *
     * @param
     * @return
     */
    @PostMapping("/saveOrUpdateProject")
    public ResponseBo saveOrUpdateProject(@RequestBody Project project) {
        String projectId = projectService.saveOrUpdateProject(project);
        return ResponseBo.ok(projectId);
    }

    /**
     * 获取组织信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getProjectById")
    public ResponseBo getProjectById(String id) {
        return projectService.getProjectById(id);
    }

    /**
     * 删除开发项目信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteProjectByIds")
    public ResponseBo deleteProjectByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            projectService.batchDelete(ids, "id", Project.class);
        }
        return ResponseBo.ok();
    }

    /**
     * 查询开发项目信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getProjectList")
    public ResponseBo getProjectList(@RequestBody Page page) {

        return projectService.getProjectList(page);
    }


    /**
     * 审核项目
     *
     * @param param
     * @return
     */
    @PostMapping("/auditProjects")
    public ResponseBo auditProjects(@RequestBody AuditParam param) {
        if ((param.ids != null) && (param.ids.size() > 0)) {
            projectService.auditProjects(param);
        }
        return ResponseBo.ok();
    }

    /**
     * 提交审核项目
     *
     * @param
     * @return
     */
    @GetMapping("/auditProjectById")
    public ResponseBo auditProjectById(String id,int type) {
        return projectService.auditProjectById(id, type);
    }


}
