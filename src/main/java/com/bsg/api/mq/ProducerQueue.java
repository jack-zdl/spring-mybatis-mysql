package com.bsg.api.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


/**
 * Created by zhang on 2017/4/26. mq的生产者
 * @description 消息由生产者发生，发送到MQ的exchange上，
 * @description exchange根据配置的路由方式发到相应的Queue上，Queue又将消息发送给consumer，
 * @description 消息从queue到consumer有push和pull两种方式。 消息队列的使用过程大概如下： 客户端连接到消息队列服务器，打开一个channel。 客户端声明一个exchange，并设置相关属性。
 * 客户端声明一个queue，并设置相关属性。 客户端使用routing key，在exchange和queue之间建立好绑定关系。 客户端投递消息到exchange。
 * @description exchange接收到消息后，就根据消息的key和已经设置的binding，进行消息路由，将消息投递到一个或多个队列里。 exchange也有几个类型，完全根据key进行投递的叫做Direct交换机，例如，绑定时设置了routing
 * key为”abc”，那么客户端提交的消息，只有设置了key为”abc”的才会投递到队列。
 */

/**
 * @description 基于消息的队列模式
 */
public class ProducerQueue {
    private final static String QUEUE_NAME = "hello";

    // AMQP的连接其实是对Socket做的封装, 注意以下AMQP协议的版本号，不同版本的协议用法可能不同。
    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); //使用默认端口连接本地rabbitmq服务器
        Connection connection = factory.newConnection(); //声明一个连接
        Channel channel = connection.createChannel(); //声明消息通道

        //exchange类型 参考:http://stephansun.iteye.com/blog/1452853
        //为消息通道绑定一个队列
        //队列的相关参数需要与第一次定义该队列时相同，否则会出错
        //参数1：队列名称
        //参数2：为true时server重启队列不会消失
        //参数3：队列是否是独占的，如果为true只能被一个connection使用，其他连接建立时会抛出异常
        //参数4：队列不再使用时是否自动删除（没有连接，并且没有未处理的消息)
        //参数5：建立队列时的其他参数
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World! hang de lei de 消息";
        //向server发布一条消息
        //参数1：exchange名字，若为空则使用默认的exchange
        //参数2：routing key
        //参数3：其他的属性
        //参数4：消息体
        //RabbitMQ默认有一个exchange，叫default exchange，它用一个空字符串表示，它是direct exchange类型，
        //任何发往这个exchange的消息都会被路由到routing key的名字对应的队列上，如果没有对应的队列，则消息会被丢弃
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
        System.out.println("P [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
