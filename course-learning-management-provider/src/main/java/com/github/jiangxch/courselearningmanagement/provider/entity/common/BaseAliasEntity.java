package com.github.jiangxch.courselearningmanagement.provider.entity.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.mongodb.morphia.annotations.PrePersist;
import org.mongodb.morphia.annotations.Property;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Setter
@Getter
@ToString
public class BaseAliasEntity implements Serializable {
    private static final long serialVersionUID = 3115826810775439134L;

    @Property("CT")
    protected Long createTime;

    @Property("UT")
    protected Long updateTime;

    @PrePersist
    public void timeSetBeforePersist() {
        if (Objects.isNull(updateTime)) {
            updateTime = createTime = System.currentTimeMillis();
        }
    }
}
