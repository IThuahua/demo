package com.example.demo;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * @Author zhoushenghua on
 */
@Configuration
public class SenderConf {

    @Bean(name="msg")
    public Queue queueMsg(){
        return new Queue("topic.msg");
    }

    @Bean(name="message")
    public Queue queueMessage(){
        return new Queue("topic.message");
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange("exchange");
    }

    @Bean
    Binding bindingExchangeMsg(@Qualifier("msg") Queue queueMsg, TopicExchange exchange){
        return BindingBuilder.bind(queueMsg).to(exchange).with("topic.msg");
    }

    @Bean
    Binding bindingExchangeMessage(@Qualifier("message") Queue queueMessage, TopicExchange exchange){
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.#");
    }





}
