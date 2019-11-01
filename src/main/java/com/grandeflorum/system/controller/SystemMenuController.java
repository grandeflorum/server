package com.grandeflorum.system.controller;

import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.system.domain.SystemMenu;
import com.grandeflorum.system.service.SystemMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 13260 on 2019/11/1.
 */
@RestController
@RequestMapping("SystemMenu")
public class SystemMenuController {

    @Autowired
    private SystemMenuService menuService;

    /**
     * 添加权限菜单信息
     *
     * @param
     * @return
     */
    @PostMapping("/addMenu")
    public ResponseBo add(@RequestBody SystemMenu systemMenu) {
        int result = menuService.addSystemMenu(systemMenu);

        if (result == 1) {
            return ResponseBo.ok("保存成功");
        } else {
            return ResponseBo.error("保存失败");
        }
    }

    /**
     * 修改权限菜单信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyMenu")
    public ResponseBo modify(@RequestBody SystemMenu systemMenu) {

        int result = menuService.updateAll(systemMenu);

        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }

    /**
     * 删除权限菜单信息
     *
     * @param ids
     * @return
     */
    @PostMapping("/deleteMenuByIds")
    public ResponseBo deleteSystemMenuByIds(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            menuService.batchDelete(ids, "id", SystemMenu.class);
        }
        return ResponseBo.ok();
    }

    /**
     * 获取全部菜单信息
     *
     * @param
     * @return
     */
    @PostMapping("/getAllMenu")
    public ResponseBo getAllMenu() {

        List<SystemMenu> result = menuService.selectAll();
        if (result != null) {
            return ResponseBo.ok(result);
        }
        return ResponseBo.ok("获取失败!");
    }
}
