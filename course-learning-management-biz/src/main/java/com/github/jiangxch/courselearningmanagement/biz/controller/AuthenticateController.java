package com.github.jiangxch.courselearningmanagement.biz.controller;

import com.github.jiangxch.courselearningmanagement.common.utils.JwtUtil;
import com.github.jiangxch.courselearningmanagement.common.result.Result;
import com.github.jiangxch.courselearningmanagement.providerapi.arg.AdminRegisterArg;
import com.github.jiangxch.courselearningmanagement.providerapi.arg.WebLoginArg;
import com.github.jiangxch.courselearningmanagement.providerapi.arg.WxLoginArg;
import com.github.jiangxch.courselearningmanagement.providerapi.result.UserInfoResult;
import com.github.jiangxch.courselearningmanagement.providerapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
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
@Api(tags = "认证相关")
public class AuthenticateController extends BaseController{

    @Autowired
    private UserService userService;

    @ApiOperation(value = "小程序登陆注册接口")
    @PostMapping("/wxLogin")
    public Result wxLogin(@Valid WxLoginArg arg,
                          HttpServletResponse response) {
        Result<UserInfoResult> userInfoResult = userService.wxLogin(arg);
        Result<String> tokenResult = userService.generateToken(userInfoResult.getData());
        String tokenData = tokenResult.getData();
        response.setHeader(JwtUtil.HEADER, tokenData);
        return userInfoResult;
    }

    @ApiOperation(value = "web登录接口")
    @PostMapping("/adminLogin")
    public Result adminLogin(WebLoginArg arg,
                        HttpServletResponse response) {
        return Result.newSuccess();
    }

    @ApiOperation(value = "web注册接口")
    @PostMapping("/adminRegister")
    public Result adminRegister(AdminRegisterArg arg,
                                HttpServletResponse response) {
        return Result.newSuccess();
    }

    @ApiOperation(value = "web修改接口")
    @PostMapping("/adminUpdate")
    public Result adminUpdate(WebLoginArg arg,
                             HttpServletResponse response) {
        return Result.newSuccess();
    }

    @ApiOperation(value = "刷新token")
    @PostMapping("/refreshToken")
    public Result refreshToken() {
        return Result.newSuccess();
    }
}
