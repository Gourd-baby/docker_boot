package com.atguigu.rabbitmq.three;

import com.atguigu.rabbitmq.rabbitutils.RabbitUtils;
import com.rabbitmq.client.Channel;

import java.lang.reflect.Member;
import java.util.Scanner;

/**
 * @version 1.0
 * @Author 作者名
 * @Date 2023/5/26 13:34
 * @注释  模拟在手动应答下，其中一个消费者宕机，是否会将消息重新加入队列，然后供其他消费者消费
 */
public class Producer {
    //唯一队列名称
    public final static String TASK_QUEUE = "task_queue";

    public static void main(String[] args) throws Exception{
        //1、连接
        Channel channel = RabbitUtils.getChannel();
        //2、创建队列
        channel.queueDeclare(TASK_QUEUE,false,false,false,null);
        //3、生产消息并进行发送
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext())
        {
            String message = scanner.next();
            System.out.println("生产者发送消息："+message);
            channel.basicPublish("",TASK_QUEUE,null, message.getBytes());
        }


    }
}
