package com.github.jiangxch.courselearningmanagement.oos.service;

import com.github.jiangxch.courselearningmanagement.common.result.Result;
import com.github.jiangxch.courselearningmanagement.oos.result.FileResult;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: sanjin
 * @date: 2020/2/26 下午7:27
 */
public interface Oos {
    Result<FileResult> upload(MultipartFile file);

    default String getFileName(MultipartFile file) {
        return StringUtils.isEmpty(file.getOriginalFilename()) ?
                file.getName() : file.getOriginalFilename();
    }
}
