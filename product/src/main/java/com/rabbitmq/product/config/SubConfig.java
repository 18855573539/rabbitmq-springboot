package com.rabbitmq.product.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Fanout模式需要声明exchange，并绑定queue，由exchange负责转发到queue上。
 * 广播模式 交换机类型设置为：fanout
 * 1.声明队列 2.声明交换机 3、声明binding，绑定队列和交换机
 */
@Configuration
public class SubConfig {

    //声明队列
    @Bean
    public Queue subQ1() {
        return new Queue("subout.q1");
    }

    @Bean
    public Queue subQ2() {
        return new Queue("subout.q2");
    }


    //声明交换机
    @Bean
    public FanoutExchange subWorkExchange(){
        return new FanoutExchange("subWorkExchange");
    }

    //声明Binding,exchange与queue的绑定关系(binding 中可以包含 routing key)
    @Bean
    public Binding bindingQ1(){
        return BindingBuilder.bind(subQ1()).to(subWorkExchange());
    }

    @Bean
    public Binding bindingQ2(){
        return BindingBuilder.bind(subQ2()).to(subWorkExchange());
    }


}
