package com.github.jiangxch.courselearningmanagement.common.args;

import lombok.Data;

/**
 * @author: sanjin
 * @date: 2020/2/28 上午1:00
 */
@Data
public class SearchArg extends PageArg {
    private String keyword;
}
