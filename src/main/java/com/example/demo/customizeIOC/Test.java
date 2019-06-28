package com.example.demo.customizeIOC;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author zhoushenghua on
 */
public class Test {

    public static void main(String[] args) {
        doBegin();
    }

    public static void doBegin(){
        ApplicationContext context = new ClassPathXmlApplicationContext("Bean/customizeBean");



    }
}
