package com.example.demo.ioc;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author zhoushenghua on
 */
public class TestABC {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean-circle.xml");
        A a = ((BeanFactory)context).getBean("a", A.class);
        a.hello();
    }


}
