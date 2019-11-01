package com.grandeflorum.system.controller;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.system.domain.SystemRole;
import com.grandeflorum.system.service.SystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/11/1.
 */
@RestController
@RequestMapping("SystemRole")
public class SystemRoleController {

    @Autowired
    private SystemRoleService roleService;

    /**
     * 添加角色信息
     *
     * @param
     * @return
     */
    @PostMapping("/addRole")
    public ResponseBo add(@RequestBody SystemRole roleWithMenu) {
        int result = roleService.addRole(roleWithMenu);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改角色信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyRole")
    public ResponseBo modify(@RequestBody SystemRole roleWithMenu) {

        int result = roleService.modifyRole(roleWithMenu);

        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }

    /**
     * 获取角色列表
     *
     * @param page
     * @return
     */
    @PostMapping("/getRoleList")
    public ResponseBo getWelderList(@RequestBody Page page) {
        return roleService.getRoleList(page);
    }

    /*
     * 获取角色详情
     *
     * @param id
     *
     * @returnRole
     */
    @GetMapping("/getRoleById")
    public ResponseBo getById(String id) {
        SystemRole roleWithMenu = roleService.getRoleById(id);
        return ResponseBo.ok(roleWithMenu);
    }

    /**
     * 删除角色信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteRoleByIds")
    public ResponseBo deleteRoleByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            for (String id : ids) {
                roleService.deleteRoleById(id);
            }
            return ResponseBo.ok("删除成功!");
        }
        return ResponseBo.error("删除失败，请重试!");
    }

    @PostMapping("/getAllRoles")
    public ResponseBo getAllRoles() {

        List<SystemRole> result = roleService.selectAll();
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.ok("获取失败!");
    }
}
