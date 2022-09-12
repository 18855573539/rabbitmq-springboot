package com.rabbitmq.product.config;

import com.rabbitmq.product.contants.Constants;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class ConfirmConfig {
    @Configuration
    public class RabbitMQConfig {

        //交换机名称
        public static final String CONFIRM_EXCHANGE = "confirm_exchange";
        //队列名称
        public static final String CONFIRM_QUEUE = "confirm_queue";

        //声明交换机
        @Bean("confirmExchange")
        public FanoutExchange confirmExchange(){
            return ExchangeBuilder.fanoutExchange(CONFIRM_EXCHANGE).durable(true).build();
        }

        //声明队列
        @Bean
        @Qualifier("confirmQueue")
        public Queue confirmQueue(){
            return QueueBuilder.durable(CONFIRM_QUEUE).build();
        }

        //绑定队列和交换机
        @Bean
        public Binding itemQueueExchange(@Qualifier("confirmQueue") Queue queue,
                                         @Qualifier("confirmExchange") FanoutExchange exchange){
            return BindingBuilder.bind(queue).to(exchange);
        }

    }


}
