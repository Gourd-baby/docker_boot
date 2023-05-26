package com.atguigu.rabbitmq.one;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @version 1.0
 * @Author 作者名
 * @Date 2023/5/23 11:40
 * @注释
 */
public class Producer {
    //队列名称
    private final static String QUEUE_NAME = "hello";
    public static void main(String[] args) throws Exception{
        //1、创建生产者，生产消息
        String message = "hello world";

        //2、通过工厂建立连接，建立逻辑连接channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.118.101");
        factory.setUsername("admin");
        factory.setPassword("123");
        Connection connection = factory.newConnection();
        //因为创建一个连接会浪费资源，所以使用channel让一个connection连接变成多个逻辑连接(可以理解为一个connection被切割成了多个channel)
        Channel channel = connection.createChannel();


        //3、创建队列，存储消息并让消费者消费
        /**
         * 生成一个队列
         * 1.队列名称
         * 2.队列里面的消息是否持久化 默认消息存储在内存中
         * 3.该队列是否只供一个消费者进行消费 是否进行共享 false 可以多个消费者消费
         * 4.是否自动删除 最后一个消费者断开连接以后 该队列是否自动删除 true 自动删除
         * 5.其他参数
         */
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //4、发送消息
        /**
         * 发送一个消息
         * 1.发送到那个交换机
         * 2.路由的 key 是哪个
         * 3.其他的参数信息
         * 4.发送消息的消息体
         */
        channel.basicPublish("",QUEUE_NAME,null,message.getBytes());

        System.out.println("消息发送完毕");
    }
}
