package com.github.jiangxch.courselearningmanagement.provider.dao;

import com.github.jiangxch.courselearningmanagement.common.args.PageArg;
import com.github.jiangxch.courselearningmanagement.common.args.SearchArg;
import com.github.jiangxch.courselearningmanagement.common.result.PageResult;
import com.github.jiangxch.courselearningmanagement.provider.dao.common.BaseDao;
import com.github.jiangxch.courselearningmanagement.provider.entity.CourseEntity;
import com.github.jiangxch.courselearningmanagement.provider.entity.CourseResourceEntity;
import org.apache.commons.lang3.StringUtils;
import org.mongodb.morphia.query.Query;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author: sanjin
 * @date: 2020/2/26 下午2:53
 */
@Component
public class CourseResourceEntityDao extends BaseDao<CourseResourceEntity> {

    public PageResult<CourseResourceEntity> listCourseResourcesByPage(SearchArg searchArg) {
        searchArg.validate();

        Integer pageNo = searchArg.getPageNo();
        Integer pageSize = searchArg.getPageSize();
        long count = countAll();

        PageResult<CourseResourceEntity> pageResult = new PageResult<>();
        pageResult.setPageNo(pageNo);
        pageResult.setPageSize(pageSize);
        pageResult.setCount(count);
        pageResult.setTotalPage(PageResult.getTotalPage(pageSize,count));

        Query<CourseResourceEntity> query = createQuery();
        if (!StringUtils.isBlank(searchArg.getKeyword())) {
            String keyword = searchArg.getKeyword();
            query.or(
                    query.criteria("resourceName").containsIgnoreCase(keyword),
                    query.criteria("courseName").containsIgnoreCase(keyword),
                    query.criteria("schoolName").containsIgnoreCase(keyword)
            );
        }

        if (searchArg.getHasASC()) {
            query.order(searchArg.getField());
        } else {
            query.order("-" + searchArg.getField());
        }
        pageResult.setRows(query.asList());

        return pageResult;
    }
}
