package com.github.jiangxch.courselearningmanagement.biz.controller;

import com.github.jiangxch.courselearningmanagement.common.result.Result;
import com.github.jiangxch.courselearningmanagement.providerapi.arg.WebLoginArg;
import com.github.jiangxch.courselearningmanagement.providerapi.arg.WxLoginArg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author: sanjin
 * @date: 2020/2/26 下午9:31
 */
@RestController
@RequestMapping("/authenticate")
@Api(tags = "认证相关")
public class AuthenticateController extends BaseController{

    @Value("${wechat.appId}")
    private String appId;
    @Value("${wechat.secret}")
    private String secret;

    @ApiOperation(value = "小程序登陆注册接口")
    @PostMapping("/wxLogin")
    public Result wxLogin(WxLoginArg arg,
                                    HttpServletResponse response) {
        return Result.newSuccess();
    }

    @ApiOperation(value = "小程序登陆注册接口")
    @PostMapping("/adminLogin")
    public Result adminLogin(WebLoginArg arg,
                        HttpServletResponse response) {
        return Result.newSuccess();
    }

    @ApiOperation(value = "刷新token")
    @PostMapping("/adminLogin")
    public Result refreshToken() {
        return Result.newSuccess();
    }
}
