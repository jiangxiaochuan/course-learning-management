package com.github.jiangxch.courselearningmanagement.oos.result;

import lombok.Data;

/**
 * @author: sanjin
 * @date: 2020/2/26 下午7:21
 */
@Data
public class FileResult {
    private String fileName;
    // 对象文件的地址
    private String url;
    // 扩展名
    private String ext;
}
