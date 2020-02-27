package com.github.jiangxch.courselearningmanagement.common.utils;

import java.util.UUID;

/**
 * @author: sanjin
 * @date: 2020/2/27 下午3:07
 */
public class IdUtil {
    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
