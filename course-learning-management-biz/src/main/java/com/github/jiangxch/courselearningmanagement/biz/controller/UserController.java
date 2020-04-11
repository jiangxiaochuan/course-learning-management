package com.github.jiangxch.courselearningmanagement.biz.controller;

import com.github.jiangxch.courselearningmanagement.common.result.Result;
import com.github.jiangxch.courselearningmanagement.providerapi.enums.UserRoleTypeEnum;
import com.github.jiangxch.courselearningmanagement.providerapi.result.UserInfoResult;
import com.github.jiangxch.courselearningmanagement.providerapi.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: sanjin
 * @date: 2020/3/9 下午10:05
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关")
public class UserController extends BaseController{
    @Autowired
    private UserService userService;

    @ApiOperation(value = "获取用户列表")
    @PostMapping("/listUsers")
    public Result<List<UserInfoResult>> listUsers() {
        checkPermission(UserRoleTypeEnum.SUPER_ADMIN);
        return userService.listUsers();
    }


}
