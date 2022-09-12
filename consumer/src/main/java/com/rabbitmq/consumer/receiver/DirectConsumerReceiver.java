package com.rabbitmq.consumer.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class DirectConsumerReceiver {

    //Routing路由模式
    @RabbitListener(queues = "direct_q1")
    public void routingReceiveq1(String message) {

        System.out.println("Routing路由模式消费者1 received message : " + message);
    }

    @RabbitListener(queues = "direct_q2")
    public void routingReceiveq2(String message) {

        System.out.println("Routing路由模式消费者2 received message : " + message);
    }

}
