package com.rabbitmq.consumer.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SubConsumerReceiver {
    //pub/sub模式进行消息监听
    @RabbitListener(queues = "subout.q1")
    public void fanoutReceiveq1(String message) {

        System.out.println("发布订阅模式消费者1 received message : " + message);
    }

    @RabbitListener(queues = "subout.q1")
    public void fanoutReceiveq2(String message) {

        System.out.println("发布订阅模式消费者2 received message : " + message);
    }
}
