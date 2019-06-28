package com.example.demo.ioc;

/**
 * @Author zhoushenghua on
 */
public class ObjectFactory<T> {

    private String className;
    private T t;

    public ObjectFactory(String className, T t) {
        this.className = className;
        this.t = t;
    }

    public T getObject(){
        //如果该Bean使用了代理,则此处返回代理后的Bean
        return t;
    }
}
