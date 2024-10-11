package com.dahuatech.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.rabbitmq</p>
 * <p>className: RabbitMQConsumer</p>
 * <p>date: 2024/10/11</p>
 *
 * @author qinjiawei(Administrator)
 * @version 1.0.0
 * @since JDK8.0
 */

public class RabbitMQConsumer {
    private final static String HOST = "192.168.101.222";
    private final static String QUEUE_NAME = "test";

    public static void main(String[] args) throws Exception {
        // 创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST); // 设置RabbitMQ服务器的主机地址
        connectionFactory.setPort(5672);        // 默认端口5672
        connectionFactory.setUsername("root"); // 默认用户名guest
        connectionFactory.setPassword("root"); // 默认密码guest

        // 建立连接和通道
        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {

            // 声明要消费的队列
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

            // 定义消息处理的回调函数
            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                String message = new String(delivery.getBody(), "UTF-8");
                System.out.println(" [x] Received '" + message + "'");
            };

            // 消费消息
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });

            synchronized (RabbitMQConsumer.class) {
                RabbitMQConsumer.class.wait();
            }
        }

    }

}
