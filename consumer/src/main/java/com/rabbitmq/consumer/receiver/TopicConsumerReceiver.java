package com.rabbitmq.consumer.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class TopicConsumerReceiver {
    //topic 模式
    //注意这个模式会有优先匹配原则。例如发送routingKey=hunan.IT,那匹配到hunan.*(hunan.IT,hunan.eco),之后就不会再去匹配*.ITd
    @RabbitListener(queues = "topic_q1")
    public void topicReceiverQ1(String message) {
        System.out.println("Topic模式 topic_sb_mq_q1 received message : " + message);
    }

    @RabbitListener(queues = "topic_q2")
    public void topicReceiverQ2(String message) {
        System.out.println("Topic模式 topic_sb_mq_q2 received  message : " + message);
    }
}
