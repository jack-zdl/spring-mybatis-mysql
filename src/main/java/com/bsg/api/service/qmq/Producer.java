package com.bsg.api.service.qmq;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Description: 消息队列发送者
 * @Author:
 * @CreateTime:
 */
@Service
public class Producer {


    private String springQueueDemo = "spring-queue-demo";
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendQueue(String exchange_key, String queue_key, Object object) {
        // convertAndSend 将Java对象转换为消息发送至匹配key的交换机中Exchange

        amqpTemplate.convertAndSend("exchangeName"," queueName","msg");
        amqpTemplate.convertAndSend(springQueueDemo,"dasdasdasdasda");
        String foo = (String) amqpTemplate.receiveAndConvert(springQueueDemo);
        System.out.println(foo);
    }

    public void fanout(){
        final String ExchangeName = "fanoutec"; // 交换器名称

    }

}
