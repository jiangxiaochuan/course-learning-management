package com.github.jiangxch.courselearningmanagement.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author: sanjin
 * @date: 2020/1/23 下午4:45
 */
public class MapUtil {

    /**
     * 从Map[id,Tag] 获取指定ids的Tag对象
     * @param ids
     * @param map
     * @param <T>
     * @return
     */
    public static <T> List<T> batchGetFromMap(Set<Integer> ids, Map<Integer, T> map) {
        List<T> objs = new ArrayList<>();
        if (ids == null || ids.size() == 0) {
            return objs;
        }
        for (Integer id : ids) {
            T t = map.get(id);
            if (t != null) {
                objs.add(t);
            }
        }
        return objs;
    }
}
