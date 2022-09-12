package com.rabbitmq.consumer.receiver;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WorkConsumerReceiver {


    /**
     * 工作队列模式，模拟多个消费者监听队列
     *
     * @param message
     */
    @RabbitListener(queues = "work_queue")
    private void workerReceiver1(Message message){
        System.out.println("工作队列模式消费者1 received message : " + message);
    }

    @RabbitListener(queues = "work_queue")
    private void workerReceiver2(Message message){
        System.out.println("工作队列模式消费者2 received message : " + message);
    }

}
