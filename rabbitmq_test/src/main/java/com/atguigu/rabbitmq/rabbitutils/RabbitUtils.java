package com.atguigu.rabbitmq.rabbitutils;



import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @version 1.0
 * @Author 作者名
 * @Date 2023/5/25 12:48
 * @注释
 */
public class RabbitUtils {

    public static Channel getChannel(){
        //2、通过工厂建立连接，建立逻辑连接channel
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.118.101");
        factory.setUsername("admin");
        factory.setPassword("123");
        Connection connection = null;
        Channel channel = null;
        try {
            connection = factory.newConnection();
            channel = connection.createChannel();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return channel;
    }
}
