package com.github.jiangxch.courselearningmanagement.common.result;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author: sanjin
 * @date: 2020/1/4 上午10:07
 */
@Data
public class PageResult<T> {
    private int pageNo;       // 当前页数
    private int pageSize;          // 每页数据数量
    private int totalPage;         // 总页数
    private long count;       // 总记录数
    private List<T> rows;       // 每行显示内容

    public static int getTotalPage(int pageSize, long count) {
        return (int) Math.ceil((float)count / pageSize);
    }
}
