package com.grandeflorum.system.controller;

import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.common.util.StrUtil;
import com.grandeflorum.system.domain.SystemUser;
import com.grandeflorum.system.service.SystemUserService;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by 13260 on 2019/11/1.
 */
@RestController
@RequestMapping("SystemUser")
public class SystemUserController {

    @Autowired
    private SystemUserService userService;


    @PostMapping("/addUser")
    public ResponseBo addUser(@RequestBody SystemUser user) {

        int result = this.userService.addUser(user);
        if (result == 1) {
            return ResponseBo.ok("新增用户成功！");
        } else {
            return ResponseBo.error("新增用户失败，请联系网站管理员！");
        }
    }

    /**
     * 修改用户信息
     *
     * @param
     * @return
     */
    @PostMapping("/modifyUser")
    public ResponseBo modify(@RequestBody SystemUser user) {
        int result = userService.modifyUser(user);
        if (result == 1) {
            return ResponseBo.ok("修改成功");
        } else {
            return ResponseBo.error("修改失败");
        }
    }


    @PostMapping("/deleteUsersByIds")
    public ResponseBo deleteUsers(@RequestBody List<String> ids) {
        if ((ids != null) && (ids.size() > 0)) {
            for (String id : ids) {
                userService.deleteUserById(id);
            }
            return ResponseBo.ok("删除成功!");
        }
        return ResponseBo.error("删除失败，请重试!");
    }

    /**
     * 获取用户(包含他的角色)
     *
     * @param userId
     * @return
     */
    @GetMapping("/getUserWithRoleByUserId")
    public ResponseBo getUserWithRoleByUserId(String id) {
        try {
            SystemUser user = this.userService.getUserWithRoleByUserId(id);
            return ResponseBo.ok(user);
        } catch (Exception e) {
            return ResponseBo.error("获取用户失败，请联系网站管理员！");
        }
    }

    /**
     * 获取用户(不包含他的角色)
     *
     * @param userId
     * @return
     */
    @RequestMapping("/getUserInfo")
    @ResponseBody
    public ResponseBo getUserInfo(Long userId) {
        try {
            SystemUser user = this.userService.selectByKey(userId);
            return ResponseBo.ok(user);
        } catch (Exception e) {
            return ResponseBo.error("获取用户信息失败！");
        }
    }

    /**
     * 获取用户所有权限
     *
     * @param userId
     * @return
     */
    @PostMapping("/getPermissionsByUserId")
    public ResponseBo getPermissionsByUserId(String userId) {
        try {
            List<String> result = userService.getAllPermissionByUserId(userId);
            return ResponseBo.ok(result);
        } catch (Exception e) {
            return ResponseBo.error("获取用户失败，请联系网站管理员！");
        }
    }

    /**
     * 获取用户列表
     *
     * @param page
     * @return
     */
    @PostMapping("/getUserList")
    public ResponseBo getUserList(@RequestBody Page page) {

        return userService.getUserList(page);
    }

    /**
     * 获取用户列表
     *
     * @param page
     * @return
     */
    @PostMapping("/changePassword")
    public ResponseBo changePassword(@RequestBody SystemUser user) {

        try {
            int result = userService.changePassword(user);
            if (result == 1) {
                return ResponseBo.ok("密码修改成功。");
            } else {
                return ResponseBo.error("密码修改失败！");
            }

        } catch (Exception e) {
            return ResponseBo.error("密码修改失败！");
        }

    }

    @PostMapping("/getAllUser")
    public ResponseBo getAllUser(){
        return ResponseBo.ok(userService.selectAll());
    }
}
