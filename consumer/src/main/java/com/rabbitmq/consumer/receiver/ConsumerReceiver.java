package com.rabbitmq.consumer.receiver;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class ConsumerReceiver {

    @RabbitListener(queues = "work_queue")
    private void workerReceiver(Message message){
        System.out.println("工作队列模式1 received message : " + message);
    }

}
