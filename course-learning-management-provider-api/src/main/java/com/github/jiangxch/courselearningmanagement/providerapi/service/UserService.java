package com.github.jiangxch.courselearningmanagement.providerapi.service;

import com.github.jiangxch.courselearningmanagement.common.result.Result;
import com.github.jiangxch.courselearningmanagement.providerapi.arg.WxLoginArg;
import com.github.jiangxch.courselearningmanagement.providerapi.result.UserInfoResult;

/**
 * @author: sanjin
 * @date: 2020/2/27 下午12:40
 */
public interface UserService {
    Result<UserInfoResult> wxLogin(WxLoginArg arg);

    Result<String> generateToken(UserInfoResult data);
}
