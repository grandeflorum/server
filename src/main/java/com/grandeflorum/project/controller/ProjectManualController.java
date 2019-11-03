package com.grandeflorum.project.controller;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.project.service.ProjectManualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("ProjectManual")
public class ProjectManualController {

    @Autowired
    private ProjectManualService projectManualService ;
    /**
     * 查询开发项目信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getProjectManualList")
    public ResponseBo getProjectManualList(@RequestBody Page page) {

        return projectManualService.getProjectManualList(page);
    }
}
