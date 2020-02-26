package com.github.jiangxch.courselearningmanagement.biz.common;


import com.github.jiangxch.courselearningmanagement.common.data.AuthInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 保存请求上下文数据
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class RequestContext {
    private AuthInfo tokenData;
}