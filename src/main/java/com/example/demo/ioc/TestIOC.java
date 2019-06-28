package com.example.demo.ioc;

import com.example.demo.rabbitMQ.UserVo;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * @Author zhoushenghua on
 */
public class TestIOC {

    public static void main(String[] args) throws Exception {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource resource = resolver.getResource("classpath:beans.xml");
        System.out.println("URL:" + resource.getURL());

        Resource resource1 = resolver.getResource("classpath:Bean/beans.xml");
        System.out.println("URL:" + resource1.getURL());

        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(resource);
        reader.loadBeanDefinitions(resource1);

        System.out.println("bean 工厂已初始化");

        UserVo userVo = factory.getBean("people", UserVo.class);
        System.out.println(userVo.toString());

        UserVo userVo1 = factory.getBean("man", UserVo.class);
        System.out.println(userVo1.toString());

        System.out.println(".....................................................");

        ApplicationContext context1 = new ClassPathXmlApplicationContext("beans.xml");
        BeanFactory factory1 = (BeanFactory)context1;
        UserVo userVo2 = factory1.getBean("people", UserVo.class);
        System.out.println(userVo2.toString());
        ApplicationContext context2 = new FileSystemXmlApplicationContext("/D:/workSpace/demo/target/classes/Bean/beans.xml");
        BeanFactory factory2 = (BeanFactory)context2;
        UserVo userVo3 = factory2.getBean("man", UserVo.class);
        System.out.println(userVo3.toString());

        System.out.println(".....................................................");

        ApplicationContext context3 = new ClassPathXmlApplicationContext(new String[]{"beans.xml","Bean/beans.xml"});
        BeanFactory factory3= (BeanFactory)context3;
        UserVo userVo4 = factory3.getBean("people", UserVo.class);
        System.out.println(userVo4.toString());
        UserVo userVo5 = factory3.getBean("man", UserVo.class);
        System.out.println(userVo5.toString());

        System.out.println(".....................................................");


        Beans b = new Beans();
        UserVo userVo6 = b.bu();
        System.out.println(userVo6.toString());

        UserVo userVo7 = factory.getBean("zsh", UserVo.class);
        System.out.println(userVo7.toString());

    }



}
