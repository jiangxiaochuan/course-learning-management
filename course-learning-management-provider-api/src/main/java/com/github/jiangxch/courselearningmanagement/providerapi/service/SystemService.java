package com.github.jiangxch.courselearningmanagement.providerapi.service;

import com.github.jiangxch.courselearningmanagement.common.result.Result;

import java.util.List;

/**
 * @author: sanjin
 * @date: 2020/2/28 下午1:45
 */
public interface SystemService {
    Result<List<String>> listChinaUniversityNames();
}
