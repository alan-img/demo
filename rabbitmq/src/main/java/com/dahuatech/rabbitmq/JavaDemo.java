package com.dahuatech.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.util.concurrent.TimeUnit;

/**
 * <p>projectName: demo</p>
 * <p>packageName: com.dahuatech.rabbitmq</p>
 * <p>className: JavaDemo</p>
 * <p>date: 2024/10/11</p>
 *
 * @author qinjiawei(Administrator)
 * @version 1.0.0
 * @since JDK8.0
 */

public class JavaDemo {
    private final static String HOST = "192.168.101.222";
    private final static String USERNAME = "root";
    private final static String PASSWORD = "root";
    private final static String EXCHANGE_NAME = "test";
    private final static String EXCHANGE_TYPE = "topic";
    private final static String ROUTING_KEY = "test.";

    public static void main(String[] args) throws Exception {

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);
        connectionFactory.setUsername(USERNAME);
        connectionFactory.setPassword(PASSWORD);

        try (Connection connection = connectionFactory.newConnection();
             Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE, true);

            while (true) {
                String msg = "hello";
                channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY + 1, null, msg.getBytes("UTF-8"));
                TimeUnit.MILLISECONDS.sleep(500L);
            }

        }
    }
}
