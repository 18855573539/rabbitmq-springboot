package com.rabbitmq.consumer.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class SimpleConsumerReceiver {
    /**
     * 简单工作模式
     */
    @RabbitListener(queues = "simpleQueue")
    public void helloWorldReceive(String message) {

        System.out.println("简单模式 received message : " + message);
    }

}
