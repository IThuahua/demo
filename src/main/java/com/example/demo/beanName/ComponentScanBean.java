package com.example.demo.beanName;

import com.example.demo.rabbitMQ.UserVo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(value = "com.example.demo.beanName")
@Configuration
public class ComponentScanBean {

//    @Bean("userBean")
//    public UserVo user(){
//        return new UserVo("Bean", 18, "US");
//    }

    public static void main(String[] args) throws Exception{
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentScanBean.class);
        Object userBean = context.getBean("userBean");
        System.out.println(userBean); //UserVo{name='one', age=18, address='china'}
    }

}
