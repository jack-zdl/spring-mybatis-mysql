package com.bsg.api.mq;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * Created by zhang on 2017/4/26.
 */

/**
 * @description 基于消息的队列模式
 */
public class ConsumerQueue {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //队列的相关参数需要与第一次定义该队列时相同，否则会出错，使用channel.queueDeclarePassive()可只被动绑定已有队列，而不创建
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("C [*] Waiting for messages. To exit press CTRL+C");

        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("C [x] Received '" + message + "'");
            }
        };
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
