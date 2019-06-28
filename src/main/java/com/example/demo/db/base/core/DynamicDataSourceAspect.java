package com.example.demo.db.base.core;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * AOP，拦截并切换数据源
 *
 * @Author zhoushenghua on 2019-06-25
 */
@Aspect
@Component
@Order(1)
public class DynamicDataSourceAspect {
    private static final Logger log = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Pointcut()
    public void pointCut(){
    }

    @Pointcut()
    public void dataSource1_pointCut(){
    }

    @Pointcut()
    public void dataSource2_pointCut(){
    }






}
