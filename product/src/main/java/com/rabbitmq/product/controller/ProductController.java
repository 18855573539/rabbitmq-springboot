package com.rabbitmq.product.controller;

import com.rabbitmq.product.contants.Constants;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
public class ProductController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //工作队列模式
    @ApiOperation(value = "workqueue发送接口", notes = "发送到所有监听该队列的消费")
    @GetMapping(value = "/workqueueSend")
    public Object workqueueSend(String message) throws AmqpException, UnsupportedEncodingException {
        //设置部分请求参数
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        rabbitTemplate.send(Constants.WORK_QUEUE,new Message(message.getBytes(), messageProperties));
        return "send workqueue success";
    }
}
