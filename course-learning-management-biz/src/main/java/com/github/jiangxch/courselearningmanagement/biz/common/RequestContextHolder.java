package com.github.jiangxch.courselearningmanagement.biz.common;

/**
 * 用ThreadLocal存储RequestContext {@link RequestContext}
 */
public class RequestContextHolder {

    private static final ThreadLocal<RequestContext> THREAD_LOCAL = new ThreadLocal<>();

    public static void setContext(RequestContext context) {
        THREAD_LOCAL.set(context);
    }

    /**
     * 获取用户信息上下文
     * @return
     */
    public static RequestContext getContext() {
        return THREAD_LOCAL.get();
    }

    public static void clearContext() {
        THREAD_LOCAL.remove();
    }
}