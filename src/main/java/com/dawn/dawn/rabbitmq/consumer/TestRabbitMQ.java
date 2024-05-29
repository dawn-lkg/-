package com.dawn.dawn.rabbitmq.consumer;

import com.dawn.dawn.common.system.entity.User;
import com.dawn.dawn.rabbitmq.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author chenliming
 * @date 2024/5/28 下午5:26
 */
@Component
public class TestRabbitMQ {
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receiveMessage(User user) {
        System.out.println("Received message: " + user);
    }
}
