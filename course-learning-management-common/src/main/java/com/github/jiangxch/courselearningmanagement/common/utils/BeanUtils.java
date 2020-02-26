package com.github.jiangxch.courselearningmanagement.common.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BeanUtils {
    /**
     * 实体类转Map
     *
     * @param object
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> entityToMap(Object object) {
        Map<String, Object> map = new HashMap();
        for (Field field : object.getClass().getDeclaredFields()) {
            try {
                boolean flag = field.isAccessible();
                field.setAccessible(true);
                Object o = field.get(object);
                map.put(field.getName(), o);
                field.setAccessible(flag);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * Map转实体类
     * long 类型转换需要特殊处理
     *
     * @param map    需要初始化的数据，key字段必须与实体类的成员名字一样，否则赋值为空
     * @param entity 需要转化成的实体类
     * @return
     */
    public static <T> T mapToEntity(Map<String, Object> map, Class<T> entity) {
        if (map == null) {
            map = new HashMap<>();
        }
        assert entity != null;
        T t = null;
        try {
            t = entity.newInstance();
            for (Field field : entity.getDeclaredFields()) {
                if (map.containsKey(field.getName())) {
                    boolean flag = field.isAccessible();
                    field.setAccessible(true);
                    Object object = map.get(field.getName());
                    if (object != null) {
                        if (field.getType() == Long.class) {//Long类型需手动转换，否则报类型转换出错
                            field.set(t, ((Integer) object).longValue());
                        } else {
                            field.set(t, object);
                        }
                        field.setAccessible(flag);
                    }
                }
            }
            return t;
        } catch (InstantiationException | IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return t;
    }

    public static void main(String[] args) {
//        JwtToken jwtToken = new JwtToken()
//                .setId(1L)
//                .setIsAdmin(true)
//                .setUsername("sanjin");
//        Map<String, Object> map = entityToMap(jwtToken);
//        System.out.println(jwtToken);
//        for (Map.Entry entry : map.entrySet()) {
//            System.out.println("key: " + entry.getKey() + ",value: " + entry.getValue());
//        }
//
//        JwtToken newJwtToken = mapToEntity(map, JwtToken.class);
//        System.out.println(newJwtToken);
    }
}
