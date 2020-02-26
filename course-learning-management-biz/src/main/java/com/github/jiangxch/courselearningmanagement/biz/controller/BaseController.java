package com.github.jiangxch.courselearningmanagement.biz.controller;

import com.github.jiangxch.courselearningmanagement.biz.common.RequestContext;
import com.github.jiangxch.courselearningmanagement.biz.common.RequestContextHolder;
import com.github.jiangxch.courselearningmanagement.common.data.AuthInfo;
import com.github.jiangxch.courselearningmanagement.common.enums.ResultEnum;
import com.github.jiangxch.courselearningmanagement.common.exception.MyException;

/**
 * @author: sanjin
 * @date: 2020/2/26 下午7:59
 */
public class BaseController {
    protected AuthInfo getAuthInfo() {
        RequestContext context = RequestContextHolder.getContext();
        if (context != null) {
            AuthInfo authInfo = context.getTokenData();
            if (authInfo != null
                    || authInfo.getUserId() != null
                    || authInfo.getRoleType() != null) {
                return authInfo;
            }
        }
        throw new MyException(ResultEnum.AUTHENTICATION_ERROR);
    }

    protected String getUserId() {
        return getAuthInfo().getUserId();
    }

    protected Integer getRoleType() {
        return getAuthInfo().getRoleType();
    }
}
