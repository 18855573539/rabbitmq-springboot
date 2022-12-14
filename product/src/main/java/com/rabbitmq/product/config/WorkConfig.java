package com.rabbitmq.product.config;

import com.rabbitmq.product.contants.Constants;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 工作队列模式
 * 直连模式只需要声明队列，所有消息都通过队列转发。
 * 无需设置交换机
 */
@Configuration
public class WorkConfig {
    // 声明队列
    @Bean(Constants.WORK_QUEUE)
    public Queue wordQueue(){
        return new Queue(Constants.WORK_QUEUE);
    }

}
