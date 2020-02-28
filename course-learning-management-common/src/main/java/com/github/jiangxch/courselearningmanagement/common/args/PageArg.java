package com.github.jiangxch.courselearningmanagement.common.args;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author: sanjin
 * @date: 2020/1/4 上午10:06
 */
@Data
public class PageArg {
    /**
     * 当前页码
     */
    @NotNull(message = "pageNo不能为空")
    @Min(value = 1, message = "最小pageNo为 1")
    private Integer pageNo;

    /**
     * 每页数量
     */
    @NotNull(message = "pageSize不能为空")
    @Range(min = 1,max = 100)
    private Integer pageSize;

    /** 排序字段 */
    private String field;

    /** 排序顺序(DESC降,ASC) */
    private Boolean hasASC;

    public void validate() {
        if (pageNo == null || pageNo <= 0) {
            pageNo = 1;
        }
        if (pageSize == null || pageSize <= 0) {
            pageSize = 5;
        }
        if (hasASC == null) {
            hasASC = true;
        }
    }
}
