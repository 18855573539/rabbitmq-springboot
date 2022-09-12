package com.rabbitmq.consumer.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TTLConsumerReceiver {
    //pub/sub模式进行消息监听
//    @RabbitListener(queues = "ttl_queue")
//    public void fanoutReceiveq1(String message) {
//
//        System.out.println("发布订阅模式消费者1 received message : " + message);
//    }
}
