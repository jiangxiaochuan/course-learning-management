package com.github.jiangxch.courselearningmanagement.common.args;

import com.sun.istack.internal.NotNull;
import lombok.Data;

/**
 * @author: sanjin
 * @date: 2020/1/4 上午10:06
 */
@Data
public class PageArg {
    /**
     * 当前页码
     */
    private Integer pageNo;

    /**
     * 每页数量
     */
    private Integer pageSize;

    /** 排序字段 */
    private String field;

    /** 排序顺序(DESC降,ASC) */
    private Boolean isASC;

    public void validate() {
        if (pageNo == null || pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 5;
        }
    }
}
