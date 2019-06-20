package com.example.demo.ioc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * //模拟setter方法注入,单例模式,解决循环依赖IOC问题
 * @Author zhoushenghua on
 */
public class SetterSingletonIOCAnalogy {

    //单例Bean的cache,使用ConcurrentHashMap是防止多线程产生多个Bean
    public static final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);
    //创建最初暴露出去的Factory,用工厂方式是因为有些Bean需要被代理,不能将代理前的bean暴露
    public static final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>(16);
    //执行工厂方法生产出来的Bean的cache
    public static final Map<String, Object> earlySingletonObjects = new HashMap<>(16);

    public static void main(String[] args) {
        A a = (A)getA();
        a.hello();
        B b = (B)getB();
        b.hello();
        C c = (C)getC();
        c.hello();
    }


    //模拟applicationContext.getBean("a"),省略xml配置文件
    public static Object getA(){
        String beanName = "A";
        Object singletonObject = getSingleton(beanName);
        if(singletonObject == null){
            A bean = new A();
            singletonFactories.put(beanName, new ObjectFactory<>(beanName, bean));
            bean.setB((B)getB());
            addSingleton(beanName, bean);
            return bean;
        }
        return singletonObject;
    }

    //模拟applicationContext.getBean("b")
    public static Object getB(){
        String beanName = "B";
        Object singletonObject = getSingleton(beanName);
        if(singletonObject == null){
            B bean = new B();
            singletonFactories.put(beanName, new ObjectFactory<>(beanName, bean));
            bean.setC((C)getC());
            addSingleton(beanName, bean);
            return bean;
        }
        return singletonObject;
    }

    //模拟applicationContext.getBean("c")
    public static Object getC(){
        String beanName = "C";
        Object singletonObject = getSingleton(beanName);
        if(singletonObject == null){
            C bean = new C();
            singletonFactories.put(beanName, new ObjectFactory<>(beanName, bean));
            bean.setA((A)getA());
            addSingleton(beanName, bean);
            return bean;
        }
        return singletonObject;
    }

    public static void addSingleton(String beanName, Object singleton){
        singletonObjects.put(beanName, singleton);
        earlySingletonObjects.remove(beanName);
        singletonFactories.remove(beanName);
    }

    public static Object getSingleton(String beanName){
        Object singletonObject = singletonObjects.get(beanName);
        if(singletonObject == null){
            synchronized (singletonObjects){
                singletonObject = earlySingletonObjects.get(beanName);
                if(singletonObject == null){
                    ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                    if(singletonFactory != null){
                        singletonObject = singletonFactory.getObject();
                        earlySingletonObjects.put(beanName, singletonObject);
                        singletonFactories.remove(beanName);
                    }
                }
            }
        }
        return singletonObject;
    }

}
