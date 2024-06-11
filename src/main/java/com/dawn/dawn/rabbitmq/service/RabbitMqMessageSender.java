package com.dawn.dawn.rabbitmq.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author chenliming
 * @date 2024/6/2 下午5:54
 */

@Service
public class RabbitMqMessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
}
