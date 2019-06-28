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



}
