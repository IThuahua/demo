package com.example.demo.db.base.core;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    DataSourceEnum description() default DataSourceEnum.DATASOURCE_SOURCE1;
}
