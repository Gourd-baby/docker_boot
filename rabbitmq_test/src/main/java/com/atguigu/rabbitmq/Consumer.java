package com.atguigu.rabbitmq;

import com.rabbitmq.client.*;

/**
 * @version 1.0
 * @Author 作者名
 * @Date 2023/5/23 12:09
 * @注释
 */
public class Consumer {
    //队列名称
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception{
        //1、建立连接
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.118.101");
        factory.setUsername("admin");
        factory.setPassword("123");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //2、消费者接收消息并进行消费
        /**
         * 消费者消费消息
         * 1.消费哪个队列
         * 2.消费成功之后是否要自动应答 true 代表自动应答 false 手动应答
         * 3.消费者未成功消费的回调
         * 4.消费者取消消费的回调
         */
        //推送的消息如何进行消费的接口回调
        DeliverCallback deliverCallback=(consumerTag,delivery)->{
            //记得要加getBody(),因为生产者发送过来的消息，不仅仅只包含消息，还包含消息头等一些内容。我们只需要消息体
            String message= new String(delivery.getBody());
            System.out.println(message);
        };
        //取消消费的一个回调接口 如在消费的时候队列被删除掉了
        CancelCallback cancelCallback=(consumerTag)->{
            System.out.println("消息消费被中断");
        };

        channel.basicConsume(QUEUE_NAME,true,deliverCallback,cancelCallback);
    }
}
