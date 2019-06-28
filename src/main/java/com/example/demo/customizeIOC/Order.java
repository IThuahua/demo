package com.example.demo.customizeIOC;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * @Author zhoushenghua on
 */
@Getter
@Setter
@ToString
public class Order {

    private String name;

    private Map<String, Object> map;

}
