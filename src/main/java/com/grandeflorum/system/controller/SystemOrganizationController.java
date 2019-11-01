package com.grandeflorum.system.controller;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.system.domain.SystemOrganization;
import com.grandeflorum.system.service.SystemOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/11/1.
 */
@RestController
@RequestMapping("SystemOrganization")
public class SystemOrganizationController {

    @Autowired
    SystemOrganizationService organizationService;

    /**
     * 添加组织信息
     *
     * @param
     * @return
     */
    @PostMapping("/addOrganization")
    public ResponseBo add(@RequestBody SystemOrganization Organization) {
        Organization.setId(GuidHelper.getGuid());
        int result = organizationService.addOrganization(Organization);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改组织信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyOrganization")
    public ResponseBo modify(@RequestBody SystemOrganization Organization) {

        int result = organizationService.modifyOrganization(Organization);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }

    /**
     * 获取组织信息
     *
     * @param page
     * @return
     */
    @PostMapping("/getOrganizationList")
    public ResponseBo getOrganizationList(@RequestBody Page page) {

        return organizationService.getOrganizationList(page);
    }

    /**
     * 获取组织信息详情
     *
     * @param id
     * @return
     */
    @GetMapping("/getOrganizationById")
    public ResponseBo getOrganizationById(String id) {
        return organizationService.getOrganizationById(id);
    }

    /**
     * 删除组织信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteOrganizationByIds")
    public ResponseBo deleteOrganizationByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            organizationService.batchDelete(ids, "id", SystemOrganization.class);
        }
        return ResponseBo.ok();
    }

    @PostMapping("/getAllOrganization")
    public ResponseBo getAllOrganization() {

        List<SystemOrganization> result = organizationService.selectAll();
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.ok("获取失败!");
    }
}
