package com.example.demo.aop;

import com.example.demo.aop.annotation.AopTest;
import org.springframework.stereotype.Component;

/**
 * CGLIB动态代理测试类
 */
@Component
public class TestImpl {

    @AopTest
    public static String sayHi(){
        return "cglib";
    }


}
