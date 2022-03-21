package com.xxx.server.controller;

import com.xxx.server.pojo.Admin;
import com.xxx.server.pojo.AdminLoginParam;
import com.xxx.server.pojo.RespBean;
import com.xxx.server.service.IAdminRoleService;
import com.xxx.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * TODO
 *
 * @author Gpower
 * @date 2022/3/20 15:08
 */
@RestController
@Api(tags = "LoginController")
public class LoginController {
    @Autowired
    private IAdminService adminService;


    @ApiOperation(value = "登录之后返回token")
    @PostMapping("/login")
    public RespBean login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request){
        return adminService.login(adminLoginParam.getUsername()
                ,adminLoginParam.getPassword()
                ,request);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal){
        if (null == principal){
            return null;
        }
        String username = principal.getName();
        Admin admin = adminService.getAdminByUserName(username);
        admin.setPassword(null);
        return admin;
    }



    @ApiOperation(value = "退出登录")
    @PostMapping("/logout")
    public RespBean logout(){
        /*
        前端将token删除请求头，后端可以不写，最好写一下，待更新
         */
        return RespBean.success("注销成功！");
    }


}