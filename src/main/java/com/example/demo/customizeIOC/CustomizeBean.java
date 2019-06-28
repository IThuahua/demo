package com.example.demo.customizeIOC;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义Bean,用来存放Bean的所有属性
 *
 * @Author zhoushenghua on 2019-06-22
 */

@Getter
@Setter
@ToString
public class CustomizeBean {

    /** bean id */
    private String id;

    /** bean Class */
    private String name;

    /** bean property */
    private Map<String, Object> properties = new HashMap<>();

}
