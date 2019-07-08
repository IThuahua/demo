package com.example.demo.redis;

/**
 * @Author zhoushenghua on
 */
public class RedisDemo {

    public static void main(String[] args) {
        RedisUtil redisUtil = new RedisUtil();
        System.out.println(redisUtil.get("zsh"));

    }
}
