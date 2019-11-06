package com.grandeflorum.system.controller;

import com.grandeflorum.common.cache.EHCacheUtils;
import com.grandeflorum.common.domain.Page;
import com.grandeflorum.common.domain.ResponseBo;
import com.grandeflorum.common.util.GuidHelper;
import com.grandeflorum.common.util.StrUtil;
import com.grandeflorum.system.domain.SystemUser;
import com.grandeflorum.system.service.SystemUserService;
import net.sf.ehcache.CacheManager;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 13260 on 2019/11/1.
 */
@RestController
@RequestMapping("SystemUser")
public class SystemUserController {

    @Autowired
    private SystemUserService userService;

    @Autowired
    private CacheManager cacheManager;


    @PostMapping("/login")
    public ResponseBo login(@RequestBody Map<String,String> map, HttpServletRequest request) {

        Map<String, Object> result = new HashMap<>();

        SystemUser systemUser = userService.login(map);

        if (systemUser == null) {
            return ResponseBo.error("用户名或密码错误，请重试！");
        } else {
            List<String> permissions = userService.getAllPermissionByUserId(systemUser.getId());
            result.put("userinfo", systemUser);
            result.put("permission", permissions);

            String ticket = GuidHelper.getGuid();
            result.put("ticket", ticket);

            //去掉之前该用户的缓存(一个用户不能多次登录)
            EHCacheUtils.deleteCacheByUserId(cacheManager,systemUser.getId());
            EHCacheUtils.setCache(cacheManager,ticket,systemUser);

            return ResponseBo.ok(result);
        }
    }

    @RequestMapping("/loginout")
    public ResponseBo Loginout(){
        EHCacheUtils.deleteCache(cacheManager);
        return ResponseBo.ok();
    }

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
     * @param
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

    @GetMapping("/findUserByUsername")
    public ResponseBo findUserByUsername(String name) {
        return ResponseBo.ok(userService.findUserByUsername(name));
    }
}
