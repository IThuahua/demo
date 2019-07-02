package com.example.demo.rabbitMQ;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author zhoushenghua on
 */
@Component
public class HelloSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

//    public void send(){
//        UserVo userVo = new UserVo();
//        userVo.setName("周胜华");
//        userVo.setAge(26);
//        userVo.setAddress("广水市");
//        amqpTemplate.convertAndSend("queue",userVo);
//    }

    public void send(){
        amqpTemplate.convertAndSend("exchange", "topic.msg", " i love you ,zsh");

    }

    public static void main(String[] args) {
        int i = 1;
        int a = i+++i++;
        System.out.println(a + ":" + i);
        int j = 1;
        int b = j++ + ++j;
        System.out.println(b + ":" + j);
        int k = 1;
        int c = ++k+k++;
        System.out.println(c + ":" + k);
        int l = 1;
        int d = ++l + ++l;
        System.out.println(d + ":" + l);

    }



}
