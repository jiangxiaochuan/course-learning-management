package com.github.jiangxch.courselearningmanagement.common.utils;

import java.util.UUID;

/**
 * 生成工具类
 *
 * @author: sanjin
 * @date: 2020/1/6 下午10:26
 */
public class GenerateUtil {
    public static String generatorFileName() {
        return getUUIDWithNoLine();
    }

    /**
     *
     */
    private static String getUUIDWithNoLine() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "");
    }

    public static void main(String[] args) {
        System.out.println(getUUIDWithNoLine());
    }
}
