package com.rabbitmq.product.controller;

import com.rabbitmq.product.contants.Constants;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

@RestController
public class ProductController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //简单队列模式
    @ApiOperation(value = "workqueue发送接口", notes = "发送到所有监听该队列的消费")
    @GetMapping(value = "/workQueueSend")
    public Object workQueueSend(String message) throws AmqpException, UnsupportedEncodingException {
        //设置部分请求参数
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        rabbitTemplate.send(Constants.WORK_QUEUE,new Message(message.getBytes(), messageProperties));
        return "send workqueue success";
    }

    //工作队列模式
    @ApiOperation(value = "simple发送接口", notes = "发送到所有监听该队列的消费")
    @GetMapping(value = "/simpleQueueSend")
    public Object simpleQueueSend(String message) throws AmqpException, UnsupportedEncodingException {
        //设置部分请求参数
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        rabbitTemplate.send(Constants.WORK_QUEUE,new Message(message.getBytes(), messageProperties));
        return "send workqueue success";
    }

    // pub/sub 发布订阅模式   交换机类型 sub
    @ApiOperation(value = "sub发送接口", notes = "发送到subExchange。消息将往该exchange下的所有queue转发")
    @GetMapping(value = "/subSend")
    public Object fanoutSend(String message) throws AmqpException, UnsupportedEncodingException {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        //fanout模式只往exchange里发送消息。分发到exchange下的所有queue
        rabbitTemplate.send("subSend", "", new Message(message.getBytes("UTF-8"), messageProperties));
        return "message sended : " + message;
    }

    //routing路由工作模式  交换机类型 direct
    @ApiOperation(value = "direct发送接口", notes = "发送到directExchange。exchange转发消息时，会往routingKey匹配的queue发送")
    @GetMapping(value = "/directSend")
    public Object routingSend(String routingKey, String message) throws AmqpException, UnsupportedEncodingException {

        if (null == routingKey) {
            routingKey = "china.beijing";
        }
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        //fanout模式只往exchange里发送消息。分发到exchange下的所有queue
        rabbitTemplate.send("directExchange", routingKey, new Message(message.getBytes("UTF-8"), messageProperties));
        return "message sended : routingKey >" + routingKey + ";message > " + message;
    }


    //topic 工作模式   交换机类型 topic
    @ApiOperation(value = "topic发送接口", notes = "发送到topicExchange。exchange转发消息时，会往routingKey匹配的queue发送，*代表一个单词，#代表0个或多个单词。")
    @GetMapping(value = "/topicSend")
    public Object topicSend(String routingKey, String message) throws AmqpException, UnsupportedEncodingException {

        if (null == routingKey) {
            routingKey = "us.kf";
        }
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        //fanout模式只往exchange里发送消息。分发到exchange下的所有queue
        rabbitTemplate.send("topicExchange", routingKey, new Message(message.getBytes("UTF-8"), messageProperties));
        return "message sended : routingKey >" + routingKey + ";message > " + message;
    }

    // 高级模式

    //TTL
    @ApiOperation(value = "topic发送接口", notes = "ttl")
    @GetMapping(value = "/ttlSend")
    public Object ttlSend(String message) throws AmqpException, UnsupportedEncodingException {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        //fanout模式只往exchange里发送消息。分发到exchange下的所有queue
        rabbitTemplate.send("ttlExchange", "", new Message(message.getBytes("UTF-8"), messageProperties));
        return "message sended :" + message;
    }

    //DLX
    @ApiOperation(value = "topic发送接口", notes = "dlx")
    @GetMapping(value = "/dlxSend")
    public Object dlxSend(String message) throws AmqpException, UnsupportedEncodingException {
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        //fanout模式只往exchange里发送消息。分发到exchange下的所有queue
        rabbitTemplate.convertAndSend("testDLXExchange", "", new Message(message.getBytes("UTF-8"), messageProperties));

        return "message sended :message > " + message;
    }


    // 消息确认
    //DLX
    @ApiOperation(value = "topic发送接口", notes = "dlx")
    @GetMapping(value = "/confirmSend")
    public Object confirmSend(String message) throws AmqpException, UnsupportedEncodingException {
        // true：RabbitMQ会调用Basic.Return命令将消息返回给生产者
        // false：RabbitMQ会把消息直接丢弃
        rabbitTemplate.setMandatory(true);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        //放到消费者
//        //设置ReturnsCallback
//        rabbitTemplate.setReturnsCallback( returnedMessage -> {
//            //消息对象
//            Message msg = returnedMessage.getMessage();
//            //错误码
//            int replyCode = returnedMessage.getReplyCode();
//            //错误信息
//            String replyText = returnedMessage.getReplyText();
//            //交换机
//            String exchange = returnedMessage.getExchange();
//            //路由key
//            String routingKey = returnedMessage.getRoutingKey();
//
//            System.out.println("消息从交换机到队列失败，详细信息如下：");
//            System.out.println("消息对象："+msg);
//            System.out.println("错误码："+replyCode);
//            System.out.println("错误信息："+replyText);
//            System.out.println("交换机："+exchange);
//            System.out.println("路由key："+routingKey);
//        });

        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                if (ack){
                    System.out.println("成功发送消息到交换机");
                }else {
                    System.out.println("消息发送到交换机失败");
                    System.out.println("失败原因："+cause);
                }
            }
        });
        //故意发错队列
        rabbitTemplate.convertAndSend("123DLXExchange", "", new Message(message.getBytes("UTF-8"), messageProperties));

        return "message sended :message > " + message;
    }



}
