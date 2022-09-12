package com.rabbitmq.product.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Topics模式  交换机类型 topic
 * 1.声明队列 2.声明交换机 3、声明binding，绑定队列和交换机,路由模式不同队列声明不同的binding
 */
@Configuration
public class TopicConfig {
    //声明队列
    @Bean
    public Queue topicQ1() {
        return new Queue("topic_q1");
    }

    @Bean
    public Queue topicQ2() {
        return new Queue("topic_q2");
    }


    //声明exchange
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    //声明binding，需要声明一个roytingKey
    @Bean
    public Binding bindTopicHebei1() {
        return BindingBuilder.bind(topicQ1()).to(topicExchange()).with("us.*");
    }

    @Bean
    public Binding bindTopicHebei2() {
        return BindingBuilder.bind(topicQ2()).to(topicExchange()).with("#.beijing");
    }

}

