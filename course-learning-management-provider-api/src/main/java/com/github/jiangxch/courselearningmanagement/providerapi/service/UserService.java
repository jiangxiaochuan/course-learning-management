package com.github.jiangxch.courselearningmanagement.providerapi.service;

import com.github.jiangxch.courselearningmanagement.common.result.Result;
import com.github.jiangxch.courselearningmanagement.providerapi.arg.AdminRegisterOrUpdateArg;
import com.github.jiangxch.courselearningmanagement.providerapi.arg.WebLoginArg;
import com.github.jiangxch.courselearningmanagement.providerapi.arg.WxLoginArg;
import com.github.jiangxch.courselearningmanagement.providerapi.result.UserInfoResult;

import java.util.List;

/**
 * @author: sanjin
 * @date: 2020/2/27 下午12:40
 */
public interface UserService {
    Result<String> wxLogin(WxLoginArg arg);

    Result<String> generateToken(UserInfoResult data);

    Result<String> adminLogin(WebLoginArg arg);

    Result<Void> adminRegisterOrUpdateArg(AdminRegisterOrUpdateArg arg, String userId);

    Result<UserInfoResult> getUserResultById(String userId);

    Result<List<UserInfoResult>> listUsers();
}
