package com.rabbitmq.product.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 路由模式|Routing模式   交换机类型：direct
 *
 *  1.声明队列 2.声明交换机 3、声明binding，绑定队列和交换机,路由模式不同队列声明不同的binding
 */
@Configuration
public class DirectConfig {

    @Bean
    public Queue directQ1() {
        return new Queue("direct_q1");
    }

    @Bean
    public Queue directQ2() {
        return new Queue("direct_q2");
    }

    //声明exchange
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("directExchange");
    }

    //声明binding，需要声明一个routingKey
    @Bean
    public Binding bindDirectBind1() {
        return BindingBuilder.bind(directQ1()).to(directExchange()).with("china.beijing");
    }

    @Bean
    public Binding bindDirectBind2() {
        return BindingBuilder.bind(directQ2()).to(directExchange()).with("china.nanjing");
    }

}
