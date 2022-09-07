package com.rabbitmq.product.config;

import com.rabbitmq.product.contants.Constants;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WorkConfig {
    // 声明队列
    @Bean(Constants.WORK_QUEUE)
    public Queue wordQueue(){
        return new Queue(Constants.WORK_QUEUE);
    }

}
