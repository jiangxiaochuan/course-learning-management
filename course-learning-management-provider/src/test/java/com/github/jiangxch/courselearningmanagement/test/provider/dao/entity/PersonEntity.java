package com.github.jiangxch.courselearningmanagement.test.provider.dao.entity;

import com.github.jiangxch.courselearningmanagement.provider.entity.common.BaseAliasEntity;
import lombok.Data;
import lombok.ToString;
import org.mongodb.morphia.annotations.*;

/**
 * @author: sanjin
 * @date: 2020/2/25 下午6:22
 */

@Entity(value = "Person", noClassnameStored = true)
@Indexes({
        @Index(fields = {@Field("name")}, options = @IndexOptions(name = "name")),
        @Index(fields = {@Field("salary")}, options = @IndexOptions(name = "salary"))
})
@Data
@ToString(callSuper = true)
public class PersonEntity extends BaseAliasEntity {
    @Id
    private String id;

    private String name;

    private String genre;

    private Integer age;

    @Property("wage")
    private Double salary;
}
