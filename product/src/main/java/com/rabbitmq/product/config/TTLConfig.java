package com.rabbitmq.product.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class TTLConfig {
    @Bean
    public Queue lltQueue(){
        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-message-ttl",2000);
        return new Queue("ttl_queue",true, false, false, arguments);
    }

    @Bean
    public FanoutExchange ttlExchange(){
        return ExchangeBuilder.fanoutExchange("ttlExchange").build();
    }

    @Bean
    public Binding ttlBingingQueue(){
        return BindingBuilder.bind(lltQueue()).to(ttlExchange());
    }
}
