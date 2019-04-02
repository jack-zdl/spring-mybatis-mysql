package com.bsg.api.service.qmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * qmq消费者
 */
public class RabbitmqService implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("消息消费者 = " + message.toString());
    }
}
