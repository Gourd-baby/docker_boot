package com.atguigu.rabbitmq.three;

import com.atguigu.rabbitmq.rabbitutils.RabbitUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

/**
 * @version 1.0
 * @Author 作者名
 * @Date 2023/5/26 13:35
 * @注释  模拟在手动应答下，其中一个消费者宕机，是否会将消息重新加入队列，然后供其他消费者消费
 */
public class Worker_02 {
    //唯一队列名称
    public final static String TASK_QUEUE = "task_queue";

    public static void main(String[] args) throws Exception{
        //1、建立连接
        Channel channel = RabbitUtils.getChannel();
        //2、接收消息
        /**
         * deliverCallback 接收并处理消息
         * CancelCallback 取消接收消息
         */
        DeliverCallback deliverCallback = (consumTag, delivery)->{
            String message = new String(delivery.getBody());
            System.out.println(consumTag);
            System.out.println("Worker_02-接收到消息："+message);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(),false);
        };
        CancelCallback cancelCallback = (consumTag)->{
            System.out.println("取消接收消息");
        };
        boolean autoACK = false;//取消自动应答，改为手动应答
        channel.basicConsume(TASK_QUEUE,autoACK,deliverCallback,cancelCallback);
    }
}
