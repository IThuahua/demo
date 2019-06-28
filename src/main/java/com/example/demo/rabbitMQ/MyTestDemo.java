package com.example.demo.rabbitMQ;

import java.lang.reflect.Method;

/**
 * @Author zhoushenghua on
 */
public class MyTestDemo {

    @MyTest
    private void add(){
        System.out.println("add");
    }

    void subtract(){
        System.out.println("subtract");
    }

    @MyTest
    protected void multiply(){
        System.out.println("multiply");
    }

    public void divide(){
        System.out.println("divide");
    }

    public static void main(String[] args) throws Exception {
        Class clazz = MyTestDemo.class;

        Object o = clazz.newInstance();

        Method[] list = clazz.getMethods();

        for (Method method : list) {
//            method.isAnnotationPresent(MyTest.class);
            if(method.isAnnotationPresent(MyTest.class)){
                //method.invoke(o);
                System.out.println(method.invoke(o));
            }

        }
        


    }




}
