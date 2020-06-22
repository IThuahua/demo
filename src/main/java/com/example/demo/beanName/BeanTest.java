package com.example.demo.beanName;

import com.example.demo.rabbitMQ.UserVo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//@Order(1)
public class BeanTest {

//    @Bean("userBean")
//    public UserVo user(){
//        return new UserVo("BeanNameTest", 18, "US");
//    }

//    @Bean("userBean")
//    public UserVo user2(){
//        return new UserVo("two", 18, "US");
//    }

    public static void main(String[] args) throws Exception{
        ApplicationContext context = new AnnotationConfigApplicationContext(BeanBase.class, BeanTest.class);
        Object userBean = context.getBean("userBean");
        System.out.println(userBean); //UserVo{name='one', age=18, address='china'}
    }


}
