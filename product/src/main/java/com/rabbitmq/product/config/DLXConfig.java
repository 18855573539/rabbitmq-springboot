package com.rabbitmq.product.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DLXConfig {



    //声明普通队列
    @Bean
    public Queue testDLXQueue(){
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-message-ttl",6000);
        arguments.put("x-dead-letter-exchange", "dlxExchange");
        arguments.put("x-dead-letter-routing-key","dlt.msg");
        return new Queue("ttl_dlx_queue",true, false, false, arguments);
    }

    // 声明普通交换机
    @Bean
    public FanoutExchange testDLXExchange(){
        return ExchangeBuilder.fanoutExchange("testDLXExchange").build();
    }

    @Bean
    public Binding testDLXBingingQueue(){
        return BindingBuilder.bind(testDLXQueue()).to(testDLXExchange());
    }

    // 声明死信队列
    @Bean
    public Queue dlxQueue(){
        return new Queue("dlx_queue");
    }


    // 声明死信交换机
    @Bean
    public DirectExchange dlxExchange(){
        return ExchangeBuilder.directExchange("dlxExchange").build();
    }


    @Bean
    public Binding dlxBingingQueue(){
        return BindingBuilder.bind(dlxQueue()).to(dlxExchange()).with("dlt.msg");
    }
}
