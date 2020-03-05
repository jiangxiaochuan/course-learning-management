package com.github.jiangxch.courselearningmanagement.biz.controller;

import com.github.jiangxch.courselearningmanagement.common.utils.JwtUtil;
import com.github.jiangxch.courselearningmanagement.common.result.Result;
import com.github.jiangxch.courselearningmanagement.providerapi.arg.WebLoginArg;
import com.github.jiangxch.courselearningmanagement.providerapi.arg.AdminRegisterOrUpdateArg;
import com.github.jiangxch.courselearningmanagement.providerapi.arg.WxLoginArg;
import com.github.jiangxch.courselearningmanagement.providerapi.result.UserInfoResult;
import com.github.jiangxch.courselearningmanagement.providerapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author: sanjin
 * @date: 2020/2/26 下午9:31
 */
@RestController
@RequestMapping("/authenticate")
@Api(tags = "用户相关")
@Slf4j
public class AuthenticateController extends BaseController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "小程序登陆注册接口")
    @PostMapping("/wxLogin")
    public Result<String> wxLogin(@RequestBody @Valid WxLoginArg arg) {
        return userService.wxLogin(arg);
    }

    @ApiOperation(value = "web登录接口")
    @PostMapping("/adminLogin")
    public Result<String> adminLogin(@RequestBody @Valid WebLoginArg arg) {
        return userService.adminLogin(arg);
    }

    @ApiOperation(value = "web注册(或修改)接口")
    @PostMapping("/adminRegisterOrUpdate")
    public Result<Void> adminRegisterOrUpdateArg(@RequestBody @Valid AdminRegisterOrUpdateArg arg) {
        return userService.adminRegisterOrUpdateArg(arg, getUserId());
    }

    @ApiOperation(value = "刷新token")
    @PostMapping("/refreshToken")
    public Result<String> refreshToken(HttpServletResponse response) {
        Result<UserInfoResult> userInfoResult = userService.getUserResultById(getUserId());
        return userService.generateToken(userInfoResult.getData());
    }

    @ApiOperation(value = "获取用户信息")
    @PostMapping("/getUserInfo")
    public Result<UserInfoResult> getUserInfo() {
        return userService.getUserResultById(getUserId());
    }
}
