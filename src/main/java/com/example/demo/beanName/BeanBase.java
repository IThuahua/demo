package com.example.demo.beanName;

import com.example.demo.rabbitMQ.UserVo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
//@Order(2)
public class BeanBase {

    @Bean("userBean")
    public UserVo user(){
        return new UserVo("BeanNameBase", 18, "china");
    }

}
